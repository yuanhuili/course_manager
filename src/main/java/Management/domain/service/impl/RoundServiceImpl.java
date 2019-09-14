package Management.domain.service.impl;

import Management.dao.RoundDao;
import Management.domain.service.RoundService;
import Management.entity.Round;
import Management.entity.RoundScore;
import Management.entity.Seminar;
import Management.entity.SeminarScore;
import Management.mapper.RoundMapper;
import Management.mapper.RoundScoreMapper;
import Management.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/23 21:10
 * @Version 1.0
 */
@Service
public class RoundServiceImpl implements RoundService {
    @Autowired
    RoundMapper roundMapper;
    @Autowired
    SeminarMapper seminarMapper;
    @Autowired
    RoundScoreMapper roundScoreMapper;
    @Autowired
    RoundDao roundDao;


    @Override
    public List<Seminar> querrySeminar(BigInteger roundId){
        List<Seminar> seminars=seminarMapper.querryByRoundId(roundId);
        return seminars;
    }
    @Override
    public Round getRoundById(BigInteger id){
        Round round=roundDao.getRoundById(id);
        return round;
    }
    @Override
    public BigInteger putRound(Round round){
        BigInteger roundId=roundDao.putRound(round);
        return roundId;
    }
    @Override
    public void updateRound(Round round){
        roundDao.updaterRound(round);
        return;
    }

    @Override
    public List<RoundScore> queryRoundScore(BigInteger roundId){
        List<RoundScore> roundScores=roundScoreMapper.queryRoundScore(roundId);
        return roundScores;
    }

    @Override
    public RoundScore querryTeamRoundScore(BigInteger roundId,BigInteger teamId){
        RoundScore roundScore=roundScoreMapper.queryTeamRoundScore(roundId,teamId);
        return roundScore;
    }

    @Override
    public void putRoundScore(RoundScore roundScore){
        roundScoreMapper.insertTeamRoundScore(roundScore);
        return;
    }
}
