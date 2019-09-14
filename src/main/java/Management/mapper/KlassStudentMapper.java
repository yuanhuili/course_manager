package Management.mapper;

import Management.entity.KlassStudentTable;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/19
 */
@Mapper
public interface KlassStudentMapper {
    KlassStudentTable selectByCourseAndStudent(@Param("link")KlassStudentTable link);

    void add(@Param("link") KlassStudentTable link);

    void update(@Param("link") KlassStudentTable link);

    List<KlassStudentTable> selectMyCourse(@Param("studentId") BigInteger studentId);

    KlassStudentTable selectMyKlassId(@Param("courseId")BigInteger courseId,
                                      @Param("studentId")BigInteger studentId);

    void deleteByCourseId(@Param("courseId")BigInteger courseId);

    Long getKlassIdByCourseAndStudent(Long courseId,Long studentId);


}
