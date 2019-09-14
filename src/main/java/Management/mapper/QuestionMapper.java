package Management.mapper;

import Management.entity.Admin;
import Management.entity.Question;
import Management.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
@Mapper
public interface QuestionMapper  {
    List<Question> selectByClassSeminarId(@Param("klassSeminarId") BigInteger klassSeminarId);

    Integer addQuestion(@Param("question") Question question);

    void updateQuestion(@Param("question")Question question);

    void selectQuestion(@Param("questionId")BigInteger questionId);

    Question selectById(@Param("questionId")BigInteger questionId);

    List<Question> selectByAttendance(@Param("attendenceId")BigInteger attendenceId);
}
