package Management.entity.strategy;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/23 18:33
 * @Version 1.0
 */
public class TeamAndStrategy implements Strategy {
    @Override
    public boolean isLegal(TeamMemberNum teamMemberNum) {
        return false;
    }
    Long id;
    String strategyName;
    Long strategyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
