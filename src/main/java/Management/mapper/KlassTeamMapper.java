package Management.mapper;

import Management.entity.KlassTeam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import sun.nio.cs.ext.Big5;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/25 17:50
 * @Version 1.0
 */
@Mapper
public interface KlassTeamMapper {
    public void insertKlassTeam(@Param("klassId")BigInteger klassId,
                                @Param("teamId")BigInteger teamId);

    public List<KlassTeam> queryByTeamId(@Param("teamId")BigInteger teamId);

    public List<KlassTeam> queryByKlassId(@Param("klassId")BigInteger klassId);

    public void deleteSubRelation(@Param("klassId")BigInteger klassId);
}
