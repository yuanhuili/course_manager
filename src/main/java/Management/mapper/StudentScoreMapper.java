package Management.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

import Management.controller.vo.CourseScoreVo;
/**
 * @Author: yuanhuili
 * @Date: 2018/12/9 19:13
 * @Version 1.0
 */
@Mapper
public interface StudentScoreMapper {//
    public CourseScoreVo queryScoreById(@RequestParam("courseId")BigInteger courseId,@RequestParam("courseId")BigInteger StudentId);
}
