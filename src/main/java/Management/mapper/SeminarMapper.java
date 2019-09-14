package Management.mapper;

import Management.entity.Seminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/21 18:40
 * @Version 1.0
 */
@Mapper
public interface SeminarMapper {
    public void deleteByCourseId(@Param("courseId")BigInteger courseId);


    void addSeminar(@Param("seminar") Seminar seminar);


    Seminar selectByName(@Param("name")String seminarName);

    Seminar selectById(@Param("seminarId")BigInteger seminarId);

    void updateSeminar(@Param("seminar")Seminar seminar);

    void deleteById(@Param("seminarId")BigInteger seminarId);

    void updateRound(@Param("seminarId")BigInteger seminarId, @Param("roundId")BigInteger roundId);

    public List<Seminar> querryByRoundId(@Param("roundId")BigInteger roundId);

    public Seminar queryByKlassSeminarId(@Param("klassSeminarId")BigInteger klassSeminarId);

    List<Seminar> selectByCourseId(@Param("courseId")BigInteger courseId);
}
