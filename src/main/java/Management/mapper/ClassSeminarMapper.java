package Management.mapper;

import Management.entity.KlassSeminar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/22
 */
@Mapper
public interface ClassSeminarMapper {
    void add(@Param("klassSeminar") KlassSeminar klassSeminar);

    List<BigInteger> selectClassBySeminarId(@Param("seminarId")BigInteger seminarId);

    void deleteBySeminarId(@Param("seminarId")BigInteger seminarId);

    int selectStatus(@Param("seminarId") BigInteger seminarId);

    void updateDDL(@Param("klassSeminar")KlassSeminar klassSeminar);

    KlassSeminar selectDDL(@Param("seminarId")BigInteger seminarId, @Param("classId")BigInteger classId);

    void updateStatus(@Param("klassSeminar")KlassSeminar klassSeminar);

    List<BigInteger> selectIdBySeminarId(@Param("seminarId")BigInteger seminarId);

    BigInteger selectId(@Param("classId")BigInteger classId, @Param("seminarId")BigInteger seminarId);

    KlassSeminar selectById(@Param("classSeminarId")BigInteger classSeminarId);

    List<KlassSeminar> getNowSeminar();

    void deleteBySeminarIdAndClassId(@Param("seminarId")BigInteger seminarId, @Param("classId")BigInteger classId);

    List<KlassSeminar> getNowSeminarByClassId(@Param("klassId")BigInteger klassId);

    KlassSeminar selectKlassSeminar(@Param("seminarId")BigInteger seminarId, @Param("klassId")BigInteger klassId);
}
