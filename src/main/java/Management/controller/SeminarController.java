package Management.controller;

import Management.controller.vo.*;
import Management.domain.service.impl.SeminarService;
import Management.entity.*;
import Management.mapper.ClassMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/11
 */
@CrossOrigin
@RestController()
@RequestMapping("/seminar")
public class SeminarController {
    @Autowired
    SeminarService seminarService;

    @Autowired
    ClassMapper classMapper;

    @PostMapping()
    public BigInteger addSeminar(@RequestBody Seminar seminar){
        BigInteger seminarId=seminarService.addSeminar(seminar);
        return seminarId;
    }

    @GetMapping("/{seminarId}/class")
    public List<ClassVO> getSeminarClass(@PathVariable BigInteger seminarId){
        List<Klass> klasses=seminarService.queryClass(seminarId);
        List<ClassVO> classVOList=new ArrayList<ClassVO>();
        klasses.forEach(klass -> {
            ClassVO classVO=new ClassVO(klass);
            classVOList.add(classVO);
        });
        return classVOList;
    }

    @GetMapping("/{klassSeminarId}/klassSeminar")
    public Seminar getByKlassSeminar(@PathVariable("klassSeminarId")BigInteger klassSeminarId){
        Seminar seminar=seminarService.queryBySeminarKlassId(klassSeminarId);
        return  seminar;
    }

    @PutMapping("/{seminarId}")
    public Boolean updateSeminar(@PathVariable("seminarId") BigInteger seminarId,@RequestBody Seminar seminar){
        seminarService.updateSeminar(seminar);
        return true;
    }

    @DeleteMapping("/{seminarId}/class/{classId}")
    public Boolean deleteSeminar(@PathVariable("seminarId") BigInteger seminarId,
                              @PathVariable("classId") BigInteger classId){
        seminarService.deleteSeminar(seminarId,classId);
        return true;
    }

    @GetMapping("/{seminarId}")
    public ClassSeminarVO getSeminar(@PathVariable("seminarId") BigInteger seminarId){
        ClassSeminarVO seminarVO=new ClassSeminarVO();
        Seminar seminar=seminarService.selectById(seminarId);
        int status=seminarService.queryStatus(seminarId);
        BeanUtils.copyProperties(seminar,seminarVO);
        seminarVO.setStatus(status);
        return seminarVO;
    }

    @PutMapping("/{seminarId}/class/{classId}")
    public Boolean updateClassSeminar(@PathVariable("seminarId") BigInteger seminarId,
                                             @PathVariable("classId") BigInteger classId,
                                             @RequestBody KlassSeminar klassSeminar){
        klassSeminar.setSeminarId(seminarId);
        klassSeminar.setKlassId(classId);
        seminarService.updateDDL(klassSeminar);
        return true;
    }

    @GetMapping("/{seminarId}/class/{classId}")
    public KlassSeminar getClassSeminar(@PathVariable("seminarId") BigInteger seminarId,
                                          @PathVariable("classId") BigInteger classId){
//        ClassSeminarVO seminarVO=new ClassSeminarVO();
//        Seminar seminar=seminarService.selectById(seminarId);
//        int status=seminarService.queryStatus(seminarId);
//        String reportDDL=seminarService.queryDDL(seminarId,classId);
//        BeanUtils.copyProperties(seminar,seminarVO);
//        seminarVO.setReportDDL(reportDDL);
//        seminarVO.setStatus(status);
//        return seminarVO;
        return seminarService.queryByKSId(seminarId,classId);
    }

    @PutMapping("/{seminarId}/round/{roundId}")
    public Boolean updateSeminarRound(@PathVariable("seminarId") BigInteger seminarId,
                                   @PathVariable("roundId") BigInteger roundId
                                   ){
        seminarService.updateRound(seminarId,roundId);
        return true;
    }

    @PostMapping("/{seminarId}/status")
    public Boolean updateSeminarStatus(@PathVariable("seminarId") BigInteger seminarId,
                                   @RequestBody KlassSeminar klassSeminar){
        klassSeminar.setSeminarId(seminarId);
        seminarService.updateStatus(klassSeminar);
        return true;
    }

    @GetMapping("/{seminarId}/team/{teamId}/seminarScore")
    public SeminarScoreVO getSeminarScore(@PathVariable("seminarId") BigInteger seminarId,
                                          @PathVariable("teamId") BigInteger teamId){
        SeminarScoreVO seminarScoreVO=new SeminarScoreVO();
        SeminarScore seminarScore=seminarService.querySeminarScore(seminarId,teamId);
        Team team=seminarService.queryTeam(teamId);
        BeanUtils.copyProperties(seminarScore,seminarScoreVO);
        seminarScoreVO.setTeam(team);
        return seminarScoreVO;
    }

    @PutMapping("/{seminarId}/team/{teamId}/seminarScore")
    public Boolean updateSeminarScore(@PathVariable("seminarId") BigInteger seminarId,
                                          @PathVariable("teamId") BigInteger teamId,
                                      @RequestBody SeminarScore seminarScore){
        seminarService.updateSeminarScore(seminarId,teamId,seminarScore);
        return true;
    }

    @GetMapping("/{seminarId}/class/{classId}/seminarScore")
    public List<SeminarScoreVO> getAllSeminarScore(@PathVariable("seminarId") BigInteger seminarId,
                                      @PathVariable("classId") BigInteger classId){
        List<SeminarScoreVO> seminarScoreVOS=new ArrayList<SeminarScoreVO>();
        List<SeminarScore> seminarScores=seminarService.selectId(classId,seminarId);
        seminarScores.forEach(seminarScore -> {
            SeminarScoreVO seminarScoreVO=new SeminarScoreVO();
            Team team=seminarService.queryTeam(seminarScore.getTeamId());
            BeanUtils.copyProperties(seminarScore,seminarScoreVO);
            seminarScoreVO.setTeam(team);
            seminarScoreVOS.add(seminarScoreVO);
        });

        return seminarScoreVOS;
    }

    /**
     * 返回该讨论课下所有报名展示组和提问
     *
     * @param seminarId
     * @param classId
     * @return
     */
    @GetMapping("/{seminarId}/class/{classId}/presentation")
    public QuestionAndAttendanceVO getAllQA(@PathVariable("seminarId") BigInteger seminarId,
                                                      @PathVariable("classId") BigInteger classId){
        QuestionAndAttendanceVO questionAndAttendanceVO=new QuestionAndAttendanceVO();
        List<Attendance> attendances=seminarService.getAllAttendance(seminarId,classId);
        List<Question> questions=seminarService.getAllQuestion(seminarId,classId);
        questionAndAttendanceVO.setAttendances(attendances);
        questionAndAttendanceVO.setQuestions(questions);
        return questionAndAttendanceVO;
    }
/**
 * 当前有无正在进行的讨论课
 */
    @GetMapping("/presentation")
    public  KlassSeminarVO getNowPresentation(@RequestAttribute("user") User user){
        KlassSeminarVO klassSeminarVO = new KlassSeminarVO();
        try {
            KlassSeminar klassSeminar = seminarService.getNowPresentation(user);
            BeanUtils.copyProperties(klassSeminar, klassSeminarVO);
            Klass klass = classMapper.queryStudentClass(klassSeminar.getKlassId());
            klassSeminarVO.setCourseId(klass.getCourseId());
            return klassSeminarVO;
        }catch (Exception e){
            return klassSeminarVO;
        }
    }

    @PutMapping("/{seminarId}/team/{teamId}/reportScore")
    public Boolean updateScoreReport(@PathVariable("seminarId") BigInteger seminarId,
                                      @PathVariable("teamId") BigInteger teamId,
                                      @RequestBody SeminarScore seminarScore){
        seminarService.updateSeminarReport(seminarId,teamId,seminarScore);
        return true;
    }

    @GetMapping("/{classSeminarId}/reportScore")
    public List<SeminarScoreVO> updateScoreReport(@PathVariable("classSeminarId") BigInteger classSeminarId){
        List<SeminarScoreVO> seminarScoreVO=new ArrayList<>();
        List<SeminarScore> seminarScore=seminarService.getScoreAttendance(classSeminarId);
        seminarScore.forEach(seminarScore1 -> {
            SeminarScoreVO seminarScoreVO1=new SeminarScoreVO();
            BeanUtils.copyProperties(seminarScore1,seminarScoreVO1);
            seminarScoreVO.add(seminarScoreVO1);
        });
        return seminarScoreVO;
    }



}
