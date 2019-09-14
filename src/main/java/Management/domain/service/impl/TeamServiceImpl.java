package Management.domain.service.impl;

import Management.dao.HandleDao;
import Management.dao.TeamDao;
import Management.domain.service.TeamService;
import Management.entity.Klass;
import Management.entity.Team;
import Management.entity.TeamValidApplication;
import Management.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.PrinterGraphics;
import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/24 16:00
 * @Version 1.0
 */
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamMapper teamMapper;
    @Autowired
    TeamDao teamDao;
    @Autowired
    TeamValidMapper teamValidMapper;
    @Autowired
    KlassStudentMapper klassStudentMapper;
    @Autowired
    HandleDao handleDao;
    @Autowired
    TeamStudentMapper teamStudentMapper;
    @Autowired
    ClassMapper classMapper;

    @Override
    public Team querryTeamById(BigInteger id){
        Team team=teamDao.queryTeamById(id);
        return team;
    }
    @Override
    public void updateTeam(Team team){
        teamDao.updateTeam(team);
        if(teamDao.teamIsLegal(team.getCourseId().longValue(),team.getId().longValue())){
            teamMapper.updateStatus(team.getId(),1);
        }
        else {
            teamMapper.updateStatus(team.getId(),0);
        }
        return;
    }
    @Override
    public BigInteger createTeam(Team team){
        List<Integer> serials=teamMapper.getSerial(team.getKlassId());
        Integer serial=serials.size()+1;
        Klass klass=classMapper.selectById(team.getKlassId());
        Integer klassSerial=null;
        if(klass!=null)
        klassSerial=classMapper.selectById(team.getKlassId()).getKlassSerial();
        team.setTeamSerial(serial);
        team.setKlassSerial(klassSerial);
        BigInteger teamId=teamDao.createTeam(team);
        if(teamDao.teamIsLegal(team.getCourseId().longValue(),team.getId().longValue())){
            teamMapper.updateStatus(team.getId(),1);
        }
        else {
            teamMapper.updateStatus(team.getId(),0);
        }
        return teamId;
    }

    @Override
    public void deleteTeam(BigInteger id){
        teamStudentMapper.removeTeam(id);
        teamMapper.deleteTeam(id);
        return;
    }
    @Override
    public void addTeamMember(BigInteger teamId,BigInteger studentId){
        teamDao.addTeamMember(teamId,studentId);
        Team team=teamMapper.queryTeamById(teamId);
        if(teamDao.teamIsLegal(team.getCourseId().longValue(),team.getId().longValue())){
            teamMapper.updateStatus(team.getId(),1);
        }
        else {
            teamMapper.updateStatus(team.getId(),0);
        }
        return;
    }
    @Override
    public void removeMemberOrTeam(BigInteger teamId,BigInteger studentId){
        teamDao.deleteMemberOrTeam(teamId,studentId);
        Team team=teamMapper.queryTeamById(teamId);
        if(team!=null) {
            if (teamDao.teamIsLegal(team.getCourseId().longValue(), team.getId().longValue())) {
                teamMapper.updateStatus(team.getId(), 1);
            } else {
                teamMapper.updateStatus(team.getId(), 0);
            }
        }
        return;
    }

    @Override
    public void sendTeamValid(TeamValidApplication teamValidApplication){
        teamDao.addTeamValid(teamValidApplication);
        return ;
    }

    @Override
    public  List<Team> getTeamIdByCourseId(BigInteger courseId){
        List<Team> teamIds=teamMapper.queryIdByCourseId(courseId);
        return teamIds;
    }

}
