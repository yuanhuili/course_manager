package Management.controller;

import Management.domain.service.ClassService;
import Management.domain.utils.FlywayCodeEnum;
import Management.entity.Klass;
import Management.entity.KlassSeminar;
import Management.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;

/**
 * @author Mazijin
 * @since 2018/12/19
 */
@CrossOrigin
@RestController()
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    @PutMapping(value = "/{classId}")
    public String importStudent(@PathVariable("classId")BigInteger classId, @RequestParam("courseId")BigInteger courseId,
                                @RequestParam("file")MultipartFile file){

        String fileName = file.getOriginalFilename();
        try {
        classService.batchImport(fileName, file,courseId,classId);
        } catch (Exception e) {
            throw FlywayCodeEnum.EXCEL_ERROR.getException();
        }

        return classId.toString();
    }

    @DeleteMapping(value = "/{classId}")
    public void DeleteClass(@PathVariable("classId")BigInteger classId, @RequestParam("courseId")BigInteger courseId){
        classService.deleteClass(courseId,classId);
        return ;
    }

    @GetMapping(value = "/{classId}")
    public Klass getClassById(@PathVariable("classId")BigInteger classId){
        return classService.getClassById(classId);
    }

    @GetMapping(value = "/{classId}/{seminarId}")
    public BigInteger getClassSeminar(@PathVariable("classId")BigInteger classId,
                                        @PathVariable("seminarId")BigInteger seminarId){
        return classService.getClassSeminar(classId,seminarId);
    }

}
