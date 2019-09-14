package Management.domain.service.impl;

import Management.dao.StudentDao;
import Management.dao.TeacherDao;
import Management.mapper.StudentMapper;
import Management.mapper.TeacherMapper;
import Management.mapper.UserMapper;
import Management.domain.service.UserService;

import Management.domain.utils.DataUtil;
import Management.domain.utils.FlywayCodeEnum;
import Management.entity.Student;
import Management.entity.Teacher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import java.math.BigInteger;

import Management.entity.User;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String find_message = "Hello, welcome to use the classroom management system. " +
            "You are using the password retrieval function. Please do not reveal your password to others.Your password" +
            "is: ";

    @Autowired
    protected UserMapper userMapper;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    JavaMailSender jms;

    @Override
    public Boolean sendMailPassword(String account) {
        Student student=studentDao.selectByAccount(account);
        if(DataUtil.isNotEmpty(student)){
            try{
                this.sendMail(student.getEmail(),find_message+student.getPassword());
            }catch (Exception e){
                throw FlywayCodeEnum.SEND_ERROR.getException();
            }
        }else{
            Teacher teacher=teacherDao.selectByAccount(account);
            try{
            this.sendMail(teacher.getEmail(),find_message+student.getPassword());
            }catch (Exception e){
                throw FlywayCodeEnum.SEND_ERROR.getException();
        }
        }
        return true;
    }


    @Override
    public Boolean changePassword(User user) {
        if(user.getRole().equals("student")){
            studentDao.updatePasswordById(user.getId(),user.getPassword());

        }
            else if(user.getRole().equals("teacher")){
            teacherDao.updatePasswordById(user.getId(),user.getPassword());

        }
        return true;
    }

    @Override
    public User getInfo(User user) {
        User user1=new User();
        if(user.getRole().equals("student")){
            Student student=studentDao.selectById(user.getId());
            BeanUtils.copyProperties(student,user1);
            user1.setRole("student");
            user1.setName(student.getStudentName());
        }else if(user.getRole().equals("teacher")){
            Teacher teacher=teacherDao.selectById(user.getId());
            BeanUtils.copyProperties(teacher,user1);
            user1.setRole("teacher");
            user1.setName(teacher.getTeacherName());
        }
        return user1;
    }

    @Override
    public Boolean updateEmail(User user) {
        if(user.getRole().equals("student")){
            studentDao.updateEmail(user.getId(),user.getEmail());
        }else if(user.getRole().equals("teacher")){
            teacherDao.updateEmail(user.getId(),user.getEmail());
        }
        return true;
    }

    @Override
    public Boolean updateFirst(BigInteger id,User user) {
        userMapper.updateFirst(id,user.getPassword());
        return true;
    }

//    @Override
//    public UserVO getById(BigInteger id) {
//        User user=userMapper.queryById(id);
//        return user;
//    }

    @Override
    public void sendMail(String email,String text) {

        //建立邮件消息
        SimpleMailMessage mainMessage = new SimpleMailMessage();
        //发送者
        mainMessage.setFrom("li18805925575@163.com");
        //接收者
        mainMessage.setTo(email);
        //发送的标题
        mainMessage.setSubject("flyway");
        //发送的内容
        //Random random=new Random();
//        String verifyCode=String.valueOf(random.nextInt(10000));
        mainMessage.setText(text);
        jms.send(mainMessage);

    }

}
