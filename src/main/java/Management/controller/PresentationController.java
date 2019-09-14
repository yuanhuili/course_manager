package Management.controller;

import Management.domain.service.impl.AttendanceService;
import Management.domain.service.impl.FileService;
import Management.domain.service.impl.SeminarService;
import Management.domain.utils.DataUtil;
import Management.domain.utils.FlywayCodeEnum;
import Management.entity.Attendance;
import Management.entity.KlassSeminar;
import Management.entity.Question;
import Management.entity.SeminarScore;
import Management.mapper.AttendanceMapper;
import Management.mapper.ClassSeminarMapper;
import Management.mapper.SeminarMapper;
import Management.mapper.SeminarScoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.text.DateFormat;


/**
 * @author Mazijin
 * @since 2018/12/11
 */
@CrossOrigin
@RestController()
@RequestMapping("/presentation")
public class PresentationController {
    @Autowired
    AttendanceService attendanceService;

    @Autowired
    AttendanceMapper attendanceMapper;

    @Autowired
    ClassSeminarMapper classSeminarMapper;

    @Autowired
    FileService fileService;

    @Autowired
    SeminarService seminarService;

    @Autowired
    SeminarScoreMapper seminarScoreMapper;
    @DeleteMapping("/{attendanceId}")
    public Boolean getSeminarScore(@PathVariable("attendanceId") BigInteger attendanceId){
        attendanceService.deleteById(attendanceId);
        return true;
    }

    @PostMapping(value = "/{seminarId}/class/{classId}/attendance")
    public BigInteger enrollPresentation(@PathVariable("seminarId") BigInteger seminarId,
                                         @PathVariable("classId") BigInteger classId,
                                         @RequestBody Attendance attendance){
        BigInteger id=attendanceService.newAttendance(attendance,seminarId,classId);
        return  id;
    }

    //上传PPT
    @PostMapping("/{attendanceId}/uploadPPT")
    public String  uploadPowerpoint(@RequestParam("file") MultipartFile file,
                                    @PathVariable("attendanceId") BigInteger attendanceId){
        if (DataUtil.isEmpty(file)) {
            throw FlywayCodeEnum.UNKNOW_ERROR.getException();
        }
        String url=fileService.store(file).toString();
        String name=file.getOriginalFilename();
        attendanceService.updatePPT(attendanceId,name,url);
        return url;
    }

    @GetMapping(value = "/{attendanceId}/downloadPPT",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public FileSystemResource downloadPowerpoint(@RequestParam("file") String file){
        String name=fileService.load(file).toString();
        return new FileSystemResource(fileService.load(file).toString());

    }

    @PostMapping("/{attendanceId}/uploadReport")
    public String  uploadReport(@RequestParam("file") MultipartFile file,
                                    @PathVariable("attendanceId") BigInteger attendanceId){
        if (DataUtil.isEmpty(file)) {
            throw FlywayCodeEnum.UNKNOW_ERROR.getException();
        }
        String url=fileService.store(file).toString();
        String name=file.getOriginalFilename();
        attendanceService.updateReport(attendanceId,name,url);
        return url;
    }

    @PutMapping("/{classSeminarId}/{TeamId}")
    public Boolean updateSeminarScore(@PathVariable("classSeminarId") BigInteger classSeminarId,
                                      @PathVariable("TeamId") BigInteger TeamId,
                                      @RequestBody() SeminarScore seminarScore){
        seminarScore.setTeamId(TeamId);
        seminarScore.setKlassSeminarId(classSeminarId);
        seminarScoreMapper.updatePresentationScore(seminarScore);
        return true;
    }



}
