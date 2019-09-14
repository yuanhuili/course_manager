package Management.entity;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/11/30 19:52
 * @Version 1.0
 */
public class Round {
    private BigInteger id;

    private BigInteger courseId;

    private Integer roundSerial;

    private Integer presentationScoreMethod;//本轮展示分数计算方法

    private Integer reportScoreMethod;

    private Integer questionScoreMethod;

    List<KlassRound> klassRounds;

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

    public Integer getRoundSerial() {
        return roundSerial;
    }

    public void setRoundSerial(Integer roundSerial) {
        this.roundSerial = roundSerial;
    }

    public Integer getPresentationScoreMethod() {
        return presentationScoreMethod;
    }

    public void setPresentationScoreMethod(Integer presentationScoreMethod) {
        this.presentationScoreMethod = presentationScoreMethod;
    }

    public Integer getReportScoreMethod() {
        return reportScoreMethod;
    }

    public void setReportScoreMethod(Integer reportScoreMethod) {
        this.reportScoreMethod = reportScoreMethod;
    }

    public Integer getQuestionScoreMethod() {
        return questionScoreMethod;
    }

    public void setQuestionScoreMethod(Integer questionScoreMethod) {
        this.questionScoreMethod = questionScoreMethod;
    }

    public List<KlassRound> getKlassRounds() {
        return klassRounds;
    }

    public void setKlassRounds(List<KlassRound> klassRounds) {
        this.klassRounds = klassRounds;
    }

}
