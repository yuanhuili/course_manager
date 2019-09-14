package Management.domain.service.impl;

import Management.dao.CourseDao;
import Management.domain.utils.DataUtil;
import Management.entity.*;
import Management.domain.service.CourseService;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import Management.mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Author: yuanhuili
 * @Date: 2018/12/4 20:55
 * @Version 1.0
 */

@Service
public class CourseServiceimpl implements CourseService{
    @Autowired
    private CourseDao courseDao;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    private SeminarShareMapper seminarShareMapper;
    @Autowired
    private TeamShareMapper teamShareMapper;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private SeminarMapper seminarMapper;
    @Autowired
    private ClassSeminarMapper classSeminarMapper;
    @Autowired
    private SeminarScoreMapper seminarScoreMapper;
    @Autowired
    private TeamMapper teamMapper;
    @Override
    public BigInteger putCourse(Course course){
        courseDao.putCourse(course);
        //加入组队限制
        courseDao.addTeamStrategy(course);
        //加入冲突课程限制
        courseDao.addConflictStrategy(course);

        return course.getId();
    }

    @Override
    public void deleteCourseById(BigInteger id) {
        courseDao.delteCourse(id);
    }

    @Override
    public List<Team> getTeamByCourseId(BigInteger courseId) {
        List<Team> teams=courseDao.getTeam(courseId);
        return teams;
    }



    @Override
    public Team getMyTeam(BigInteger courseId, BigInteger studentId) {
        Team team=courseDao.getMyTeam(courseId,studentId);
        return team;
    }

    @Override
    public List<Klass> getKalssById(BigInteger id) {
        List<Klass> klasses=courseDao.getKlasses(id);
        return klasses;
    }



    @Override
    public List<Course> getAllKlass(BigInteger userId,String userRole) {
        List<Course> courses=new ArrayList<Course>();
        if(userRole.equalsIgnoreCase("student")){
            courses=courseDao.getAllStudentCourse(userId);
        }
        else if(userRole.equalsIgnoreCase("teacher")){
            courses=courseDao.getAllTeacherCourse(userId);
        }
        return courses;
    }

    @Override
    public List<Round> getRound(BigInteger id) {
        List<Round> rounds=courseDao.getRound(id);
        return rounds;
    }

    @Override
    public Course getCourseById(BigInteger id) {
        Course course=courseDao.getCourseById(id);
        return course;
    }


    @Override
    public List<Student> getNoTeam(BigInteger courseId,BigInteger userId){
        List<Student> students=courseDao.getNoteam(courseId);
        return students;
    }

    @Override
    public BigInteger insertKlassById(Klass klass) {
        BigInteger id=courseDao.putClass(klass);
        return id;
    }

    @Override
    public List<ShareTeamApplication> getTeamShareByCourseId(BigInteger id) {
        List<ShareTeamApplication> shareTeamApplications=courseDao.getShareTeam(id);
        return shareTeamApplications;
    }

    @Override
    public List<ShareSeminarApplivation> getSeminarShareByCourseId(BigInteger id) {
        List<ShareSeminarApplivation> shareSeminarApplivations=courseDao.getShareSeminar(id);
        return shareSeminarApplivations;
    }



    @Override
    public BigInteger teamShareRequest(ShareTeamApplication shareTeamApplication) {
        BigInteger mainCourseId=shareTeamApplication.getMainCourseId();
        BigInteger subCourseId=shareTeamApplication.getSubCourseId();
        ShareTeamApplication shareTeamApplication1=teamShareMapper.queryByMainSubCourseId(mainCourseId,subCourseId);
        if (shareTeamApplication1!=null) {
            return new BigInteger("0");
        }
        else teamShareMapper.insertTeamShare(shareTeamApplication);
        return shareTeamApplication.getId();
    }

    @Override
    public BigInteger seminarShareRequest(ShareSeminarApplivation shareSeminarApplivation) {
        BigInteger mainCourseId=shareSeminarApplivation.getMainCourseId();
        BigInteger subCourseId=shareSeminarApplivation.getSubCourseId();
        ShareSeminarApplivation shareSeminarApplivation1=seminarShareMapper.queryByMainSubCourseId(mainCourseId,subCourseId);
        if(shareSeminarApplivation1!=null){
            return new  BigInteger("0");
        }

        seminarShareMapper.insertShareSeminar(shareSeminarApplivation);
        return shareSeminarApplivation.getId();
    }

    @Override
    public List<Course> getAllCourse(){
        List<Course>courses= courseDao.queryAllCourse();
        return courses;
    }

    @Override
    public List<ScoreExcel> getScore(BigInteger courseId) {
        List<ScoreExcel> scoreExcels=new ArrayList<>();
        List<BigInteger> klassSeminarIds=new ArrayList<>();
        List<Seminar> seminars=seminarMapper.selectByCourseId(courseId);
        seminars.forEach(seminar -> {
            List<BigInteger> klassSeminarId=classSeminarMapper.selectIdBySeminarId(seminar.getId());
            klassSeminarIds.addAll(klassSeminarId);
        });
        klassSeminarIds.forEach(l->{
            List<SeminarScore> seminarScore1=seminarScoreMapper.selectsById(l);
            KlassSeminar klassSeminar=classSeminarMapper.selectById(l);
            Seminar seminar=seminarMapper.selectById(klassSeminar.getSeminarId());
            String seminarName=seminar.getSeminarName()+"讨论课";
            seminarScore1.forEach(i->{
                ScoreExcel scoreExcel=new ScoreExcel();
                BeanUtils.copyProperties(i,scoreExcel);
                scoreExcel.setSeminarName(seminarName);
                Team team=teamMapper.queryTeamById(i.getTeamId());
                String teamName=team.getKlassSerial()+"-"+team.getTeamSerial()+"组";
                scoreExcel.setTeamName(teamName);
                scoreExcels.add(scoreExcel);
            });
        });
        return scoreExcels;
    }
}

