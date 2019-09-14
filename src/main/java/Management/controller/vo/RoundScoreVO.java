package Management.controller.vo;

import Management.entity.Round;
import Management.entity.RoundScore;
import Management.entity.SeminarScore;
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
public class RoundScoreVO {
    private BigInteger roundId;

    private Integer roundSerial;

    private BigInteger teamId;

    private String teamName;

    private BigDecimal totalScore;

    private BigDecimal presentationScore;

    private BigDecimal questionScore;

    private BigDecimal reportScore;

    private List<SeminarScore> seminarScores;

    public RoundScoreVO(){};

    public RoundScoreVO(RoundScore roundScore){
        this.roundId=roundScore.getRoundId();
        this.teamId=roundScore.getTeamId();
        this.totalScore=roundScore.getTotalScore();
        this.presentationScore=roundScore.getPresentationScore();
        this.reportScore=roundScore.getReportScore();
        this.questionScore=roundScore.getQuestionScore();
        this.seminarScores=roundScore.getSeminarScores();
    }

    public BigInteger getRoundId() {
        return roundId;
    }

    public void setRoundId(BigInteger roundId) {
        this.roundId = roundId;
    }

    public Integer getRoundSerial() {
        return roundSerial;
    }

    public void setRoundSerial(Integer roundSerial) {
        this.roundSerial = roundSerial;
    }

    public BigInteger getTeamId() {
        return teamId;
    }

    public void setTeamId(BigInteger teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
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

