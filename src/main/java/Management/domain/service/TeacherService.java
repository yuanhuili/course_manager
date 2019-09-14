package Management.domain.service;


import Management.entity.Teacher;
import Management.entity.Commission;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
public interface TeacherService {

    List<Teacher> selectAll();

    Teacher selectByIdentity(String identity);

    Teacher modifyInfo(Teacher teacher);

    Teacher resetPassword(BigInteger teacherId);//

    Boolean deleteTeacher(BigInteger teacherId);

    Teacher activateTeacher(Teacher teacher);

    Teacher add(Teacher teacher);

    //li
    Teacher querryByCourseId(BigInteger courseId);

    Commission all(BigInteger userId);

    Commission queryHistoryMessage(BigInteger userId);
}
