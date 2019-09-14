package Management.domain.service;

import Management.controller.vo.CourseScoreVo;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/9 16:32
 * @Version 1.0
 */
public interface StudentScoreService {
    CourseScoreVo getScore(BigInteger CourseId,BigInteger studentId);

}
