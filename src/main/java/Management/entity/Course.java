package Management.entity;

import Management.entity.strategy.ConflictCourseStrategy;
import Management.entity.strategy.CourseMemberLimitStrategy;
import Management.entity.strategy.MemberLimitStrategy;

import java.math.BigInteger;
import java.lang.String;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Course {


	private BigInteger id;

	private BigInteger teacherId;

    private String courseName;

	private String introduction;

	private BigDecimal presentationPercentage;

	private BigDecimal reportPercentage;

	private BigDecimal questionPercentage;

    private String teamStartTime;

    private String teamEndTime;

    private BigInteger teamMainCourseId;

    private BigInteger seminarMainCourseId;

    private Integer minMember;

    private Integer maxMember;

    private List<Klass> klasses;

    private Teacher teacher;

    private Long strategyId;
    private List<ConflictCourseStrategy> conflictCourseStrategies;
    private MemberLimitStrategy memberLimitStrategy;
    private ArrayList<CourseMemberLimitStrategy> courseMemberLimitStrategyList;
    private boolean isAnd;
    private ArrayList<Course> conflictCourseList;

    private List<Round> rounds;



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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

    public List<Klass> getKlasses() {
        return klasses;
    }

    public void setKlasses(List<Klass> klasses) {
        this.klasses = klasses;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<ConflictCourseStrategy> getConflictCourseStrategies() {
        return conflictCourseStrategies;
    }

    public void setConflictCourseStrategies(List<ConflictCourseStrategy> conflictCourseStrategies) {
        this.conflictCourseStrategies = conflictCourseStrategies;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public Integer getMinMember() {
        return minMember;
    }

    public void setMinMember(Integer minMember) {
        this.minMember = minMember;
    }

    public Integer getMaxMember() {
        return maxMember;
    }

    public void setMaxMember(Integer maxMember) {
        this.maxMember = maxMember;
    }

    public MemberLimitStrategy getMemberLimitStrategy() {
        return memberLimitStrategy;
    }

    public void setMemberLimitStrategy(MemberLimitStrategy memberLimitStrategy) {
        this.memberLimitStrategy = memberLimitStrategy;
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

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}
