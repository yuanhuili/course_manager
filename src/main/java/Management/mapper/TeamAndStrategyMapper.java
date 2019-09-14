package Management.mapper;

import Management.entity.strategy.TeamAndStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/23 18:54
 * @Version 1.0
 */
@Mapper
public interface TeamAndStrategyMapper {
    public TeamAndStrategy querryTeamAndStrategy(@Param("id")BigInteger id);
}
