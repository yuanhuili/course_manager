package Management.controller.vo;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/25 13:34
 * @Version 1.0
 */
public class TeamValidVO {


    private BigInteger teamId;

    private BigInteger courseId;

    private String reason;


    public BigInteger getTeamId() {
        return teamId;
    }

    public void setTeamId(BigInteger teamId) {
        this.teamId = teamId;
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


}
