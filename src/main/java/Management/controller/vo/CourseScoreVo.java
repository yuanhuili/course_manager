package Management.controller.vo;

import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/9 20:31
 * @Version 1.0
 */
public class CourseScoreVo {
    private String courseName;

    private List<RoundScoreVO> roundScores;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<RoundScoreVO> getRoundScores() {
        return roundScores;
    }

    public void setRoundScores(List<RoundScoreVO> roundScores) {
        this.roundScores = roundScores;
    }
}
