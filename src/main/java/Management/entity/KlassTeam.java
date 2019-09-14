package Management.entity;

import com.fasterxml.jackson.databind.node.BigIntegerNode;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/25 17:44
 * @Version 1.0
 */
public class KlassTeam {
    private BigInteger klassId;
    private BigInteger teamId;

    public BigInteger getKlassId() {
        return klassId;
    }

    public void setKlassId(BigInteger klassId) {
        this.klassId = klassId;
    }

    public BigInteger getTeamId() {
        return teamId;
    }

    public void setTeamId(BigInteger teamId) {
        this.teamId = teamId;
    }
}
