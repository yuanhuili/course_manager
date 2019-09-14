package Management.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
/**
 * @Author: yuanhuili
 * @Date: 2018/12/9 18:01
 * @Version 1.0
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoundScore {
    private BigInteger roundId;

    private BigInteger teamId;

    private BigDecimal totalScore;

    private BigDecimal presentationScore;

    private BigDecimal questionScore;

    private BigDecimal reportScore;

    private List<SeminarScore> seminarScores;

    public BigInteger getRoundId() {
        return roundId;
    }

    public void setRoundId(BigInteger roundId) {
        this.roundId = roundId;
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

    public BigDecimal getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(BigDecimal questionScore) {
        this.questionScore = questionScore;
    }

    public BigDecimal getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(BigDecimal presentationScore) {
        this.presentationScore = presentationScore;
    }

    public BigDecimal getReportScore() {
        return reportScore;
    }

    public void setReportScore(BigDecimal reportScore) {
        this.reportScore = reportScore;
    }

    public List<SeminarScore> getSeminarScores() {
        return seminarScores;
    }

    public void setSeminarScores(List<SeminarScore> seminarScores) {
        this.seminarScores = seminarScores;
    }
}
