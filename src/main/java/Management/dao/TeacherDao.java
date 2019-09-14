package Management.dao;

import Management.entity.Student;
import Management.entity.Teacher;
import Management.mapper.StudentMapper;
import Management.mapper.TeacherMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * @author Mazijin
 * @since 2018/12/16
 */
@Component
public class TeacherDao {

    @Autowired
    private TeacherMapper teacherMapper;

    public Teacher selectByAccount(String account){
        return teacherMapper.selectByAccount(account);
    }

    public void updatePasswordById( BigInteger id, String password){
        teacherMapper.updatePasswordById(id, password);
        return;
    }


    public Teacher selectById( BigInteger id){
        return teacherMapper.selectById(id);
    }

    public void updateEmail(BigInteger id,String email){
        teacherMapper.updateEmail(id, email);
        return;
    }
}
