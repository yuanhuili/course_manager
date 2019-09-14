package Management.entity;

/**
 * @author Mazijin
 * @since 2018/12/31
 */
public class ScoreExcel {
    private String seminarName;

    private String teamName;

    private int presentationScore;

    private int reportScore;

    private int questionScore;

    public String getSeminarName() {
        return seminarName;
    }

    public void setSeminarName(String seminarName) {
        this.seminarName = seminarName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPresentationScore() {
        return presentationScore;
    }

    public void setPresentationScore(int presentationScore) {
        this.presentationScore = presentationScore;
    }

    public int getReportScore() {
        return reportScore;
    }

    public void setReportScore(int reportScore) {
        this.reportScore = reportScore;
    }

    public int getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(int questionScore) {
        this.questionScore = questionScore;
    }
}
