package Management.domain.service.impl;

import Management.dao.RoundDao;
import Management.dao.SeminarDao;
import Management.domain.utils.DataUtil;
import Management.entity.*;
import Management.mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/21
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SeminarService {
    @Autowired
    SeminarDao seminarDao;

    @Autowired
    RoundDao roundDao;

    @Autowired
    ClassSeminarMapper classSeminarMapper;

    @Autowired
    ClassMapper classMapper;

    @Autowired
    SeminarMapper seminarMapper;

    @Autowired
    RoundMapper roundMapper;

    @Autowired
    SeminarScoreMapper seminarScoreMapper;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    AttendanceMapper attendanceMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    RoundScoreMapper roundScoreMapper;

    @Autowired
    KlassStudentMapper klassStudentMapper;

    public BigInteger addSeminar(Seminar seminar) {
        if(DataUtil.isEmpty(seminar.getRoundId())) {
            BigInteger bigInteger=roundDao.addRound(seminar);
            seminar.setRoundId(bigInteger);
        }
        List<Seminar> seminars=seminarMapper.querryByRoundId(seminar.getRoundId());
        Integer seminarSerial=new Integer("1");
        if(DataUtil.isNotEmpty(seminars)){
            seminarSerial=seminars.get(seminars.size()-1).getSeminarSerial()+1;
        }
        seminar.setSeminarSerial(seminarSerial);
        BigInteger seminarId =seminarDao.addSeminar(seminar);
        return seminarId;

    }

    public List<Klass> queryClass(BigInteger seminarId) {
        List<BigInteger> classes=classSeminarMapper.selectClassBySeminarId(seminarId);
        List<Klass> klasses=new ArrayList<Klass>();
        if(DataUtil.isNotEmpty(classes)){
            classes.forEach(klassId->{
                Klass klass=classMapper.selectById(klassId);
                klasses.add(klass);
            });
        }
        return klasses;
    }

    public void deleteSeminar(BigInteger seminarId, BigInteger classId) {
       classSeminarMapper.deleteBySeminarIdAndClassId(seminarId,classId);
        return;
    }

    public void updateSeminar(Seminar seminar) {
        seminarMapper.updateSeminar(seminar);
        return;
    }

    public Seminar selectById(BigInteger seminarId) {
        Seminar seminar=seminarMapper.selectById(seminarId);
        return seminar;
    }

    public int queryStatus(BigInteger seminarId) {
        return classSeminarMapper.selectStatus(seminarId);
    }

    public void updateDDL(KlassSeminar klassSeminar) {
        classSeminarMapper.updateDDL(klassSeminar);
        return;
    }

    public String queryDDL(BigInteger seminarId, BigInteger classId) {
        KlassSeminar klassSeminar=classSeminarMapper.selectDDL(seminarId,classId);
        return klassSeminar.getReportDDL();
    }

    public void updateRound(BigInteger seminarId, BigInteger roundId) {
        seminarMapper.updateRound(seminarId,roundId);
        return;
    }

    public void updateStatus(KlassSeminar klassSeminar) {
        classSeminarMapper.updateStatus(klassSeminar);
        return;
    }

    public SeminarScore querySeminarScore(BigInteger seminarId, BigInteger teamId) {
        SeminarScore seminarScore=new SeminarScore();
        List<BigInteger> ids=classSeminarMapper.selectIdBySeminarId(seminarId);
        ids.forEach(id->{
            SeminarScore seminarScore1=seminarScoreMapper.querySeminarScore(id,teamId);
            if(DataUtil.isNotEmpty(seminarScore1)){
                BeanUtils.copyProperties(seminarScore1,seminarScore);
            }
        });
        return seminarScore;
    }

    public Team queryTeam(BigInteger teamId) {
        return teamMapper.queryTeamById(teamId);
    }

    public void updateSeminarScore(BigInteger seminarId, BigInteger teamId, SeminarScore seminarScore2) {
        List<SeminarScore> seminarScores=new ArrayList<SeminarScore>();
        BigInteger klassId=teamMapper.queryTeamById(teamId).getKlassId();
        BigInteger klassSeminarId=classSeminarMapper.selectId(klassId,seminarId);
        seminarScore2.setKlassSeminarId(klassSeminarId);
        seminarScore2.setTeamId(teamId);
        seminarScoreMapper.updateSeminarScore(seminarScore2);
        //更改round_score
        BigInteger roundId=seminarMapper.selectById(seminarId).getRoundId();
        //蕴藏给分规则的round
        Round round=roundMapper.querryById(roundId);
        List<KlassSeminar> klassSeminars=new ArrayList<KlassSeminar>();
        //该round下的所有讨论课
        List<Seminar> seminars=seminarMapper.querryByRoundId(roundId);
        seminars.forEach(seminar -> {
            BigInteger klassSeminarId1=classSeminarMapper.selectId(klassId,seminarId);
            SeminarScore seminarScore=seminarScoreMapper.querySeminarScore(klassSeminarId1,teamId);
            seminarScores.add(seminarScore);
        });
        RoundScore roundScore=new RoundScore();
        roundScore.setRoundId(roundId);
        roundScore.setTeamId(teamId);
        BigDecimal prensentationScore=new BigDecimal("0");
        BigDecimal questionScore=new BigDecimal("0");
        BigDecimal reportScore=new BigDecimal("0");
        BigDecimal total=new BigDecimal("0");

        if(round.getPresentationScoreMethod()==0){
            int count=0;
            for(SeminarScore seminarScore:seminarScores) {
                if (DataUtil.isNotEmpty(seminarScore.getPresentationScore())) {
                    prensentationScore = seminarScore.getPresentationScore().add(prensentationScore);
                    count++;
                }
            }
            if(count!=0){
                prensentationScore=prensentationScore.divide(new BigDecimal(count),1,RoundingMode.HALF_UP);
            }
        }else {
            for(SeminarScore seminarScore:seminarScores) {
                if (DataUtil.isNotEmpty(seminarScore.getPresentationScore())) {
                    if(prensentationScore.compareTo(seminarScore.getPresentationScore())==-1)
                    prensentationScore = seminarScore.getPresentationScore();
                }
            }
        }
        //提问分
        if(round.getQuestionScoreMethod()==0){
            int count=0;
            for(SeminarScore seminarScore:seminarScores) {
                if (DataUtil.isNotEmpty(seminarScore.getQuestionScore())) {
                    questionScore = seminarScore.getQuestionScore().add(questionScore);
                    count++;
                }
            }
            if(count!=0){
                questionScore=questionScore.divide(new BigDecimal(count),1,RoundingMode.HALF_UP);
            }
        }else {
            for(SeminarScore seminarScore:seminarScores) {
                if (DataUtil.isNotEmpty(seminarScore.getQuestionScore())) {
                    if(questionScore.compareTo(seminarScore.getQuestionScore())==-1)
                        questionScore = seminarScore.getQuestionScore();
                }
            }
        }
        //报告分
        if(round.getReportScoreMethod()==0){
            int count=0;
            for(SeminarScore seminarScore:seminarScores) {
                if (DataUtil.isNotEmpty(seminarScore.getReportScore())) {
                    reportScore = seminarScore.getReportScore().add(reportScore);
                    count++;
                }
            }
            if(count!=0){
                reportScore=reportScore.divide(new BigDecimal(count),1,RoundingMode.HALF_UP);
            }
        }else {
            for(SeminarScore seminarScore:seminarScores) {
                if (DataUtil.isNotEmpty(seminarScore.getReportScore())) {
                    if(reportScore.compareTo(seminarScore.getReportScore())==-1)
                        reportScore = seminarScore.getReportScore();
                }
            }
        }
        total=total.add(prensentationScore.multiply(new BigDecimal(0.4)))
                .add(questionScore.multiply(new BigDecimal(0.3)))
                        .add(reportScore.multiply(new BigDecimal(0.3)));
        roundScore.setPresentationScore(prensentationScore);
        roundScore.setQuestionScore(questionScore);
        roundScore.setReportScore(reportScore);
        roundScore.setTotalScore(total);
        roundScoreMapper.updateScore(roundScore);


    }

    public List<SeminarScore> selectId(BigInteger classId, BigInteger seminarId) {
        BigInteger bigInteger=classSeminarMapper.selectId(classId,seminarId);
        List<SeminarScore> seminarScores=seminarScoreMapper.selectsById(bigInteger);
        return seminarScores;
    }

    public List<Attendance> getAllAttendance(BigInteger seminarId, BigInteger classId) {
        List<Attendance> mainList=new ArrayList<Attendance>();
        Attendance attendance1=new Attendance();
        Seminar seminar=seminarMapper.selectById(seminarId);
        int maxTeam=seminar.getMaxTeam();
        BigInteger id=classSeminarMapper.selectId(classId,seminarId);
        List<Attendance> list=attendanceMapper.selectByKlassSeminarId(id);
        for(int i=0;i<maxTeam;i++){
            mainList.add(attendance1);
        }
        list.forEach(attendance -> {
            mainList.set(attendance.getTeamOrder()-1,attendance);
        });
//        if(mainList.size()>maxTeam)
//        {
//            for (int i=mainList.size()-maxTeam;i>1;i--)
//            mainList.remove(maxTeam+i-1);
//        }//
        return mainList;


    }

    public List<Question> getAllQuestion(BigInteger seminarId, BigInteger classId) {
        BigInteger id=classSeminarMapper.selectId(classId,seminarId);
        List<Question> list=questionMapper.selectByClassSeminarId(id);
        return list;

    }

    public KlassSeminar getNowPresentation(User user) {
        if(user.getRole().equals("teacher")) {
            List<KlassSeminar> klassSeminars = classSeminarMapper.getNowSeminar();
            return klassSeminars.get(klassSeminars.size() - 1);
        }else if (user.getRole().equals("student")){
            List<KlassStudentTable> klassStudentTables=klassStudentMapper.selectMyCourse(user.getId());
            List<KlassSeminar> klassSeminars=new ArrayList<>();
            klassStudentTables.forEach(klassStudentTable -> {
                List<KlassSeminar> klassSeminarss=classSeminarMapper.getNowSeminarByClassId(klassStudentTable.getKlassId());
                if(DataUtil.isNotEmpty(klassSeminarss)){
                    klassSeminars.addAll(klassSeminarss);
                }
            });
            return klassSeminars.get(klassSeminars.size() - 1);
        }
        return null;
    }

    public List<SeminarScore> getRoundSeminarScore(BigInteger roundId, BigInteger teamId) {
        List<Seminar> seminars = seminarDao.queryByRoundId(roundId);
        List<SeminarScore> seminarScores = new ArrayList<SeminarScore>();
        for (Seminar seminar : seminars) {
            SeminarScore seminarScore = new SeminarScore();
            List<BigInteger> ids = classSeminarMapper.selectIdBySeminarId(seminar.getId());
            ids.forEach(id -> {
                SeminarScore seminarScore1 = seminarScoreMapper.querySeminarScore(id, teamId);
                if (DataUtil.isNotEmpty(seminarScore1)) {
                    BeanUtils.copyProperties(seminarScore1, seminarScore);
                }
            });
            if (DataUtil.isNotEmpty(seminarScore.getKlassSeminarId()))
                seminarScores.add(seminarScore);
        }
        return  seminarScores;
    }

    public void updateSeminarReport(BigInteger seminarId, BigInteger teamId, SeminarScore seminarScore) {
        Team team=teamMapper.queryTeamById(teamId);
        BigInteger klassSeminar=classSeminarMapper.selectId(team.getKlassId(),seminarId);
        SeminarScore seminarScore1=seminarScoreMapper.querySeminarScore(klassSeminar,teamId);
        seminarScore1.setReportScore(seminarScore.getReportScore());
        this.updateSeminarScore(seminarId,teamId,seminarScore1);
    }

    public Seminar queryBySeminarKlassId(BigInteger klassSeminarId) {
        Seminar seminar=seminarMapper.queryByKlassSeminarId(klassSeminarId);
        return seminar;
    }

    public KlassSeminar queryByKSId(BigInteger seminarId,BigInteger klassId){
        return classSeminarMapper.selectKlassSeminar(seminarId,klassId);
    }

    public List<SeminarScore> getScoreAttendance(BigInteger classSeminarId) {
        List<Attendance> attendances=attendanceMapper.selectByKlassSeminarId(classSeminarId);
        List<SeminarScore> seminarScores=new ArrayList<>();
        attendances.forEach(attendance -> {
            SeminarScore seminarScore=seminarScoreMapper.querySeminarScore(classSeminarId,attendance.getTeamId());
            seminarScores.add(seminarScore);
        });
        return seminarScores;
    }
}


