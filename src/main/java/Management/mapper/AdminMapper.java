package Management.mapper;

import Management.entity.Admin;
import Management.entity.Attendance;
import Management.entity.Student;
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
public interface AdminMapper extends BaseMapper<User> {

    public Admin selectByAccount(@Param("account") String account);

    List<Attendance> selectByKlassSeminarId(@Param("klassSeminarId") BigInteger klassSeminarId);

}
