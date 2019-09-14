package Management.mapper;

import Management.entity.KlassRound;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/23 23:43
 * @Version 1.0
 */
@Mapper
public interface ClassRoundMapper {
    public List<KlassRound> querryClassRound(@Param("roundId")BigInteger roundId);
    public void insertClassRound(@Param("klassRound")KlassRound klassRound);
    public void updateClassRound(@Param("klassRound")KlassRound klassRound);
}
