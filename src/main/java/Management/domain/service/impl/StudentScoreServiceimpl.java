package Management.domain.service.impl;

import Management.controller.vo.CourseScoreVo;
import Management.mapper.StudentScoreMapper;
import Management.domain.service.StudentScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/9 19:12
 * @Version 1.0
 */
@Service
public class StudentScoreServiceimpl implements StudentScoreService {
    @Autowired
    protected StudentScoreMapper studentScoreMapper;

    @Override
    public CourseScoreVo getScore(BigInteger courseId,BigInteger studentId){
        CourseScoreVo courseScore=studentScoreMapper.queryScoreById(courseId,studentId);
        return courseScore;
    }
}
