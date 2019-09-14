package Management.mapper;

import Management.entity.KlassSeminar;
import Management.entity.KlassStudentTable;
import Management.entity.TeamStudent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/25 17:51
 * @Version 1.0
 */
@Mapper
public interface TeamStudentMapper {
    void addTeamMember(@Param("teamId") BigInteger teamId,
                       @Param("studentId")BigInteger studentId);
    void removeTeamMember(@Param("teamId") BigInteger teamId,
                          @Param("studentId")BigInteger studentId);
    void removeTeam(@Param("teamId") BigInteger teamId);

    void deleteSubRelation(@Param("courseId")BigInteger courseId);

    TeamStudent selectById(@Param("teamId")BigInteger teamId, @Param("studentId")BigInteger studentId);
}
