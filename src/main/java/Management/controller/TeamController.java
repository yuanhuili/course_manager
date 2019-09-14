package Management.controller;

import Management.controller.vo.MembersVO;
import Management.controller.vo.TeamVO;
import Management.controller.vo.TeamValidVO;
import Management.dao.HandleDao;
import Management.dao.TeamDao;
import Management.domain.service.ClassService;
import Management.domain.service.CourseService;
import Management.domain.service.TeacherService;
import Management.domain.service.TeamService;
import Management.domain.service.impl.HandleService;
import Management.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/11
 */
@CrossOrigin
@RestController()
@RequestMapping("/team")
public class TeamController {
    @Autowired
    TeamService teamService;
    @Autowired
    CourseService courseService;
    @Autowired
    ClassService classService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    HandleService handleService;
    @Autowired
    TeamDao teamDao;

    @GetMapping(value = "/{teamId}")
    public TeamVO getTeamById(@PathVariable("teamId")BigInteger teamId){
        Team team=teamService.querryTeamById(teamId);
        if(team==null) return null;
        TeamVO teamVO=new TeamVO();
        BeanUtils.copyProperties(team,teamVO);
        Course course=new Course();
        Klass klass=new Klass();
        if(courseService.getCourseById(team.getCourseId())!=null) course=courseService.getCourseById(team.getCourseId());
            teamVO.setCourseName(course.getCourseName());
        if(classService.getClassById(team.getKlassId())!=null) klass=classService.getClassById(team.getKlassId());
            teamVO.setClassNmae(klass.getKlassTime());
        return teamVO;
    }

    @PutMapping(value = "/{teamId}")
    public void updateTeam(@PathVariable("teamId")BigInteger teamId,
                           @RequestBody Team team){
        team.setId(teamId);
        team.setLeaderId(team.getLeader().getId());
        teamService.updateTeam(team);
        return;
    }

    @DeleteMapping(value = "/{teamId}")
    public void deleteTeam(@PathVariable("teamId")BigInteger teamId){
        teamService.deleteTeam(teamId);
        return;
    }

    @PutMapping(value = "/{teamId}/add")
    public void addTeamMember(@PathVariable("teamId")BigInteger teamId,
                              @RequestBody MembersVO membersVO){
        List<Student> members=membersVO.getMembers();
        members.forEach(member->{
            teamService.addTeamMember(teamId,member.getId());
        });
        return;
    }
    @PutMapping(value = "/{teamId}/remove")
    public void removeMemberOrTeam(@PathVariable("teamId")BigInteger teamId,
                                   @RequestBody User user){
        teamService.removeMemberOrTeam(teamId,user.getId());
        return;
    }
    @PostMapping(value = "/{teamId}/teamValidRequest")
    public boolean teamValidRequest(@PathVariable("teamId")BigInteger teamId,
                                 @RequestBody TeamValidVO teamValidVO){
        TeamValidApplication teamValidApplication=new TeamValidApplication();
        teamValidApplication.setReason(teamValidVO.getReason());
        teamValidApplication.setTeamId(teamId);
        teamValidApplication.setTeacherId(teacherService.querryByCourseId(teamValidVO.getCourseId()).getId());
        teamService.sendTeamValid(teamValidApplication);
        return true;
    }
    @PutMapping(value = "/{shareTeamId}/handle/{status}")
    public Boolean handleValid(@PathVariable("shareTeamId")BigInteger shareTeamId,
                             @PathVariable("status")Integer status,
                             @RequestAttribute("user")User user){
//        if (user.getRole().equalsIgnoreCase("teacher"))
            handleService.handleTeamValid(shareTeamId,status);
            return true;
    }

    @PostMapping(value = "/newTeam")
    public BigInteger newTeam(@RequestBody Team team){
        BigInteger teamId=teamService.createTeam(team);
        return teamId;
    }
}
