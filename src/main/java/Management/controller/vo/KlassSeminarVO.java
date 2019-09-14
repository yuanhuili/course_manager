package Management.controller.vo;

import java.math.BigInteger;

/**
 * @author Mazijin
 * @since 2018/12/25
 */
public class KlassSeminarVO {
    private BigInteger id;

    private BigInteger klassId;

    private BigInteger courseId;

    private BigInteger seminarId;

    private String reportDDL;

    private String seminarStatus;

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

    public BigInteger getSeminarId() {
        return seminarId;
    }

    public void setSeminarId(BigInteger seminarId) {
        this.seminarId = seminarId;
    }

    public String getReportDDL() {
        return reportDDL;
    }

    public void setReportDDL(String reportDDL) {
        this.reportDDL = reportDDL;
    }

    public String getSeminarStatus() {
        return seminarStatus;
    }

    public void setSeminarStatus(String seminarStatus) {
        this.seminarStatus = seminarStatus;
    }

    public BigInteger getCourseId() {
        return courseId;
    }

    public void setCourseId(BigInteger courseId) {
        this.courseId = courseId;
    }
}
