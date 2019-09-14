package Management.controller.vo;

import Management.entity.Student;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/23 16:25
 * @Version 1.0
 */
public class TeamVO {
    private BigInteger id;

    private String teamName;

    private BigInteger klassId;

    private BigInteger courseId;

    private String courseName;

    private String classNmae;

    private Integer teamSerial;

    private Integer klassSerial;

    private Student Leader;

    private List<Student> members;



    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getKlassSerial() {
        return klassSerial;
    }

    public void setKlassSerial(Integer klassSerial) {
        this.klassSerial = klassSerial;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassNmae() {
        return classNmae;
    }

    public void setClassNmae(String classNmae) {
        this.classNmae = classNmae;
    }

    public Integer getTeamSerial() {
        return teamSerial;
    }

    public void setTeamSerial(Integer teamSerial) {
        this.teamSerial = teamSerial;
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
