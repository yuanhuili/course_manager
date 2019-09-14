package Management.mapper;

import Management.entity.strategy.CourseMemberLimitStrategy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/23 18:53
 * @Version 1.0
 */
@Mapper
public interface CourseMemberLimitStrategyMapper {
    public void insertCourseMemberLimit(@Param("courseId") BigInteger courseId,
                                        @Param("minMember")Integer minMember,
                                        @Param("maxMember")Integer maxMember);
    public CourseMemberLimitStrategy selectCourseMemberLimit(@Param("courseId")BigInteger courseId);
}
