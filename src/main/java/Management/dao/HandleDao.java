package Management.dao;

import Management.domain.utils.DataUtil;
import Management.entity.*;
import Management.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.objenesis.instantiator.sun.MagicInstantiator;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/26 21:09
 * @Version 1.0
 */
@Component
public class HandleDao {
    @Autowired
    SeminarShareMapper seminarShareMapper;
    @Autowired
    TeamShareMapper teamShareMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    TeamValidMapper teamValidMapper;
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    TeamStudentMapper teamStudentMapper;
    @Autowired
    ClassMapper classMapper;
    @Autowired
    KlassTeamMapper klassTeamMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ClassDao classDao;
    @Autowired
    KlassStudentMapper klassStudentMapper;


    public void deleteSeminarShare(BigInteger id) {
        ShareSeminarApplivation shareSeminarApplivation=new ShareSeminarApplivation();
        shareSeminarApplivation=seminarShareMapper.queryById(id);
        if (shareSeminarApplivation!=null){
            seminarShareMapper.deleteSeminarShare(id);
            BigInteger subCourseId=shareSeminarApplivation.getSubCourseId();
            courseMapper.handleSeminarShare(subCourseId,null);
        }
        return;
    }


    public void deleteTeamShare(BigInteger id){
        ShareTeamApplication shareTeamApplication=new ShareTeamApplication();
        shareTeamApplication=teamShareMapper.queryById(id);
        if(shareTeamApplication!=null){
            teamShareMapper.deleteTeamShare(id);
            BigInteger subCourseId=shareTeamApplication.getSubCourseId();
            courseMapper.handleTeamShare(subCourseId,null);
        }
        return;
    }

    public void handleTeamValid(BigInteger teamShareId,Integer status){
        TeamValidApplication teamValidApplication=new TeamValidApplication();
        teamValidApplication=teamValidMapper.queryById(teamShareId);
        teamValidMapper.updateRecord(teamShareId,status);
        if (teamValidApplication!=null)
            teamMapper.updateStatus(teamValidApplication.getTeamId(),status);

    }

    public void handleSeminarShare(BigInteger id,Integer status){
        ShareSeminarApplivation shareSeminarApplivation=new ShareSeminarApplivation();
        shareSeminarApplivation=seminarShareMapper.queryById(id);
        if (shareSeminarApplivation!=null){
            seminarShareMapper.updateStatus(id,status);
            BigInteger subCourseId=shareSeminarApplivation.getSubCourseId();
            BigInteger mainCourseId=new BigInteger("16");
            if(status==1)
                mainCourseId=shareSeminarApplivation.getMainCourseId();
            courseMapper.handleTeamShare(subCourseId,mainCourseId);
        }
        return;
    }
    public void handleTeamShare(BigInteger id,Integer status){
        ShareTeamApplication shareTeamApplication=new ShareTeamApplication();
        shareTeamApplication=teamShareMapper.queryById(id);
        if(DataUtil.isNotEmpty(shareTeamApplication)){
            teamShareMapper.updateStatus(id,status);
            BigInteger subCourseId=shareTeamApplication.getSubCourseId();
            BigInteger mainCourseId=new BigInteger("16");
            Course mainCourse=courseMapper.queryByCourseId(mainCourseId);
            Course subCourse=courseMapper.queryByCourseId(subCourseId);
            List<BigInteger> subKlassIds=classMapper.queryKlassIdByCourseId(subCourseId);
            if(status==1) {
                mainCourseId = shareTeamApplication.getMainCourseId();
                List<Team> teamList=teamMapper.queryByCourseId(mainCourseId);
                courseMapper.handleTeamShare(subCourseId, mainCourseId);
//                System.out.println("mainCourseID:"+mainCourseId);
                //删除从课程学生和队伍的连联系
                teamStudentMapper.deleteSubRelation(shareTeamApplication.getSubCourseId());
                //删除从课程班级和队伍的联系

                //删除从课程的team
                teamMapper.deleteByCourseId(shareTeamApplication.getSubCourseId());
                //删除从课程班级和队伍的联系
                for (BigInteger subKlassId : subKlassIds) {
                    klassTeamMapper.deleteSubRelation(subKlassId);
                }
                for(int i=0;i<teamList.size();i++){
                    //System.out.println("teamID:"+teamList.get(i).getId());
                    this.teamChangeCourse(teamList.get(i),mainCourse,subCourse);
                }
            }
            if(status==0){
                mainCourseId = shareTeamApplication.getMainCourseId();
                courseMapper.handleTeamShare(subCourseId, null );
            }
        }
        return;
    }

    private void teamChangeCourse(Team team,Course mainCourse,Course subCourse){
        //获取该组所有的学生
        List<Student> studentList=studentMapper.getMembers(team.getId().longValue());
        //多数学生所在的klassId
        Long maxKlassId=null;
        for(int i=0;i<studentList.size();i++){
            Map<Long,Integer> klassMap=new HashMap<>();
            Integer temp=0;
            //获取这个学生的entity
            Student student=studentMapper.selectById(studentList.get(i).getId());
            Long klassId=null;
            //获得这个学生的klassId
            if(klassStudentMapper.getKlassIdByCourseAndStudent(subCourse.getId().longValue(),student.getId().longValue())!=null)
            klassId=klassStudentMapper.getKlassIdByCourseAndStudent(subCourse.getId().longValue(),student.getId().longValue()).longValue();
            //收集每个学生的klassId
//            System.out.println("studentId:"+student.getId());
//            System.out.println("subCourseID:"+subCourse.getId());
//            System.out.println("klassID:"+klassId);
            if(klassId!=null){
                Integer klassCount=klassMap.get(klassId);
                if(klassCount==null){
                    klassMap.put(klassId,1);
                }
                else{
                    klassMap.put(klassId,klassCount+1);
                }
            }
            //取出人数最多的klassId
            for (Long key : klassMap.keySet()) {
                Integer value=klassMap.get(key);
                if(value>temp){
                    temp=value;
                    maxKlassId=key;
                }
            }
        }
//        System.out.println("maxKlassId:"+maxKlassId);
//        System.out.println("teamID:"+team.getId());
        //创建新的klass_team的副本

        if (maxKlassId!=null)
            klassTeamMapper.insertKlassTeam(BigInteger.valueOf(maxKlassId),team.getId());
    }

}
