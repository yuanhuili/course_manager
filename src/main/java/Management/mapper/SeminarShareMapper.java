package Management.mapper;

import Management.entity.ShareSeminarApplivation;
import Management.entity.ShareTeamApplication;
import Management.entity.TeamValidApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/22 17:22
 * @Version 1.0
 */
@Mapper
public interface SeminarShareMapper {
    List<ShareSeminarApplivation> querrySeminarShare(@Param("courseId") BigInteger courseId);
    void deleteSeminarShare(@Param("seminarShareId")BigInteger seminarShareId);
    void  insertShareSeminar(@Param("shareSeminarApplivation")ShareSeminarApplivation shareSeminarApplivation);
    void updateStatus(@Param("id")BigInteger id,@Param("status")Integer status);
    ShareSeminarApplivation queryById(@Param("id")BigInteger id);
    ShareSeminarApplivation queryByMainSubCourseId(@Param("courseId")BigInteger courseId,
                                                @Param("subCourseId")BigInteger subCourseId);

    List<TeamValidApplication> selectByTeacherId(BigInteger userId);
}
