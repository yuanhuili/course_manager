package Management.domain.service.impl;

import Management.domain.service.TeacherService;
import Management.domain.utils.DataUtil;
import Management.entity.*;
import Management.mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    TeamValidMapper teamValidMapper;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    SeminarShareMapper seminarShareMapper;

    @Autowired
    TeamShareMapper teamShareMapper;

    @Autowired
    CourseMapper courseMapper;

    @Override
    public List<Teacher> selectAll() {
        List<Teacher> list=teacherMapper.selectAll();
        if (DataUtil.isNotEmpty(list)){
            return list;
        }
        return null;
    }

    @Override
    public Teacher selectByIdentity(String identity) {
        Teacher teacher= teacherMapper.selectByAccount(identity);
        if(DataUtil.isNotEmpty(teacher)){
            return teacher;
        }else {
            teacher=teacherMapper.selectByName(identity);
            return teacher;
        }
    }

    @Override
    public Teacher modifyInfo(Teacher teacher) {
//        try {
            teacherMapper.modifyInfo(teacher);
            Teacher teacherInfo=teacherMapper.selectByAccount(teacher.getAccount());
            return teacherInfo;
//        }catch (Exception e){
//            throw FlywayCodeEnum.DB_UPDATE_ERROR.getException();
//        }
    }

    @Override
    public Teacher resetPassword(BigInteger teacherId) {
        teacherMapper.resetPassword(teacherId);
        Teacher teacher=teacherMapper.selectById(teacherId);
        return teacher;
    }

    @Override
    public Boolean deleteTeacher(BigInteger teacherId) {
        try{
            teacherMapper.deleteById(teacherId);

        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Teacher activateTeacher(Teacher teacher) {
        teacherMapper.active(teacher);
        Teacher teacherInfo=teacherMapper.selectById(teacher.getId());
        return teacherInfo;

    }

    @Override
    public Teacher add(Teacher teacher) {
        teacherMapper.add(teacher);
        return teacherMapper.selectByAccount(teacher.getAccount());
    }
    @Override
    public Teacher querryByCourseId(BigInteger courseId){
        Teacher teacher=teacherMapper.querryByCourseId(courseId);
        return teacher;
    }

    //li

    @Override
    public Commission all(BigInteger userId) {
        List<TeamValidApplications> teamValidApplicationsList=new ArrayList<TeamValidApplications>();
        List<TeamValidApplication> teamValidApplications=teamValidMapper.selectByTeacherId(userId);
        if(DataUtil.isNotEmpty(teamValidApplications)){
            teamValidApplications.forEach(teamValidApplication -> {
                TeamValidApplications teamValidApplications1=new TeamValidApplications();
                String teamName=teamMapper.selectTeamName(teamValidApplication.getTeamId());
                String teacherName=teacherMapper.selectById(teamValidApplication.getTeacherId()).getTeacherName();
                teamValidApplications1.setTeamName(teamName);
                teamValidApplications1.setTeacherName(teacherName);
                BeanUtils.copyProperties(teamValidApplication,teamValidApplications1);
                teamValidApplicationsList.add(teamValidApplications1);
            });
        }
        List<ShareTeamApplications> shareTeamApplicationsList=new ArrayList<ShareTeamApplications>();
        List<ShareTeamApplication> shareTeamApplications=teamShareMapper.querryByTeacherId(userId);
        shareTeamApplications.forEach(shareTeamApplication -> {
            ShareTeamApplications shareTeamApplications1=new ShareTeamApplications();
            BeanUtils.copyProperties(shareTeamApplication,shareTeamApplications1);
            Course mainCourse=courseMapper.queryByCourseId(shareTeamApplication.getMainCourseId());
            Course subCourse=courseMapper.queryByCourseId(shareTeamApplication.getSubCourseId());
            shareTeamApplications1.setMainCourseName(mainCourse.getCourseName());
            shareTeamApplications1.setSubCourseName(subCourse.getCourseName());
            String mainTeacherName=teacherMapper.selectById(mainCourse.getTeacherId()).getTeacherName();
            String subTeacherName=teacherMapper.selectById(subCourse.getTeacherId()).getTeacherName();
            shareTeamApplications1.setMainTeacherName(mainTeacherName);
            shareTeamApplications1.setSubCourseTeacherName(subTeacherName);
            shareTeamApplicationsList.add(shareTeamApplications1);
        });
        Commission commission=new Commission();
        commission.setShareTeamApplications(shareTeamApplicationsList);
        commission.setTeamValidApplications(teamValidApplicationsList);
        return commission;
    }

    @Override
    public Commission queryHistoryMessage(BigInteger userId){
        List<TeamValidApplications> teamValidApplicationsList=new ArrayList<TeamValidApplications>();
        List<TeamValidApplication> teamValidApplications=teamValidMapper.selectHistoryMessage(userId);
        if(DataUtil.isNotEmpty(teamValidApplications)){
            teamValidApplications.forEach(teamValidApplication -> {
                TeamValidApplications teamValidApplications1=new TeamValidApplications();
                String teamName=teamMapper.selectTeamName(teamValidApplication.getTeamId());
                String teacherName=teacherMapper.selectById(teamValidApplication.getTeacherId()).getTeacherName();
                teamValidApplications1.setTeamName(teamName);
                teamValidApplications1.setTeacherName(teacherName);
                BeanUtils.copyProperties(teamValidApplication,teamValidApplications1);
                teamValidApplicationsList.add(teamValidApplications1);
            });
        }
        List<ShareTeamApplications> shareTeamApplicationsList=new ArrayList<ShareTeamApplications>();
        List<ShareTeamApplication> shareTeamApplications=teamShareMapper.selectHistoryMessage(userId);
        shareTeamApplications.forEach(shareTeamApplication -> {
            ShareTeamApplications shareTeamApplications1=new ShareTeamApplications();
            BeanUtils.copyProperties(shareTeamApplication,shareTeamApplications1);
            Course mainCourse=courseMapper.queryByCourseId(shareTeamApplication.getMainCourseId());
            Course subCourse=courseMapper.queryByCourseId(shareTeamApplication.getSubCourseId());
            shareTeamApplications1.setMainCourseName(mainCourse.getCourseName());
            shareTeamApplications1.setSubCourseName(subCourse.getCourseName());
            String mainTeacherName=teacherMapper.selectById(mainCourse.getTeacherId()).getTeacherName();
            String subTeacherName=teacherMapper.selectById(subCourse.getTeacherId()).getTeacherName();
            shareTeamApplications1.setMainTeacherName(mainTeacherName);
            shareTeamApplications1.setSubCourseTeacherName(subTeacherName);
            shareTeamApplicationsList.add(shareTeamApplications1);
        });
        Commission commission=new Commission();
        commission.setShareTeamApplications(shareTeamApplicationsList);
        commission.setTeamValidApplications(teamValidApplicationsList);
        return commission;
    }
}
