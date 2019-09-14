package Management.entity.strategy;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/23 18:36
 * @Version 1.0
 */
public class TeamOrStrategy implements Strategy {
    @Override
    public boolean isLegal(TeamMemberNum teamMemberNum) {
        return false;
    }
    Long id;
    String strategy;
    Long strategyId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrategy() {
        return strategy;
    }

    public void setStrategy(String strategy) {
        this.strategy = strategy;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}
