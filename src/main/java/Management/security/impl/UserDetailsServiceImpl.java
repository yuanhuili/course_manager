package Management.security.impl;

import Management.mapper.AdminMapper;
import Management.mapper.StudentMapper;
import Management.mapper.TeacherMapper;
import Management.mapper.UserMapper;
import Management.domain.utils.DataUtil;
import Management.entity.Admin;
import Management.entity.Student;
import Management.entity.Teacher;
import Management.security.UserDetailsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Management.entity.User;

/**
 * @author Mazijin
 * @since 2018/12/11
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public User loadUserByAccount(String account) throws UsernameNotFoundException {
        User user=new User();
        Admin admin=adminMapper.selectByAccount(account);
        Student student=studentMapper.selectByAccount(account);
        Teacher teacher=teacherMapper.selectByAccount(account);
        if(DataUtil.isNotEmpty(admin)){
            BeanUtils.copyProperties(admin,user);
            user.setRole("admin");
        }
        else if(DataUtil.isNotEmpty(student)){
            BeanUtils.copyProperties(student,user);
            user.setName(student.getStudentName());
            user.setRole("student");
        }
        else if(DataUtil.isNotEmpty(teacher)){
            BeanUtils.copyProperties(teacher,user);
            user.setName(teacher.getTeacherName());
            user.setRole("teacher");
        }
        else if(DataUtil.isEmpty(user)){
            throw new UsernameNotFoundException("");
        }
        return user;
    }
}
