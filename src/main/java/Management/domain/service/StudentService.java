package Management.domain.service;


import Management.entity.Student;
import Management.entity.User;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
public interface StudentService {

    List<Student> selectAll();

    Student selectByIdentity(String identity);

    Student modifyInfo(Student student);

    Student resetPassword(BigInteger studentId);

    Boolean deleteStudent(BigInteger studentId);

    Student activateStudent(Student student);
}
