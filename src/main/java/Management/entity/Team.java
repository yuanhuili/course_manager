package Management.entity;

import org.apache.catalina.LifecycleState;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/11/30 20:07
 * @Version 1.0
 */

public class Team {
   private BigInteger id;

   private BigInteger klassId;

   private BigInteger courseId;

   private BigInteger leaderId;

   private String teamName;

   private Integer teamSerial;

   private Integer klassSerial;

   private Integer status;

    private Student Leader;

    private List<Student> members;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getKlassId() {
        return klassId;
    }

    public void setKlassId(BigInteger klassId) {
        this.klassId = klassId;
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public BigInteger getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(BigInteger leaderId) {
        this.leaderId = leaderId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getTeamSerial() {
        return teamSerial;
    }

    public void setTeamSerial(int teamSerial) {
        this.teamSerial = teamSerial;
    }

    public void setTeamSerial(Integer teamSerial) {
        this.teamSerial = teamSerial;
    }

    public Integer getKlassSerial() {
        return klassSerial;
    }

    public void setKlassSerial(Integer klassSerial) {
        this.klassSerial = klassSerial;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Student getLeader() {
        return Leader;
    }

    public void setLeader(Student leader) {
        Leader = leader;
    }

    public List<Student> getMembers() {
        return members;
    }

    public void setMembers(List<Student> members) {
        this.members = members;
    }
}
