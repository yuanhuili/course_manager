package Management.domain.service.impl;

import Management.dao.StudentDao;
import Management.dao.TeacherDao;
import Management.domain.service.StudentService;
import Management.domain.service.UserService;
import Management.domain.utils.DataUtil;
import Management.domain.utils.FlywayCodeEnum;
import Management.entity.Student;
import Management.entity.Teacher;
import Management.entity.User;
import Management.mapper.StudentMapper;
import Management.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<Student> selectAll() {
        List<Student> list=studentMapper.selectAll();
        if (DataUtil.isNotEmpty(list)){
            return list;
        }
        return null;
    }

    @Override
    public Student selectByIdentity(String identity) {
        Student student=studentMapper.selectByAccount(identity);
        if(DataUtil.isNotEmpty(student)){
            return student;
        }else {
            student=studentMapper.selectByName(identity);
            return student;
        }
    }

    @Override
    public Student modifyInfo(Student student) {
//        try {
            studentMapper.modifyInfo(student);
            Student studentInfo=studentMapper.selectByAccount(student.getAccount());
            return studentInfo;
//        }catch (Exception e){
//            throw FlywayCodeEnum.DB_UPDATE_ERROR.getException();
//        }
    }

    @Override
    public Student resetPassword(BigInteger studentId) {
        studentMapper.resetPassword(studentId);
        Student student=studentMapper.selectById(studentId);
        return student;
    }

    @Override
    public Boolean deleteStudent(BigInteger studentId) {
        try{
            studentMapper.deleteById(studentId);

        }catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public Student activateStudent(Student student) {
        studentMapper.active(student);
        Student studentInfo=studentMapper.selectById(student.getId());
        return studentInfo;

    }
}
