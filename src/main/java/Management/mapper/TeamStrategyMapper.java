package Management.mapper;

import Management.entity.strategy.TeamStrategy;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/23 18:55
 * @Version 1.0
 */
@Mapper
public interface TeamStrategyMapper {
    public List<TeamStrategy>  queryTeamStrategy(BigInteger courseId);
}
