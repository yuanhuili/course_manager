package Management.mapper;

import Management.controller.vo.ClassVO;
import Management.entity.Klass;
import Management.entity.KlassRound;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/20
 */
@Mapper
public interface ClassMapper {
    void deleteClass(@Param("id") BigInteger id,@Param("class_id") BigInteger classId);
    void deleteByCourseId(@Param("courseId") BigInteger courseId);
    List<Klass> querryKlasses(@Param("courseId")BigInteger courseId);
    void insertClass(@Param("klass") Klass klass);
    Klass queryStudentClass(@Param("klassId")BigInteger klassId);

    Klass selectById(@Param("klassId")BigInteger klassId);

    List<KlassRound> selectByRoundId(@Param("roundId")BigInteger roundId);

    List<BigInteger> queryKlassIdByCourseId(@Param("courseId")BigInteger courseId);

}
