package Management.mapper;

import Management.entity.Team;
import Management.entity.TeamValidApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/25 13:18
 * @Version 1.0
 */
@Mapper
public interface TeamValidMapper {
    void  insertTeamValid(@Param("teamValidApplication")TeamValidApplication teamValidApplication);
    void updateTeamValidReason(@Param("teamValidApplication")TeamValidApplication teamValidApplication);
    void updateStatus(@Param("id")BigInteger id,
                      @Param("status")Integer status);

    void updateRecord(@Param("id")BigInteger id,
                      @Param("status")Integer status);
    TeamValidApplication queryById(@Param("id")BigInteger id);

    TeamValidApplication queryByTeamTeacherId(@Param("teamId")BigInteger teamId,
                                              @Param("teacherId")BigInteger teacherId);

    List<TeamValidApplication> selectByTeacherId(@Param("teacherId")BigInteger teacherId);

    List<TeamValidApplication>  selectHistoryMessage(@Param("teacherId")BigInteger teacherId);

}
