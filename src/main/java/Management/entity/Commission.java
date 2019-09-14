package Management.entity;

import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/26
 */
public class Commission {
    List<TeamValidApplications> teamValidApplications;
    List<ShareTeamApplications> shareTeamApplications;

    public List<ShareTeamApplications> getShareTeamApplications() {
        return shareTeamApplications;
    }

    public void setShareTeamApplications(List<ShareTeamApplications> shareTeamApplications) {
        this.shareTeamApplications = shareTeamApplications;
    }

    public List<TeamValidApplications> getTeamValidApplications() {
        return teamValidApplications;
    }

    public void setTeamValidApplications(List<TeamValidApplications> teamValidApplications) {
        this.teamValidApplications = teamValidApplications;
    }
}
