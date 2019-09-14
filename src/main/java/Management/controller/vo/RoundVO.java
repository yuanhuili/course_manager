package Management.controller.vo;

import Management.entity.Round;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/20 23:07
 * @Version 1.0
 */
public class RoundVO {
    private BigInteger id;

    private Integer roundSerial;

    RoundVO(){};

    public RoundVO(Round round) {
        this.id = round.getId();
        this.roundSerial = round.getRoundSerial();
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public Integer getRoundSerial() {
        return roundSerial;
    }

    public void setRoundSerial(Integer roundSerial) {
        this.roundSerial = roundSerial;
    }
}
