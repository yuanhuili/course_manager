package Management.entity;

import com.sun.imageio.plugins.common.BogusColorSpace;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/13 19:15
 * @Version 1.0
 */
public class SeminarScoreTable {
    private BigInteger klassSeminarId;

    private BigInteger teamId;

    private BigDecimal totalScore;

    private BigDecimal presentationScore;

    private BigDecimal questionScore;

    private BigDecimal reportScore;

    public BigInteger getKlassSeminarId() {
        return klassSeminarId;
    }

    public void setKlassSeminarId(BigInteger klassSeminarId) {
        this.klassSeminarId = klassSeminarId;
    }

    public BigInteger getTeamId() {
        return teamId;
    }

    public void setTeamId(BigInteger teamId) {
        this.teamId = teamId;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public BigDecimal getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(BigDecimal presentationScore) {
        this.presentationScore = presentationScore;
    }

    public BigDecimal getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(BigDecimal questionScore) {
        this.questionScore = questionScore;
    }

    public BigDecimal getReportScore() {
        return reportScore;
    }

    public void setReportScore(BigDecimal reportScore) {
        this.reportScore = reportScore;
    }
}
