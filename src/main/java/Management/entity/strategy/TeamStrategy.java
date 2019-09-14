package Management.entity.strategy;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/13 19:23
 * @Version 1.0
 */
public class TeamStrategy implements Strategy {
    @Override
    public boolean isLegal(TeamMemberNum teamMemberNum) {
        return false;
    }
    Long courseId;
    Byte strategySerial;
    String strategyName;
    Long strategyId;

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Byte getStrategySerial() {
        return strategySerial;
    }

    public void setStrategySerial(Byte strategySerial) {
        this.strategySerial = strategySerial;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}
