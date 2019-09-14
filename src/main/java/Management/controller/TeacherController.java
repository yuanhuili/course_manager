package Management.controller;

import Management.controller.vo.TeacherVO;
import Management.domain.service.TeacherService;
import Management.entity.Teacher;
import Management.entity.Commission;
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
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @PostMapping()
    public TeacherVO addTeacher(@RequestBody Teacher teacher){
        TeacherVO teacherVO=new TeacherVO();
        Teacher teacherInfo=teacherService.add(teacher);
        BeanUtils.copyProperties(teacherInfo,teacherVO);
        return teacherVO;
    }
    @GetMapping()
    public List<TeacherVO> getAll(){
        List<TeacherVO> TeacherVOs=new ArrayList<TeacherVO>();
        List<Teacher> teachers=teacherService.selectAll();
        teachers.forEach(student -> {
            TeacherVO teacherVO=new TeacherVO();
                    BeanUtils.copyProperties(student,teacherVO);
            TeacherVOs.add(teacherVO);
                }
        );
        return TeacherVOs;
    }

    @GetMapping(value = "/searchTeacher")
    public TeacherVO searchTeacher(@RequestParam("identity")String identity){
        TeacherVO teacherVO=new TeacherVO();
        Teacher teacher=teacherService.selectByIdentity(identity);
        BeanUtils.copyProperties(teacher,teacherVO);
        return teacherVO;
    }

    @PutMapping(value = "/{teacherId}/information")
    public TeacherVO modifyTeacherInfo(@PathVariable("teacherId")BigInteger teacherId, @RequestBody Teacher teacher){
        teacher.setId(teacherId);
        Teacher teacherInfo=teacherService.modifyInfo(teacher);
        TeacherVO teacherVO=new TeacherVO();
        BeanUtils.copyProperties(teacherInfo,teacherVO);
        return teacherVO;
    }

    @PutMapping(value = "/{teacherId}/password")
    public TeacherVO resetTeacherPassword(@PathVariable("teacherId")BigInteger teacherId){
        Teacher teacherInfo=teacherService.resetPassword(teacherId);
        TeacherVO teacherVO=new TeacherVO();
        BeanUtils.copyProperties(teacherInfo,teacherVO);
        return teacherVO;
    }

    @DeleteMapping(value = "/{teacherId}")
    public Boolean deleteTeacher(@PathVariable("teacherId")BigInteger teacherId){
        Boolean aBoolean=teacherService.deleteTeacher(teacherId);
        return aBoolean;
    }

    @PutMapping(value = "/active")
    public TeacherVO activateTeacher(@RequestBody Teacher teacher,@RequestAttribute("userId")BigInteger id){
        teacher.setId(id);
        Teacher teacherInfo=teacherService.activateTeacher(teacher);
        TeacherVO teacherVO=new TeacherVO();
        BeanUtils.copyProperties(teacherInfo,teacherVO);
        return teacherVO;
    }
    /*
    老师待办事项
     */
    @GetMapping("/commission")
    public Commission commissionTeacher(@RequestAttribute("userId")BigInteger userId){
        return teacherService.all(userId);
    }

    @GetMapping("/historyMessage")
    public Commission getHistoryMessage(@RequestAttribute("userId")BigInteger userId){
        return teacherService.queryHistoryMessage(userId);
    }

}
