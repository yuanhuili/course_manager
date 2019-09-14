package Management.entity;

import com.baomidou.mybatisplus.annotations.TableField;

import java.math.BigInteger;
import java.sql.Time;
import java.util.Date;

/**
 * @Author: yuanhuili
 * @Date: 2018/11/30 19:39
 * @Version 1.0
 */
public class Presentation {
    @TableField(value="id")
    private BigInteger id;

    @TableField(value="gmtCreate")
    private Date gmtCreate;

    @TableField(value="gmtModified")
    private  Date gmtModified;

    @TableField(value="ppt")
    private String ppt;

    @TableField(value="presentationScore")
    private float presentationScore;

    @TableField(value="time")
    private Time time;

    @TableField(value="hasPresented")
    private int hasPresented;

    @TableField(value="teamId")
    private BigInteger teamId;

    @TableField(value="seminarId")
    private BigInteger seminarId;

    @TableField(value="presentationOrder")
    private int presentationOrder;

    @TableField(value="report")
    private String report;

    @TableField(value="reportScore")
    private  float reportScore;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getPpt() {
        return ppt;
    }

    public void setPpt(String ppt) {
        this.ppt = ppt;
    }

    public float getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(float presentationScore) {
        this.presentationScore = presentationScore;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public int getHasPresented() {
        return hasPresented;
    }

    public void setHasPresented(int hasPresented) {
        this.hasPresented = hasPresented;
    }

    public BigInteger getTeamId() {
        return teamId;
    }

    public void setTeamId(BigInteger teamId) {
        this.teamId = teamId;
    }

    public BigInteger getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(BigInteger seminarId) {
        this.seminarId = seminarId;
    }

    public int getPresentationOrder() {
        return presentationOrder;
    }

    public void setPresentationOrder(int presentationOrder) {
        this.presentationOrder = presentationOrder;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public float getReportScore() {
        return reportScore;
    }

    public void setReportScore(float reportScore) {
        this.reportScore = reportScore;
    }
}
