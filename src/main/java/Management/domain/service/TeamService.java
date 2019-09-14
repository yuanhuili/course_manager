package Management.domain.service;

import Management.entity.Team;
import Management.entity.TeamValidApplication;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/24 15:52
 * @Version 1.0
 */
public interface TeamService {
    public Team querryTeamById(BigInteger id);
    public void updateTeam(Team team);
    public void deleteTeam(BigInteger id);
    void addTeamMember(BigInteger teamId,BigInteger studentId);
    void removeMemberOrTeam(BigInteger teamId,BigInteger studentId);
    void sendTeamValid(TeamValidApplication teamValidApplication);
    BigInteger createTeam(Team team);
    List<Team> getTeamIdByCourseId(BigInteger courseId);
}
