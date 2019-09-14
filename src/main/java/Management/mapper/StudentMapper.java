package Management.mapper;

import Management.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
@Mapper
public interface StudentMapper  {

    public Student selectByAccount(@Param("account") String account);

    public Student selectByName(@Param("name") String name);

    public void updatePasswordById(@Param("id") BigInteger id,@Param("password") String password);

    public Student selectById(@Param("id") BigInteger id);

    public void deleteById(@Param("id") BigInteger id);

    public void updateEmail(@Param("id") BigInteger id,@Param("email") String email);

    public List<Student> selectAll();

    public void modifyInfo(@Param("student") Student student);

    public void resetPassword(@Param("id")BigInteger id);

    public void active(@Param("student")Student student);

    public void addStudent(@Param("student")Student student);

    public void updateStudent(@Param("student")Student student);

    //author li
    public List<Student> queryTeamMember(@Param("teamId")BigInteger teamId,
                                         @Param("leaderId")BigInteger leaderId);

    //author li
    public List<Student> querryNoTeam(@Param("courseId")BigInteger courseId);



    public void updateTeamStudent(@Param("student")Student student);

    public ArrayList<Student> getMembers(Long teamId);



}
