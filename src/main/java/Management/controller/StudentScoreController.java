package Management.controller;

import Management.controller.vo.CourseScoreVo;
import Management.domain.service.StudentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/9 16:29
 * @Version 1.0
 */
@CrossOrigin
@RestController()
@RequestMapping("/student")
public class StudentScoreController {
    @Autowired
    StudentScoreService studentService;

    @PostMapping(value="/queryScoreById")
    public CourseScoreVo getScore(@RequestParam("courseId")BigInteger courseId, @RequestParam("studentId")BigInteger studentId){
        CourseScoreVo courseScore=studentService.getScore(courseId,studentId);
        return  courseScore;
    }
}
