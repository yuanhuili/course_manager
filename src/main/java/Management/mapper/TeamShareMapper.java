package Management.mapper;

import Management.entity.ShareTeamApplication;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/22 13:36
 * @Version 1.0
 */
@Mapper
public interface TeamShareMapper {
    List<ShareTeamApplication> querryTeamShare(@Param("courseId")BigInteger courseId);
    void deleteTeamShare(@Param("teamShareId")BigInteger teamShareId);
    void insertTeamShare(@Param("shareTeamApplication")ShareTeamApplication shareTeamApplication);
    void updateStatus(@Param("id")BigInteger id,@Param("status")Integer status);
    ShareTeamApplication queryById(@Param("id")BigInteger id);
    ShareTeamApplication queryByMainSubCourseId(@Param("mainCourseId")BigInteger mainCourseId,
                                             @Param("subCourseId")BigInteger subCourseId);
    List<ShareTeamApplication> querryByTeacherId(@Param("teacherId")BigInteger teacherId);

    List<ShareTeamApplication>  selectHistoryMessage(@Param("teacherId")BigInteger teacherId);
}
