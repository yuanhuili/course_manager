package Management.mapper;

import Management.entity.Student;
import Management.entity.Teacher;
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
public interface TeacherMapper extends BaseMapper<User> {
//
    public Teacher selectByAccount(@Param("account") String account);

    public Teacher selectByName(@Param("name") String name);

    public void updatePasswordById(@Param("id") BigInteger id,@Param("password") String password);

    public Teacher selectById(@Param("id") BigInteger id);

    public void updateEmail(@Param("id") BigInteger id,@Param("email") String email);

    public void add(@Param("teacher")Teacher teacher);

    List<Teacher> selectAll();

    void modifyInfo(@Param("teacher")Teacher teacher);

    void resetPassword(@Param("id")BigInteger teacherId);

    void deleteById(@Param("id") BigInteger id);

    void active(@Param("teacher") Teacher teacher);

    //li
    Teacher querryByCourseId(@Param("courseId")BigInteger courseId);
}
