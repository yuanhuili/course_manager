package Management.mapper;

import Management.entity.*;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import javax.swing.plaf.basic.BasicGraphicsUtils;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/20 22:51
 * @Version 1.0
 */
@Mapper
public interface RoundMapper extends BaseMapper<Round> {
    public List<Round> querryRoundByCourseId(@Param("courseId")BigInteger courseId);
    public void deleteRound(@Param("courseId")BigInteger courseId);
    void addRound(@Param("round")Round round);
    BigInteger selectByRound(@Param("round")Round round);
    public Round querryById(@Param("id")BigInteger id);
    public void insertRound(@Param("round")Round round);
    public void updateRound(@Param("round")Round round);


}
