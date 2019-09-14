package Management.domain.service.impl;

import Management.domain.utils.DataUtil;
import Management.entity.KlassSeminar;
import Management.entity.KlassTeam;
import Management.entity.Question;
import Management.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/24
 */
@Service
public class QuestionService {
    @Autowired
    ClassSeminarMapper classSeminarMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    KlassTeamMapper klassTeamMapper;

    @Autowired
    TeamStudentMapper teamStudentMapper;


    public List<Question> selectQuestion(BigInteger classId, BigInteger seminarId) {
        BigInteger bigInteger=classSeminarMapper.selectId(classId,seminarId);
        List<Question> questions=questionMapper.selectByClassSeminarId(bigInteger);
        return questions;
    }

    public BigInteger addQuestion(BigInteger classSeminarId, BigInteger attendanceId, BigInteger studentId) {
        KlassSeminar klassSeminar=classSeminarMapper.selectById(classSeminarId);
        List<KlassTeam> klassTeams=klassTeamMapper.queryByKlassId(klassSeminar.getKlassId());
        BigInteger teamId=new BigInteger("0");
        for(KlassTeam klassTeam:klassTeams) {
            if (DataUtil.isNotEmpty(teamStudentMapper.selectById(klassTeam.getTeamId(), studentId))) {
                teamId = klassTeam.getTeamId();
            }
        }
        Question question=new Question();
        question.setAttendanceId(attendanceId);
        question.setKlassSeminarid(classSeminarId);
        question.setTeamId(teamId);
        question.setStudentId(studentId);
        questionMapper.addQuestion(question);
        return question.getId();
    }

    public void updateQuestion(Question question) {
        questionMapper.updateQuestion(question);
        return;
    }
}
