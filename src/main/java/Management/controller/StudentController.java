package Management.controller;

import Management.controller.vo.StudentVO;
import Management.domain.service.StudentService;
import Management.entity.Student;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/11
 */
@CrossOrigin
@RestController()
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @GetMapping()
    public List<StudentVO> getAll(){
        List<StudentVO> studentVOS=new ArrayList<StudentVO>();
        List<Student> students=studentService.selectAll();
        students.forEach(student -> {
            StudentVO studentVO=new StudentVO();
            BeanUtils.copyProperties(student,studentVO);
            studentVOS.add(studentVO);
                }
        );
        return studentVOS;
    }

    @GetMapping(value = "/searchStudent")
    public StudentVO searchStudent(@RequestParam("identity")String identity){
        StudentVO studentVO=new StudentVO();
        Student student=studentService.selectByIdentity(identity);
        BeanUtils.copyProperties(student,studentVO);
        return studentVO;
    }

    @PutMapping(value = "/{studentId}/information")
    public StudentVO modifyStudentInfo(@PathVariable("studentId")BigInteger studentId, @RequestBody Student student){
        student.setId(studentId);
        Student studentInfo=studentService.modifyInfo(student);
        StudentVO studentVO=new StudentVO();
        BeanUtils.copyProperties(studentInfo,studentVO);
        return studentVO;
    }

    @PutMapping(value = "/{studentId}/password")
    public StudentVO resetStudentPassword(@PathVariable("studentId")BigInteger studentId){
        Student studentInfo=studentService.resetPassword(studentId);
        StudentVO studentVO=new StudentVO();
        BeanUtils.copyProperties(studentInfo,studentVO);
        return studentVO;
    }

    @DeleteMapping(value = "/{studentId}")
    public Boolean deleteStudent(@PathVariable("studentId")BigInteger studentId){
        Boolean aBoolean=studentService.deleteStudent(studentId);
        return aBoolean;
    }

    @PutMapping(value = "/active")
    public StudentVO activateStudent(@RequestBody Student student,@RequestAttribute("userId")BigInteger id){
        student.setId(id);
        Student studentInfo=studentService.activateStudent(student);
        StudentVO studentVO=new StudentVO();
        BeanUtils.copyProperties(studentInfo,studentVO);
        return studentVO;
    }



}
