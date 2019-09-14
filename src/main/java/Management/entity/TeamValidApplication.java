package Management.entity;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/13 19:18
 * @Version 1.0
 */
public class TeamValidApplication {

    private BigInteger id;

    private BigInteger teamId;

    private BigInteger teacherId;

    private String reason;

    private Integer status;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getTeamId() {
        return teamId;
    }

    public void setTeamId(BigInteger teamId) {
        this.teamId = teamId;
    }

    public BigInteger getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(BigInteger teacherId) {
        this.teacherId = teacherId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
