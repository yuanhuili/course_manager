package Management.controller.vo;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/11/30 19:54
 * @Version 1.0
 */
public class ClassSeminarVO {
    private BigInteger id;

    private BigInteger courseId;

    private BigInteger roundId;

    private String seminarName;

    private String introduction;

    private int maxTeam;

    private int isVisble;

    private int seminarSerial;

    private String enrollStartTime;

    private String enrollEndTime;

    private int status;

    private String reportDDL;

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

    public int getMaxTeam() {
        return maxTeam;
    }

    public void setMaxTeam(int maxTeam) {
        this.maxTeam = maxTeam;
    }

    public int getIsVisble() {
        return isVisble;
    }

    public void setIsVisble(int isVisble) {
        this.isVisble = isVisble;
    }

    public int getSeminarSerial() {
        return seminarSerial;
    }

    public void setSeminarSerial(int seminarSerial) {
        this.seminarSerial = seminarSerial;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getReportDDL() {
        return reportDDL;
    }

    public void setReportDDL(String reportDDL) {
        this.reportDDL = reportDDL;
    }
}
