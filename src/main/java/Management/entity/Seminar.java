package Management.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/11/30 19:54
 * @Version 1.0
 */
public class Seminar {
    private BigInteger id;

    private BigInteger courseId;

    private BigInteger roundId;

    private String seminarName;

    private String introduction;

    private Integer maxTeam;

    private Integer isVisible;

    private Integer seminarSerial;

    private String enrollStartTime;

    private String enrollEndTime;


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

    public Integer getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(Integer isVisible) {
        this.isVisible = isVisible;
    }

    public Integer getSeminarSerial() {
        return seminarSerial;
    }

    public void setSeminarSerial(Integer seminarSerial) {
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


}
