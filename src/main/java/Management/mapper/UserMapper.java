package Management.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import Management.entity.User;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    public User queryById(@Param("id") BigInteger id);

    public void updateFirst(@Param("id") BigInteger id,@Param("password") String password);

    public User selectByName(@Param("account") String account);


}
