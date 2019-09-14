package Management.controller.vo;

import Management.entity.strategy.ConflictCourseStrategy;
import Management.entity.Course;
import Management.entity.Klass;
import Management.entity.strategy.CourseMemberLimitStrategy;
import Management.entity.strategy.MemberLimitStrategy;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/19 15:50
 * @Version 1.0
 */
public class CourseVO {
    private BigInteger id;
    private BigInteger teacherId;
    private String courseName;
    private String courseTime;
    private String introduction;
    private BigInteger teamMainCourseId;
    private BigInteger seminarMainCourseId;
    private BigDecimal presentationPercentage;
    private BigDecimal reportPercentage;
    private BigDecimal questionPercentage;
    private String teamStartTime;
    private String teamEndTime;
    private String teacherName;
    private List<Klass> klasses;
    private Byte maxMember;
    private Byte minMember;
    private ArrayList<CourseMemberLimitStrategy> courseMemberLimitStrategyList;
    private boolean isAnd;
    private ArrayList<Course> conflictCourseList;

    public CourseVO(){};

    public CourseVO(Course course){
        this.id=course.getId();
        this.teacherId=course.getTeacher().getId();
        this.introduction=course.getIntroduction();
        this.presentationPercentage=course.getPresentationPercentage();
        this.reportPercentage=course.getReportPercentage();
        this.questionPercentage=course.getQuestionPercentage();
        this.courseName=course.getCourseName();
        this.teamEndTime=course.getTeamEndTime();
        this.teamStartTime=course.getTeamStartTime();
        this.teamMainCourseId=course.getTeamMainCourseId();
        this.seminarMainCourseId=course.getSeminarMainCourseId();
        this.teacherName=course.getTeacher().getTeacherName();
        this.klasses=course.getKlasses();
    }


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(BigInteger teacherId) {
        this.teacherId = teacherId;
    }



    public BigInteger getTeamMainCourseId() {
        return teamMainCourseId;
    }

    public void setTeamMainCourseId(BigInteger teamMainCourseId) {
        this.teamMainCourseId = teamMainCourseId;
    }

    public BigInteger getSeminarMainCourseId() {
        return seminarMainCourseId;
    }

    public void setSeminarMainCourseId(BigInteger seminarMainCourseId) {
        this.seminarMainCourseId = seminarMainCourseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public BigDecimal getPresentationPercentage() {
        return presentationPercentage;
    }

    public void setPresentationPercentage(BigDecimal presentationPercentage) {
        this.presentationPercentage = presentationPercentage;
    }

    public BigDecimal getReportPercentage() {
        return reportPercentage;
    }

    public void setReportPercentage(BigDecimal reportPercentage) {
        this.reportPercentage = reportPercentage;
    }

    public BigDecimal getQuestionPercentage() {
        return questionPercentage;
    }

    public void setQuestionPercentage(BigDecimal questionPercentage) {
        this.questionPercentage = questionPercentage;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTeamStartTime() {
        return teamStartTime;
    }

    public void setTeamStartTime(String teamStartTime) {
        this.teamStartTime = teamStartTime;
    }

    public String getTeamEndTime() {
        return teamEndTime;
    }

    public void setTeamEndTime(String teamEndTime) {
        this.teamEndTime = teamEndTime;
    }

    public List<Klass> getKlasses() {
        return klasses;
    }

    public void setKlasses(List<Klass> klasses) {
        this.klasses = klasses;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public Byte getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Byte maxMember) {
        this.maxMember = maxMember;
    }

    public Byte getMinMember() {
        return minMember;
    }

    public void setMinMember(Byte minMember) {
        this.minMember = minMember;
    }

    public ArrayList<CourseMemberLimitStrategy> getCourseMemberLimitStrategyList() {
        return courseMemberLimitStrategyList;
    }

    public void setCourseMemberLimitStrategyList(ArrayList<CourseMemberLimitStrategy> courseMemberLimitStrategyList) {
        this.courseMemberLimitStrategyList = courseMemberLimitStrategyList;
    }

    public boolean isAnd() {
        return isAnd;
    }

    public void setAnd(boolean and) {
        isAnd = and;
    }

    public ArrayList<Course> getConflictCourseList() {
        return conflictCourseList;
    }

    public void setConflictCourseList(ArrayList<Course> conflictCourseList) {
        this.conflictCourseList = conflictCourseList;
    }
}
