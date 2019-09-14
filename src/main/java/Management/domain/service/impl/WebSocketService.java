package Management.domain.service.impl;

import Management.entity.*;
import Management.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/25
 */
@Service
@Component
public class WebSocketService {
    @Autowired
    AttendanceMapper attendanceMapper;

    @Autowired
    ClassSeminarMapper classSeminarMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    StudentMapper studentMapper;

    public List<Attendance> selectByKlassSeminarId(BigInteger klassSeminarId) {
        return attendanceMapper.selectByKlassSeminarId(klassSeminarId);
    }

    public void startPresentation(BigInteger attendenceId) {
        attendanceMapper.startPresentation(attendenceId);
        attendanceMapper.startTwoPresentation(attendenceId);
    }

    public void endPresentation(BigInteger attendenceId) {
        attendanceMapper.endPresentation(attendenceId);
    }

    public List<Question> getQuestion(BigInteger attendenceId) {
        return questionMapper.selectByAttendance(attendenceId);

    }

    public void selectQuestion(BigInteger questionId) {
        questionMapper.selectQuestion(questionId);
    }

    public String getPresent(BigInteger attendenceId) {
        Attendance attendance=attendanceMapper.selectById(attendenceId);
        Team team=teamMapper.queryTeamById(attendance.getTeamId());
        String name=team.getKlassSerial().toString()+"-"+team.getTeamSerial().toString();
        return name;
    }

    public String getQuestionInfo(BigInteger questionId) {
        Question question=questionMapper.selectById(questionId);
        Team team=teamMapper.queryTeamById(question.getTeamId());
        Student student=studentMapper.selectById(question.getStudentId());
        String Info=team.getKlassSerial().toString()+"-"+team.getTeamSerial().toString()+"的"+student.getStudentName()
                +"同学";
        return Info;
    }

    public void endSeminar(BigInteger classSeminarId) {
        KlassSeminar klassSeminar=classSeminarMapper.selectById(classSeminarId);
        klassSeminar.setSeminarStatus(2);
        classSeminarMapper.updateStatus(klassSeminar);
        attendanceMapper.endAllPresentation(classSeminarId);
    }
}
