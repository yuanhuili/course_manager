package Management.controller.vo;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/11/30 19:54
 * @Version 1.0
 */
public class SeminarVO {
    private BigInteger id;

    private BigInteger courseId;

    private BigInteger roundId;

    private String seminarName;

    private String introduction;

    private Integer maxTeam;

    private Integer isVisble;

    private Integer seminarSerial;

    private String enrollStartTime;

    private String enrollEndTime;

    private Integer status;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }

    public BigInteger getRoundId() {
        return roundId;
    }

    public void setRoundId(BigInteger roundId) {
        this.roundId = roundId;
    }

    public String getSeminarName() {
        return seminarName;
    }

    public void setSeminarName(String seminarName) {
        this.seminarName = seminarName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getMaxTeam() {
        return maxTeam;
    }

    public void setMaxTeam(Integer maxTeam) {
        this.maxTeam = maxTeam;
    }

    public Integer getIsVisble() {
        return isVisble;
    }

    public void setIsVisble(Integer isVisble) {
        this.isVisble = isVisble;
    }

    public void setSeminarSerial(Integer seminarSerial) {
        this.seminarSerial = seminarSerial;
    }


    public Integer getSeminarSerial() {
        return seminarSerial;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEnrollStartTime() {
        return enrollStartTime;
    }

    public void setEnrollStartTime(String enrollStartTime) {
        this.enrollStartTime = enrollStartTime;
    }

    public String getEnrollEndTime() {
        return enrollEndTime;
    }

    public void setEnrollEndTime(String enrollEndTime) {
        this.enrollEndTime = enrollEndTime;
    }

}
