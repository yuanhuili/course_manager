package Management.controller.vo;

import Management.entity.Attendance;
import Management.entity.Question;

import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/25
 */
public class QuestionAndAttendanceVO {
    private List<Attendance> attendances;
    private List<Question> questions;

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public void setAttendances(List<Attendance> attendances) {
        this.attendances = attendances;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
