package Management.mapper;

import Management.entity.strategy.TeamOrStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/23 18:55
 * @Version 1.0
 */
@Mapper
public interface TeamOrStrategyMapper {
    public TeamOrStrategy querryTeamOrStrategy(@Param("id")BigInteger id);
}
