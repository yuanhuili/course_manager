package Management.entity;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/11 21:01
 * @Version 1.0
 */
public class TeamRequest {
    private int id;

    private Course course;

    private Klass aclass;//不应用class

    private Team team;

    private String leaderName;

    private String requestType;//enum{teamValid,teamMember}

    private String reason;

    private String handleType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Klass getAclass() {
        return aclass;
    }

    public void setAclass(Klass aclass) {
        this.aclass = aclass;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getHandleType() {
        return handleType;
    }

    public void setHandleType(String handleType) {
        this.handleType = handleType;
    }
}
