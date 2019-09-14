package Management.mapper;

import Management.entity.strategy.MemberLimitStrategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/23 19:07
 * @Version 1.0
 */
@Mapper
public interface MemberLimitStrategyMapper {
    public MemberLimitStrategy querryMemberStrategy();

}
