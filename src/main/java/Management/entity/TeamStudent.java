package Management.entity;

import sun.nio.cs.ext.Big5;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/25 17:47
 * @Version 1.0
 */
public class TeamStudent {
    private BigInteger teamId;
    private BigInteger studentId;

    public BigInteger getTeamId() {
        return teamId;
    }

    public void setTeamId(BigInteger teamId) {
        this.teamId = teamId;
    }

    public BigInteger getStudentId() {
        return studentId;
    }

    public void setStudentId(BigInteger studentId) {
        this.studentId = studentId;
    }
}
