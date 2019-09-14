package Management.mapper;

import Management.entity.strategy.ConflictCourseStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/23 18:51
 * @Version 1.0
 */
@Mapper
public interface ConflictCourseMapper {
    public void insertConfilctCourse(@Param("course1Id") BigInteger course1Id,
                                     @Param("course2Id")BigInteger course2Id);
    public List<ConflictCourseStrategy>  queryConfilctStrategy(@Param("courseId")BigInteger courseId);

}

