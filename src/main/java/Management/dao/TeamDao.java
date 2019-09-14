package Management.dao;

import Management.entity.*;
import Management.entity.strategy.*;
import Management.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/23 19:59
 * @Version 1.0
 */
@Component
public class TeamDao {
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    TeamStudentMapper teamStudentMapper;
    @Autowired
    StrategyMapper strategyMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    KlassStudentMapper classStudentMapper;
    @Autowired
    TeamValidMapper teamValidMapper;
    @Autowired
    KlassTeamMapper klassTeamMapper;


    public Team queryTeamById(BigInteger id){
        Team team=teamMapper.queryTeamById(id);
        if (team==null) return null;;
        Student leader=studentMapper.selectById(team.getLeaderId());
        leader.setPassword(null);
        team.setLeader(leader);
        List<Student> members=studentMapper.queryTeamMember(id,leader.getId());
        team.setMembers(members);
        return team;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateTeam(Team team){
        teamMapper.updateTeam(team);
        studentMapper.updateTeamStudent(team.getLeader());
        List<Student> members=team.getMembers();
        members.forEach(member->{
            studentMapper.updateTeamStudent(member);
        });
    }
    public void addTeamMember(BigInteger teamId,BigInteger studentId){
        teamStudentMapper.addTeamMember(teamId,studentId);
        return;
    }

    public void deleteMemberOrTeam(BigInteger teamId,BigInteger studentId){
        if (studentId.equals(teamMapper.queryTeamById(teamId).getLeaderId())){
            teamMapper.deleteTeam(teamId);
            teamStudentMapper.removeTeam(teamId);//要修改，不是这个MApper
        }
        else
            teamStudentMapper.removeTeamMember(teamId,studentId);
        return;
    }

    public void addTeamValid(TeamValidApplication teamValidApplication){
        TeamValidApplication teamValidApplication1=teamValidMapper.queryByTeamTeacherId(teamValidApplication.getTeamId(), teamValidApplication.getTeacherId());
        BigInteger id=new BigInteger("1");
        if (teamValidApplication1!=null) {
            id=teamValidApplication1.getId();
            teamValidApplication.setId(id);
            teamValidMapper.updateTeamValidReason(teamValidApplication);
        }
        else
            teamValidMapper.insertTeamValid(teamValidApplication);
        teamMapper.updateStatus(teamValidApplication.getTeamId(),2);
        return;
    }


    public BigInteger createTeam(Team team){
        List<Student> students=team.getMembers();
        Student leader=studentMapper.selectById(team.getLeaderId());
        students.add(leader);
        teamMapper.insertTeam(team);
        klassTeamMapper.insertKlassTeam(team.getKlassId(),team.getId());
        for (Student student : students) {
            teamStudentMapper.addTeamMember(team.getId(),student.getId());
        }
        return team.getId();
    }

    public boolean teamIsLegal(Long courseId,Long teamId){
        return isLegal("TeamStrategy",courseId,teamId);
    }
    /**
     * 组队策略
     */
    public boolean isLegal(String strategy,Long id,Long teamId){
        System.out.println(strategy+id);
        switch (strategy){
            case "TeamStrategy":
                return teamStrategyIsLegal(id,teamId);
            case "TeamAndStrategy":
                return teamAndStrategyIsLegal(id,teamId);
            case "TeamOrStrategy":
                return teamOrStrategyIsLegal(id,teamId);
            case "ConflictCourseStrategy":
                return conflictCourseStrategyIsLegal(id,teamId);
            case "MemberLimitStrategy":
                return memberLimitStrategy(id,teamId);
            case "CourseMemberLimitStrategy":
                return courseMemberLimitStrategyIsLegal(id,teamId);
            default:break;
        }
        return false;
    }
    public boolean teamStrategyIsLegal(Long id,Long teamId){
        ArrayList<TeamStrategy> strategyEntity=strategyMapper.getTeamStrategy(id);
        for(TeamStrategy teamStrategy:strategyEntity){
            if(!isLegal(teamStrategy.getStrategyName(),teamStrategy.getStrategyId(),teamId)){
                return false;
            }
        }
        return true;
    }

    /**
     * 处理team_and_strategy
     * @param id
     * @return
     */
    public boolean teamAndStrategyIsLegal(Long id,Long teamId){
        ArrayList<TeamAndStrategy> teamAndStrategyEntity=strategyMapper.getTeamAndStrategy(id);
        for(TeamAndStrategy teamAndStrategyEntity1:teamAndStrategyEntity){
            if(!isLegal(teamAndStrategyEntity1.getStrategyName(),teamAndStrategyEntity1.getStrategyId(),teamId)){
                return false;
            }
        }
        return true;
    }

    /**
     * 处理team_or_strategy
     * @param id
     * @return
     */
    public boolean teamOrStrategyIsLegal(Long id,Long teamId){
        ArrayList<TeamOrStrategy> teamOrStrategyEntity=strategyMapper.getTeamOrStrategy(id);
        for(TeamOrStrategy teamOrStrategyEntity1:teamOrStrategyEntity){
            if(isLegal(teamOrStrategyEntity1.getStrategy(),teamOrStrategyEntity1.getStrategyId(),teamId)){
                return true;
            }
        }
        return false;
    }

    /**
     * 处理conflict_course_strategy的数据
     * @param id
     * @param teamId
     * @return
     */
    public boolean conflictCourseStrategyIsLegal(Long id,Long teamId){
        Long courseId=0L;
        ArrayList<ConflictCourseStrategy> conflictCourseStrategies=strategyMapper.getConflictCourseStrategy(id);
        ArrayList<Student> studentEntities=studentMapper.getMembers(teamId);
        for(Student studentEntity:studentEntities){
            ArrayList<SimpleCourseEntity> simpleCourseEntities=courseMapper.findSimpleCourseEntityByTeacherId(studentEntity.getId().longValue());
            for(SimpleCourseEntity simpleCourseEntity:simpleCourseEntities){
                for(ConflictCourseStrategy conflictCourseStrategy:conflictCourseStrategies){
                    if(conflictCourseStrategy.getCourseId().equals(simpleCourseEntity.getId())){
                        if(courseId==0L){
                            courseId=conflictCourseStrategy.getCourseId();
                        }else if(!courseId.equals(conflictCourseStrategy.getCourseId())){
                            System.out.println("conflictCourseStrategy"+id+"不合法");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * 处理course_member_limit_strategy的数据
     * @param id
     * @param teamId
     * @return
     */
    public boolean courseMemberLimitStrategyIsLegal(Long id,Long teamId){
        CourseMemberLimitStrategy courseMemberLimitStrategyEntities=strategyMapper.getCourseMemberLimitStrategy(id);
        Integer count=0;
        ArrayList<Student> studentEntities=studentMapper.getMembers(teamId);
        for(Student studentEntity:studentEntities){
            if(classStudentMapper.getKlassIdByCourseAndStudent(courseMemberLimitStrategyEntities.getCourseId(),studentEntity.getId().longValue())!=null){
                count++;
            }
        }
        if(count>=courseMemberLimitStrategyEntities.getMinMember()&&count<=courseMemberLimitStrategyEntities.getMaxMember()){
            return true;
        }else{
            System.out.println("courseMemberLimitStrategy"+id+"不合法"+"人数为"+count);
            return false;
        }
    }

    /**
     * 处理member_limit_strategy
     * @param id
     * @param teamId
     * @return
     */
    public boolean memberLimitStrategy(Long id,Long teamId){
        MemberLimitStrategy memberLimitStrategy=strategyMapper.getMemberLimitStrategy(id);
        ArrayList<Student> studentEntities=studentMapper.getMembers(teamId);
        if(studentEntities.size()>=memberLimitStrategy.getMinMember()&&studentEntities.size()<=memberLimitStrategy.getMaxMember()){
            return true;
        }else {
            System.out.println("memberLimitStrategy"+id+"不合法");
            return false;
        }
    }



}
