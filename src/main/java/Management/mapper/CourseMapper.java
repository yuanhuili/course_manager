package Management.mapper;

import Management.entity.*;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.PathVariable;

import javax.swing.plaf.basic.BasicGraphicsUtils;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/4 21:07
 * @Version 1.0
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course>{ ;

    public void insertCourse(@Param("course")Course course);


    public Course queryByCourseId(@Param("courseId")BigInteger courseId);

    public void deleteCourseById(@Param("courseId")BigInteger courseId);

    public Boolean insertKlass(@Param("courseId")BigInteger courseId,@Param("klass")Klass klass);


    public  void deleteShareTeam(@Param("shareTeamId")BigInteger shareTeamId);

    public void deleteShareSeminar(@Param("shareSeminarId")BigInteger shareSeminarId);



    public List<Share> getTeamShare(@Param("courseId")BigInteger courseId);

    public List<Share> getSeminarShare(@Param("courseId")BigInteger courseId);

    public void shareRequest(@Param("courseId")BigInteger courseId);

    public List<Course> queryByTeacherId(@Param("teacherId")BigInteger teacherId);

    public List<Course> queryAllCourse();

    public void handleTeamShare(@Param("subCourseId")BigInteger subCourseId,
                                @Param("mainCourseId")BigInteger mainCourseId);

    public void handleSeminarShare(@Param("subCourseId")BigInteger subCourseId,
                                   @Param("mainCourseId")BigInteger mainCourseId);

    ArrayList<SimpleCourseEntity> findSimpleCourseEntityByTeacherId(Long teacherId);

}
