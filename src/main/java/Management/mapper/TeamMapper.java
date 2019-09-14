package Management.mapper;

import Management.entity.Student;
import Management.entity.Team;
import org.apache.catalina.LifecycleState;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/21 18:04
 * @Version 1.0
 */
@Mapper
public interface TeamMapper {
    public void deleteByCourseId(@Param("courseId")BigInteger courseId);

    public Team queryTeamById(@Param("id")BigInteger id);

    public List<Team> queryByCourseId(@Param("courseId")BigInteger courseId);

    public Team queryMyTeam(@Param("courseId")BigInteger courseId,
                          @Param("studentId")BigInteger studentId);

    public void insertTeam(@Param("team")Team team);

    public void updateTeam(@Param("team")Team team);

    public void deleteTeam(@Param("id")BigInteger id);


    BigInteger queryTeam(@Param("klassId")BigInteger klassId, @Param("studentId")BigInteger studentId);

    void updateStatus(@Param("id")BigInteger id,
                      @Param("status")Integer status);

    List<Team> queryIdByCourseId(@Param("courseId")BigInteger courseId);



    String selectTeamName(@Param("id")BigInteger teamId);

    List<Integer> getSerial(@Param("klassId")BigInteger klassId);

}
