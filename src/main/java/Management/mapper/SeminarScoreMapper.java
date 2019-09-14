package Management.mapper;

import Management.entity.Admin;
import Management.entity.SeminarScore;
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
public interface SeminarScoreMapper extends BaseMapper<User> {

    SeminarScore querySeminarScore(@Param("id")BigInteger id,@Param("teamId") BigInteger teamId);

    void updateSeminarScore(@Param("seminarScore") SeminarScore seminarScore);

    List<SeminarScore> selectsById(@Param("id")BigInteger id);

    void updatePresentationScore(@Param("seminarScore")SeminarScore seminarScore);
}
