package Management.controller.vo;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/26 14:28
 * @Version 1.0
 */
public class AllRoundGradeVO {
    private BigInteger roundId;

    private List<RoundScoreVO> roundScoreVOS;

    public List<RoundScoreVO> getRoundScoreVOS() {
        return roundScoreVOS;
    }

    public void setRoundScoreVOS(List<RoundScoreVO> roundScoreVOS) {
        this.roundScoreVOS = roundScoreVOS;
    }

    public BigInteger getRoundId() {
        return roundId;
    }

    public void setRoundId(BigInteger roundId) {
        this.roundId = roundId;
    }
}
