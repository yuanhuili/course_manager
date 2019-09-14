package Management.domain.service;

import Management.entity.Round;
import Management.entity.RoundScore;
import Management.entity.Seminar;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/23 21:05
 * @Version 1.0
 */
public interface RoundService {
    public List<Seminar> querrySeminar(BigInteger roundId);
    public Round getRoundById(BigInteger id);
    public BigInteger putRound(Round round);
    public void updateRound(Round round);
    public List<RoundScore> queryRoundScore(BigInteger roundId);
    public RoundScore querryTeamRoundScore(BigInteger roundId,BigInteger teamId);
    public void putRoundScore(RoundScore roundScore);
}

