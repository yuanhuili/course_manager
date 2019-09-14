package Management.entity;

import javafx.util.converter.TimeStringConverter;

import java.math.BigInteger;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/13 19:07
 * @Version 1.0
 */
public class KlassSeminar {
    private BigInteger id;

    private BigInteger klassId;

    private BigInteger seminarId;

    private String reportDDL;

    private Integer seminarStatus;

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

    public Integer getSeminarStatus() {
        return seminarStatus;
    }

    public void setSeminarStatus(Integer seminarStatus) {
        this.seminarStatus = seminarStatus;
    }
}
