package Management.dao;

import Management.entity.*;
import Management.entity.strategy.ConflictCourseStrategy;
import Management.entity.strategy.CourseMemberLimitStrategy;
import Management.entity.strategy.MemberLimitStrategy;
import Management.mapper.*;
import Management.mapper.CourseMemberLimitStrategyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/19 20:33
 * @Version 1.0
 */
@Component
public class CourseDao {
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    RoundMapper roundMapper;

    @Autowired
    ClassMapper classMapper;

    @Autowired
    KlassStudentMapper classStudentMapper;

    @Autowired
    SeminarMapper seminarMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    TeamMapper teamMapper;

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TeamShareMapper teamShareMapper;
    @Autowired
    SeminarShareMapper seminarShareMapper;

    @Autowired
    ConflictCourseMapper conflictCourseMapper;
    @Autowired
    CourseMemberLimitStrategyMapper courseMemberLimitStrategyMapper;

    @Autowired
    StrategyMapper strategyMapper;

    @Autowired
    KlassStudentMapper klassStudentMapper;

    public List<Course> getAllStudentCourse(BigInteger userId){
        List<KlassStudentTable> klassStudentTables=klassStudentMapper.selectMyCourse(userId);
        List<Course> courses=new ArrayList<Course>();
        klassStudentTables.forEach(klassStudentTable -> {
            BigInteger klassId=klassStudentTable.getKlassId();
            BigInteger courseId=klassStudentTable.getCourseId();
            Klass klass =new Klass();
            Course course=new Course();
            Teacher teacher=new Teacher();
            List<Klass> klasses=new ArrayList<Klass>();
             klass = classMapper.queryStudentClass(klassId);
            klasses.add(klass);
             course=courseMapper.queryByCourseId(courseId);
                teacher=teacherMapper.querryByCourseId(courseId);
            course.setTeacher(teacher);
            course.setKlasses(klasses);
            courses.add(course);
        });
        return courses;
    }
    public List<Course>getAllTeacherCourse(BigInteger userId){
        List<Course> courses=courseMapper.queryByTeacherId(userId);
        courses.forEach(course -> {
            List<Klass>klasses=classMapper.querryKlasses(course.getId());
            course.setKlasses(klasses);
            course.setTeacher(teacherMapper.querryByCourseId(course.getId()));
        });
        return courses;

    }

    public void putCourse(Course course){
        courseMapper.insertCourse(course);
    }

    public List<Round> getRound(BigInteger courseId){
        List<Round> rounds=roundMapper.querryRoundByCourseId(courseId);
        return  rounds;
    }

    public Course getCourseById(BigInteger id){
        Course course=courseMapper.queryByCourseId(id);
        course.setTeacher(teacherMapper.querryByCourseId(course.getId()));
        course.setKlasses(classMapper.querryKlasses(course.getId()));
        return course;
    }

    /*删除course并级联删除相关的记录*/
    public void delteCourse(BigInteger courseId){
        seminarMapper.deleteByCourseId(courseId);
        roundMapper.deleteRound(courseId);
        classMapper.deleteByCourseId(courseId);
        klassStudentMapper.deleteByCourseId(courseId);
        courseMapper.deleteCourseById(courseId);
    }

    public List<Team> getTeam(BigInteger courseId){
        List<Team> teams=teamMapper.queryByCourseId(courseId);
//        for (Team team : teams) {
//            Student leader=new Student();
//            leader=studentMapper.selectById(team.getLeaderId());
//            leader.setPassword(null);
//            List<Student> members=studentMapper.queryTeamMember(team.getId(),leader.getId());
//            team.setLeader(leader);
//            team.setMembers(members);
//        }
        return teams;
    }

    public Team getMyTeam(BigInteger courseId,BigInteger studentId){
        Team  team=teamMapper.queryMyTeam(courseId,studentId);
       if (team!=null) {
            Student leader = new Student();
            leader = studentMapper.selectById(team.getLeaderId());
            List<Student> members = studentMapper.queryTeamMember(team.getId(),leader.getId());
            members.remove(leader);
            team.setMembers(members);
            team.setLeader(leader);
            return team;
        }
      return null;
    }


    public List<Student> getNoteam(BigInteger courseId){
        List<Student> students=studentMapper.querryNoTeam(courseId);
        return  students;
    }
    public List<Klass> getKlasses(BigInteger courseId){
        List<Klass> klasses=classMapper.querryKlasses(courseId);
        return klasses;
    }
    public BigInteger putClass(Klass klass){
        classMapper.insertClass(klass);
        return klass.getId();
    }

    public List<ShareTeamApplication> getShareTeam(BigInteger courseId){
        List<ShareTeamApplication> shareTeamApplications= teamShareMapper.querryTeamShare(courseId);
        return shareTeamApplications;
    }

    public List<ShareSeminarApplivation> getShareSeminar(BigInteger courseId){
        List<ShareSeminarApplivation> shareSeminarApplications=seminarShareMapper.querrySeminarShare(courseId);
        return shareSeminarApplications;
    }

    public List<Course> queryAllCourse(){
        List<Course> courses=courseMapper.queryAllCourse();
        courses.forEach(course -> {
            course.setTeacher(teacherMapper.querryByCourseId(course.getId()));
            course.setMaxMember(5);
            course.setMinMember(3);
            List<ConflictCourseStrategy> conflictCourseStrategies=new ArrayList<ConflictCourseStrategy>();
            conflictCourseStrategies=conflictCourseMapper.queryConfilctStrategy(course.getId());
//            for (ConflictCourseStrategy conflictCourseStrategy : conflictCourseStrategies) {
//                CourseMemberLimitStrategy courseMemberLimitStrategy=courseMemberLimitStrategyMapper.selectCourseMemberLimit(conflictCourseStrategy.getCourseId().longValue());
////                if (courseMemberLimitStrategy!=null) {
////                    conflictCourseStrategy.setMax_Member(courseMemberLimitStrategy.getMaxMember());
////                    conflictCourseStrategy.setMin_Member(courseMemberLimitStrategy.getMinMember());
////                }
//            }
            course.setConflictCourseStrategies(conflictCourseStrategies);
        });
        return courses;
    }



    public boolean addTeamStrategy(Course courseEntity){
        //加课程自身的规则，并获取规则ID
        MemberLimitStrategy memberLimitStrategy=courseEntity.getMemberLimitStrategy();
        strategyMapper.addMemberLimitStrategy(memberLimitStrategy);
        Long memberLimitStrategyId = memberLimitStrategy.getId();

        ArrayList<Long> idList = new ArrayList<>();
        ArrayList<CourseMemberLimitStrategy> courseMemberLimitStrategyList = courseEntity.getCourseMemberLimitStrategyList();

        //加入其他课程的规则信息
        for(int i=0;i<courseMemberLimitStrategyList.size();i++){
            //加入选其他课的规则，并把ID到List中
            strategyMapper.addCourseMemberLimitStrategy(courseMemberLimitStrategyList.get(i));
            Long courseMemberLimitStrategyId=courseMemberLimitStrategyList.get(i).getId();
            idList.add(courseMemberLimitStrategyId);
        }

        //按与或规则添加到相应表中，取得ID
        Long courseLimitId=null;
        String strategy_name=new String();
        if(courseEntity.isAnd()){
            //如果是“与”关系
            //获取与表最大ID
            Long andId=strategyMapper.getAndMaxId();
            andId=(andId==null)?andId=Long.parseLong("1"):andId+1;
            for(int i=0;i<idList.size();i++){
                //加入“与”表
                strategyMapper.andCourseMemberLimitStrategy(andId,idList.get(i),"CourseMemberLimitStrategy");
            }
            courseLimitId=andId;
            strategy_name="TeamAndStrategy";
        }
        else if(!courseEntity.isAnd()){
            //如果是“或关系”
            //获取或表最大ID
            Long orId=strategyMapper.getAndMaxId();
            orId=(orId==null)?orId=Long.parseLong("1"):orId+1;
            for(int i=0;i<idList.size();i++){
                //加入“或”表
                strategyMapper.orCourseMemberLimitStrategy(orId,idList.get(i),"CourseMemberLimitStrategy");
            }
            courseLimitId=orId;
            strategy_name="TeamOrStrategy";
        }

        //将课程自身规则和选其他课的规则以“与”逻辑存入表中
        Long newAndId=strategyMapper.getAndMaxId();
        newAndId=(newAndId==null)?newAndId=Long.parseLong("1"):newAndId+1;
        strategyMapper.andCourseMemberLimitStrategy(newAndId,courseLimitId,strategy_name);
        strategyMapper.andCourseMemberLimitStrategy(newAndId,memberLimitStrategyId,"MemberLimitStrategy");

        //获得strategySerial
        Byte strategySerial=this.getSerial(courseEntity);
        //将最终规则存入最终表中
        strategyMapper.combineAllStrategy(courseEntity.getId().longValue(),strategySerial,"TeamAndStrategy",newAndId);
        return true;
    }

    /**
     * 冲突课程的限制规则
     * @param courseEntity
     * @return
     */
    public boolean addConflictStrategy(Course courseEntity){
        ArrayList<Course> conflictList=courseEntity.getConflictCourseList();
        Long maxId=strategyMapper.getConflictMaxId();
        maxId=(maxId==null)?Long.parseLong("1"):maxId+1;
        for(int i=0;i<conflictList.size();i++){
            //加入冲突的课程
            strategyMapper.addConflictStrategy(maxId,conflictList.get(i).getId().longValue());
        }
        //将本课程加入冲突列表中
        //strategyMapper.addConflictStrategy(maxId,courseEntity.getId());
        //获得strategySerial
        Byte strategySerial=this.getSerial(courseEntity);
        //将最终规则存入最终表中
        strategyMapper.combineAllStrategy(courseEntity.getId().longValue(),strategySerial,"ConflictCourseStrategy",maxId);
        return true;
    }
    private Byte getSerial(Course courseEntity){
        Byte strategySerial=null;
        ArrayList<Byte> serialList =strategyMapper.getSerialList(courseEntity.getId().longValue());
        if(serialList.size()==0){
            return 1;
        }
        else{
            for(Byte i=0;i<serialList.size();i++){
                Integer tempInt=i+1;
                Byte tempb=Byte.parseByte(tempInt.toString());
                if(!serialList.get(i).equals(tempb)){
                    Integer temp=i+1;
                    return Byte.parseByte(temp.toString());
                }
            }
        }
        Integer re=serialList.size()+1;
        return Byte.parseByte(re.toString());
    }






}
