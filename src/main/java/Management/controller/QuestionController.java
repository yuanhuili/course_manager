package Management.controller;

import Management.controller.vo.QuestionVO;
import Management.controller.vo.SeminarScoreVO;
import Management.domain.service.impl.QuestionService;
import Management.domain.service.impl.SeminarService;
import Management.entity.Question;
import Management.entity.SeminarScore;
import Management.entity.Team;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/11
 */
@CrossOrigin
@RestController()
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @Autowired
    SeminarService seminarService;
    @GetMapping("/{seminarId}/class/{classId}")
    public List<QuestionVO> getSeminarScore(@PathVariable("seminarId") BigInteger seminarId,
                                           @PathVariable("classId") BigInteger classId){
        List<QuestionVO> questionVOS=new ArrayList<QuestionVO>();
        List<Question> questions=questionService.selectQuestion(classId,seminarId);
        questions.forEach(question -> {
            QuestionVO questionVO=new QuestionVO();
            BeanUtils.copyProperties(question,questionVO);
            Team team=seminarService.queryTeam(question.getTeamId());
            questionVO.setTeam(team);
            questionVOS.add(questionVO);
        });
        return questionVOS;
    }

    @PostMapping("/{classSeminarId}/attendance/{attendanceId}")
    public BigInteger question(@PathVariable("classSeminarId") BigInteger classSeminarId,
                                      @PathVariable("attendanceId") BigInteger attendanceId,
                                      @RequestAttribute("userId") BigInteger studentId
                                      ){
        BigInteger bigInteger=questionService.addQuestion(classSeminarId,attendanceId,studentId);
        return bigInteger;
    }

    @PutMapping("/{questionId}")
    public Boolean updateSeminarScore(@PathVariable("questionId") BigInteger questionId,
                                      @RequestBody() Question question
                                     ){
        question.setId(questionId);
        questionService.updateQuestion(question);
        return true;
    }
}
