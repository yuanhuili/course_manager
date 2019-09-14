package Management.mapper;

import Management.entity.strategy.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/28 10:58
 * @Version 1.0
 */
@Mapper
public interface StrategyMapper {
    /**
     * 加入课程自身规则
     * @param memberLimitStrategy
     */
    Long addMemberLimitStrategy(MemberLimitStrategy memberLimitStrategy);

    /**
     * 加入课程自身规则
     * @param courseMemberLimitStrategyEntity
     */
    Long addCourseMemberLimitStrategy(CourseMemberLimitStrategy courseMemberLimitStrategyEntity);

    /**
     * 加入“与”表
     * @param id
     * @param strategyId
     * @param strategyName
     */
    Long andCourseMemberLimitStrategy(Long id,Long strategyId,String strategyName);

    /**
     * 加入“或”表
     * @param id
     * @param strategyId
     * @param strategyName
     */
    Long orCourseMemberLimitStrategy(Long id,Long strategyId,String strategyName);

    /**
     * 获取与表最大ID
     */
    Long getAndMaxId();

    /**
     * 获取或表最大ID
     */
    Long getOrMaxId();

    /**
     * 获取冲突表最大ID
     */
    Long getConflictMaxId();

    /**
     * 将结果存入最终表中
     * @param courseId
     * @param strategyId
     * @param strategyName
     */
    void combineAllStrategy(Long courseId,Byte strategySerial,String strategyName,Long strategyId);

    /**
     * 将结果存入最终表中
     * @param id
     * @param courseId
     */
    void addConflictStrategy(Long id,Long courseId);

    /**
     * 获取相应课程的serial
     * @param courseId
     */
    ArrayList<Byte> getSerialList(Long courseId);


    /**
     * 获取team_strategy里面的数据
     * @param id
     * @return
     */
    ArrayList<TeamStrategy> getTeamStrategy(Long id);

    /**
     * 获取team_and_strategy里面的数据
     * @param id
     * @return
     */
    ArrayList<TeamAndStrategy> getTeamAndStrategy(Long id);

    /**
     * 获取team_or_strategy
     * @param id
     * @return
     */
    ArrayList<TeamOrStrategy> getTeamOrStrategy(Long id);

    /**
     * 获取conflict_course_strategy里面的数据
     * @param id
     * @return
     */
    ArrayList<ConflictCourseStrategy> getConflictCourseStrategy(Long id);

    /**
     * 获取course_member_limit_strategy的数据
     * @param id
     * @return
     */
    CourseMemberLimitStrategy getCourseMemberLimitStrategy(Long id);

    /**
     * 获取member_limit_strategy里面的数据
     * @param id
     * @return
     */
    MemberLimitStrategy getMemberLimitStrategy(Long id);
}
