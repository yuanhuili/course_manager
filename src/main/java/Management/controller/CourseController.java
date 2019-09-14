package Management.controller;


import Management.controller.vo.*;
import Management.controller.vo.CourseVO;
import Management.dao.CourseDao;
import Management.domain.service.*;
import Management.domain.service.impl.HandleService;
import Management.domain.service.impl.SeminarService;
import Management.domain.utils.DataUtil;
import Management.entity.*;
import Management.entity.strategy.MemberLimitStrategy;
import Management.mapper.KlassStudentMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
@CrossOrigin
@RestController()
@RequestMapping("/course")
public class CourseController {

	@Autowired
	CourseService courseService ;

	@Autowired
	TeacherService teacherService;

	@Autowired
	ClassService classService;
	@Autowired
	RoundService roundService;
	@Autowired
	TeamService teamService;
	@Autowired
	HandleService handleService;
	@Autowired
	SeminarService seminarService;
	@Autowired
	KlassStudentMapper klassStudentMapper;


	@GetMapping()
	public List<CourseVO> getAllKlass(@RequestAttribute("user") User user) {
		List<CourseVO> courseVOS=new ArrayList<CourseVO>();
		List<Course> courses=courseService.getAllKlass(user.getId(),user.getRole());
		courses.forEach(course -> {
			CourseVO courseVO=new CourseVO(course);
			courseVOS.add(courseVO);
		});
		return courseVOS;


	}

	@PostMapping()
	public Integer  createCourse(@RequestBody CourseVO courseVO,@RequestAttribute("user")User user){
		BigInteger userId=user.getId();
		Course course=new Course();
		course.setTeacherId(userId);
		course.setCourseName(courseVO.getCourseName());
		course.setIntroduction(courseVO.getIntroduction());
		course.setPresentationPercentage(courseVO.getPresentationPercentage());
		course.setQuestionPercentage(courseVO.getQuestionPercentage());
		course.setReportPercentage(courseVO.getReportPercentage());
		course.setTeamStartTime(courseVO.getTeamStartTime());
		course.setTeamEndTime(courseVO.getTeamEndTime());
		//设置本课程人数限制
		MemberLimitStrategy selfStrategy=new MemberLimitStrategy();
		selfStrategy.setMinMember(courseVO.getMinMember());
		selfStrategy.setMaxMember(courseVO.getMaxMember());
		course.setMemberLimitStrategy(selfStrategy);
		//设置选修课程人数限制
		course.setCourseMemberLimitStrategyList(courseVO.getCourseMemberLimitStrategyList());
		course.setAnd(courseVO.isAnd());
		//设置冲突课程
		course.setConflictCourseList(courseVO.getConflictCourseList());
		courseService.putCourse(course);
		return course.getId().intValue();
	}

	@GetMapping(value = "/allCourses")
	public List<Course>  getAllCourse(){
		List<Course> courses=courseService.getAllCourse();
		return courses;
	}

	@GetMapping(value="/{courseId}/round")
	public List<RoundVO> getRound(@PathVariable("courseId")BigInteger courseId){
		List<RoundVO> roundVOS=new ArrayList<RoundVO>();
		List<Round> rounds=courseService.getRound(courseId);
		rounds.forEach(round -> {
			RoundVO roundVO=new RoundVO(round);
			roundVOS.add(roundVO);
		});
	return roundVOS;
	}



	@GetMapping(value="/{courseId}")
	public Course getByCourseId(@PathVariable("courseId")BigInteger courseId){
		Course course=courseService.getCourseById(courseId);
		//CourseVO courseVO=new CourseVO(course);
		return course;
	}

	@DeleteMapping(value="/{courseId}")
	public Integer deleteCourse(@PathVariable("courseId")BigInteger courseId){
		courseService.deleteCourseById(courseId);
		return new Integer("200");

	}

	@GetMapping(value = "/{courseId}/team")
	public List<Team> getTeamInfo(@PathVariable("courseId")BigInteger courseId){
		List<Team> teams=courseService.getTeamByCourseId(courseId);
		return teams;
	}

	@GetMapping(value = "/{courseId}/teamId")
	public List<Team> getTeamIdByCourseId(@PathVariable("courseId")BigInteger courseId){
		return teamService.getTeamIdByCourseId(courseId);
	}

	@GetMapping(value="/{courseId}/myTeam")
	public Team getMyTeamById(@PathVariable("courseId")BigInteger courseId,
							  @RequestAttribute("user")User user){
		Team team=courseService.getMyTeam(courseId,user.getId());
		if(team==null) return new Team();
		return team;

	}

	@GetMapping(value="/{courseId}/noTeam")
	public List<StudentVO> getNoteamStudentById(@PathVariable("courseId")BigInteger courseId,
												@RequestAttribute("user")User user){
		List<Student> students=courseService.getNoTeam(courseId,user.getId());
		List<StudentVO> studentVOS=new ArrayList<StudentVO>();
		students.forEach(student -> {
			StudentVO studentVO=new StudentVO(student);
			studentVOS.add(studentVO);
		});
		return studentVOS;
	}

	@GetMapping(value="/{courseId}/class")
	public List<ClassVO> getKlassById(@PathVariable("courseId")BigInteger courseId){
		List<Klass> klasses=courseService.getKalssById(courseId);
		List<ClassVO> classVOS=new ArrayList<ClassVO>();
		klasses.forEach(klass -> {
			ClassVO classVO=new ClassVO(klass);
			classVOS.add(classVO);
		});
		return classVOS;
	}
	@GetMapping(value = "/{courseId}/myclass")
	public ClassVO getMyclass(@PathVariable("courseId")BigInteger courseId,
				   @RequestAttribute("user") User user){
		Klass klass=classService.getMyClass(courseId,user.getId());
		ClassVO classVO=new ClassVO(klass);
		return classVO;
	}


	@PostMapping(value="/{courseId}/class")
	public Integer putClass(@PathVariable("courseId")BigInteger courseId,
						   @RequestBody ClassVO classVO,
							@RequestAttribute("user")User user){
		if(user.getRole().equalsIgnoreCase("teacher")){
			Klass klass=new Klass();
			klass.setCourseId(courseId);
			klass.setGrade(classVO.getGrade());
			klass.setKlassSerial(classVO.getClassSerial());
			klass.setKlassTime(classVO.getTime());
			klass.setKlassLocation(classVO.getClassRoom());
			BigInteger id=courseService.insertKlassById(klass);
			return id.intValue();
		}
		return new Integer("403");
	}

	@GetMapping(value="/{courseId}/teamshare")
	public	List<ShareVO> getTeamShareByCourseId(@PathVariable("courseId")BigInteger courseId){
		List<ShareTeamApplication> shareTeamApplications=courseService.getTeamShareByCourseId(courseId);
		List<ShareVO> shareVOS =new ArrayList<ShareVO>();
		shareTeamApplications.forEach(shareTeamApplication -> {
			ShareVO shareVO = new ShareVO();
			shareVO.setId(shareTeamApplication.getId());
			CourseVO maincourseVO = new CourseVO();
			CourseVO subCourseVO = new CourseVO();
			BigInteger McourseId = shareTeamApplication.getMainCourseId();
			BigInteger ScourseId = shareTeamApplication.getSubCourseId();
			maincourseVO.setId(McourseId);
			maincourseVO=new CourseVO(courseService.getCourseById(McourseId));
			maincourseVO.setTeacherName(teacherService.querryByCourseId(McourseId).getTeacherName());
			subCourseVO.setId(ScourseId);
			subCourseVO=new CourseVO(courseService.getCourseById(ScourseId));
			subCourseVO.setTeacherName(teacherService.querryByCourseId(ScourseId).getTeacherName());
			shareVO.setMainCourse(maincourseVO);
			shareVO.setSubCourse(subCourseVO);
			shareVOS.add(shareVO);
		});
		return shareVOS;
	}

	@GetMapping(value="/{courseId}/seminarshare")
	public	List<ShareVO> getSeminarShareByCourseId(@PathVariable("courseId")BigInteger courseId){
		List<ShareSeminarApplivation> shareSeminarApplications=courseService.getSeminarShareByCourseId(courseId);
		List<ShareVO> shareVOS =new ArrayList<ShareVO>();
		shareSeminarApplications.forEach(shareSeminarApplivation -> {
			ShareVO shareVO = new ShareVO();
			shareVO.setId(shareSeminarApplivation.getId());
			CourseVO maincourseVO = new CourseVO();
			CourseVO subCourseVO = new CourseVO();
			BigInteger McourseId = shareSeminarApplivation.getMainCourseId();
			BigInteger ScourseId = shareSeminarApplivation.getSubCourseId();
			maincourseVO.setId(McourseId);
			maincourseVO.setCourseName(courseService.getCourseById(McourseId).getCourseName());
			maincourseVO.setTeacherName(teacherService.querryByCourseId(McourseId).getTeacherName());
			subCourseVO.setId(ScourseId);
			subCourseVO.setCourseName(courseService.getCourseById(ScourseId).getCourseName());
			subCourseVO.setTeacherName(teacherService.querryByCourseId(ScourseId).getTeacherName());
			shareVO.setMainCourse(maincourseVO);
			shareVO.setSubCourse(subCourseVO);
			shareVOS.add(shareVO);
		});
		return shareVOS;
	}


	@DeleteMapping(value="/teamshare/{teamShareId}")
	public Boolean	deleteTeamShare(@PathVariable("teamShareId")BigInteger teamShareId,
								   @RequestAttribute("user")User user){
		if(user.getRole().equalsIgnoreCase("teacher")) {
			handleService.cancelTeamShare(teamShareId);
			return true;
		}
		return false;
	}

	@DeleteMapping(value = "/seminarshare/{seminarShareId}")
	public void deleteSeminarShare(@PathVariable("seminarShareId")BigInteger seminarShareId,
								   @RequestAttribute("user")User user){
		if(user.getRole().equalsIgnoreCase("teacher")) {
			handleService.cancelTeamShare(seminarShareId);
			return;
		}
		return;
	}

	@PostMapping(value="/{courseId}/teamShareRequest")
	public Integer teamShareRequest(@PathVariable("courseId")BigInteger courseId,
								   @RequestBody ShareVO shareVO){
		ShareTeamApplication shareTeamApplication=new ShareTeamApplication();
		shareTeamApplication.setMainCourseId(courseId);
		shareTeamApplication.setSubCourseId(shareVO.getSubCourseId());
		shareTeamApplication.setSubCourseTeacherId(teacherService.querryByCourseId(shareVO.getSubCourseId()).getId());
		BigInteger teamShareId= courseService.teamShareRequest(shareTeamApplication);
		return teamShareId.intValue();
	}

	@PostMapping(value = "/{courseId}/seminarShareRequest")
	public Integer seminarShareRequest(@PathVariable("courseId")BigInteger courseId,
									   @RequestBody ShareVO shareVO){
		ShareSeminarApplivation shareSeminarApplivation=new ShareSeminarApplivation();
		shareSeminarApplivation.setMainCourseId(courseId);
		shareSeminarApplivation.setSubCourseId(shareVO.getSubCourseId());
		shareSeminarApplivation.setSubCourseTeacherId(teacherService.querryByCourseId(shareVO.getSubCourseId()).getId());
		BigInteger seminarShareRequestId=courseService.seminarShareRequest(shareSeminarApplivation);
		return  seminarShareRequestId.intValue();
	}


	@GetMapping(value = "/{courseId}/teamScore/{teamId}")
	public List<RoundScoreVO> getTeamAllRoundScore(@PathVariable("courseId")BigInteger courseId,
												 @PathVariable("teamId")BigInteger teamId){
		List<Round> rounds=courseService.getRound(courseId);
		List<RoundScoreVO> roundScoreVOS=new ArrayList<RoundScoreVO>();
 		for (Round round : rounds) {
			RoundScore roundScore=roundService.querryTeamRoundScore(round.getId(),teamId);
			List<SeminarScore> seminarScores=new ArrayList<SeminarScore>();
			if(DataUtil.isNotEmpty(roundScore)) {
				seminarScores=seminarService.getRoundSeminarScore(round.getId(),teamId);
				roundScore.setSeminarScores(seminarScores);
				RoundScoreVO roundScoreVO = new RoundScoreVO(roundScore);
				roundScoreVO.setTeamName(teamService.querryTeamById(teamId).getTeamName());
				roundScoreVO.setRoundSerial(roundService.getRoundById(round.getId()).getRoundSerial());
				roundScoreVOS.add(roundScoreVO);
			}
		}
		return roundScoreVOS;
	}


	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<MyError> courseNotFound(CourseNotFoundException e){
		BigInteger courseId=e.getId();
		MyError error=new MyError(404,"courseID "+courseId+" NOT FOUND");
		return new ResponseEntity<MyError>(error, HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/{courseId}/export")
	public String seminarShareRequest(@PathVariable("courseId")BigInteger courseId,
									HttpServletResponse response){
		Course course=courseService.getCourseById(courseId);
		//工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("成绩表");
		//设置导出的表的名字
		String fileName = course.getCourseName()+".xls";
		//设置头
		int rowNum =1;
		String [] headers = {"讨论课","组名","展示分","提问分","报告分"};
		HSSFRow row = sheet.createRow(0);
		//设置为居中加粗
		HSSFCellStyle style = workbook.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
		HSSFFont font = workbook.createFont();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setFont(font);
		for(int i=0;i<headers.length;i++) {
			HSSFCell cell = row.createCell(i);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
			cell.setCellStyle(style);

		}
		List<ScoreExcel> scoreExcels=courseService.getScore(courseId);

		for (ScoreExcel scoreExcel : scoreExcels) {
			HSSFRow rown = sheet.createRow(rowNum);
			rown.createCell(0).setCellValue(scoreExcel.getSeminarName());
			rown.createCell(1).setCellValue(scoreExcel.getTeamName());
			rown.createCell(2).setCellValue(scoreExcel.getPresentationScore());
			rown.createCell(3).setCellValue(scoreExcel.getQuestionScore());
			rown.createCell(4).setCellValue(scoreExcel.getReportScore());
			HSSFCell createCell = rown.createCell(1);
			createCell.setCellStyle(style);
			rowNum++;
		}
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition", "attachment;filename=" + fileName);
		try {
			response.flushBuffer();
			workbook.write(response.getOutputStream());
		}catch (Exception e){
			return "导出成绩失败";
		}
		return "导出成绩成功";
	}

}
