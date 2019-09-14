package Management.controller;

import Management.controller.vo.LoginVO;
import Management.controller.vo.QuestionVO;
import Management.domain.service.impl.AttendanceService;
import Management.domain.service.impl.WebSocketService;
import Management.entity.Attendance;
import Management.entity.KlassSeminar;
import Management.entity.Question;
import Management.webSocket.WebSocketServer;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/11
 */
@CrossOrigin
@RestController()
@RequestMapping("/webSocket")
public class WebSocketController {
    @Autowired
    WebSocketServer webSocketServer;
    @Autowired
    WebSocketService webSocketService;
    @Autowired
    AttendanceService attendanceService;


    @PostMapping(value = "/presentation/{attendenceId}/start")
    public Boolean onePresent(@PathVariable("attendenceId") BigInteger attendenceId) {
        webSocketService.startPresentation(attendenceId);
        try {
            String name=webSocketService.getPresent(attendenceId);
            List<Attendance> attendances=attendanceService.selectAllAttendance(attendenceId);
            webSocketServer.sendInfo(new Gson().toJson("请"+name + "开始展示"));
            webSocketServer.sendInfo(new Gson().toJson(attendances));
        } catch (Exception e) {
        }
        return true;
    }

    @PostMapping(value = "/presentation/{attendenceId}/end")
    public Boolean endPresent(@PathVariable("attendenceId") BigInteger attendenceId) {
        webSocketService.endPresentation(attendenceId);
        try {
            String name=webSocketService.getPresent(attendenceId);
            webSocketServer.sendInfo(new Gson().toJson(name + "结束展示"));
        } catch (Exception e) {
        }
        return true;
    }

    @PostMapping(value = "/presentation/{attendenceId}/question")
    public Boolean questionList(@PathVariable("attendenceId") BigInteger attendenceId) {

        List<Question> questions= webSocketService.getQuestion(attendenceId);
        try {
            webSocketServer.sendInfo(new Gson().toJson(questions));
        } catch (Exception e) {
        }
        return true;
    }

    @PostMapping(value = "/presentation/{questionId}",produces = "application/json; charset=utf-8")
    public Boolean selectQuestion(@PathVariable("questionId") BigInteger questionId) {
        webSocketService.selectQuestion(questionId);
        try {
            //teamName studentId
            String name=webSocketService.getQuestionInfo(questionId);
            webSocketServer.sendInfo(new Gson().toJson("请"+name+"提问"));
        } catch (Exception e) {
        }
        return true;
    }
    @PutMapping("/endSeminar/{classSeminarId}")
    public Boolean endSeminar(@PathVariable("classSeminarId") BigInteger classSeminarId){
        try {
            //teamName studentId
            webSocketService.endSeminar(classSeminarId);
            webSocketServer.sendInfo(new Gson().toJson("false"));
        } catch (Exception e) {
        }

        return true;
    }


}
