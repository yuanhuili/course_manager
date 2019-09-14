package Management.mapper;

import Management.entity.Round;
import Management.entity.RoundScore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/24 15:27
 * @Version 1.0
 */
@Mapper
public interface RoundScoreMapper {
    public List<RoundScore> queryRoundScore(@Param("roundId")BigInteger roundId);
    public RoundScore queryTeamRoundScore(@Param("roundId")BigInteger roundId,
                                           @Param("teamId")BigInteger teamId);
    public void insertTeamRoundScore(@Param("roundScore")RoundScore roundScore);


    void updateScore(@Param("roundScore")RoundScore roundScore);
}
