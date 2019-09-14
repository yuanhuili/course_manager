package Management.dao;

import Management.domain.utils.DataUtil;
import Management.entity.KlassRound;
import Management.entity.Round;
import Management.mapper.ClassMapper;
import Management.mapper.ClassRoundMapper;
import Management.entity.Seminar;
import Management.mapper.RoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/23 23:47
 * @Version 1.0
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/22
 */
@Component
public class RoundDao {
    @Autowired
    RoundMapper roundMapper;
    @Autowired
    ClassRoundMapper classRoundMapper;
    @Autowired
    ClassMapper classMapper;

    public Round getRoundById(BigInteger id){
        Round round=roundMapper.querryById(id);
        List<KlassRound> klassRounds =classRoundMapper.querryClassRound(id);
        klassRounds.forEach(klassRound -> {
            Integer klassSerial=new Integer("0");
            Integer grade=new Integer("0");
            if(classMapper.queryStudentClass(klassRound.getKlassId())!=null) {
                klassSerial = classMapper.queryStudentClass(klassRound.getKlassId()).getKlassSerial();
                grade=classMapper.queryStudentClass(klassRound.getKlassId()).getGrade();
            }
            klassRound.setClassSerial(klassSerial);
            klassRound.setGrade(grade);
        });
        round.setKlassRounds(klassRounds);
        return round;
    }

    public BigInteger putRound(Round round){
        roundMapper.insertRound(round);
//        List<KlassRound> klassRounds=round.getKlassRounds();
//        klassRounds.forEach(klassRound -> {
//            classRoundMapper.insertClassRound(klassRound);
//        });
        return round.getId();}
    public BigInteger addRound(Seminar seminar) {
        List<Round> roundList=roundMapper.querryRoundByCourseId(seminar.getCourseId());
        int roundSerial=1;
        if(DataUtil.isNotEmpty(roundList)){
            List<Integer> serials = new ArrayList<Integer>();
            roundList.forEach(round -> {
                serials.add(round.getRoundSerial());
            });
            roundSerial = Collections.max(serials) + 1;
        }
        Round round=new Round();
        round.setCourseId(seminar.getCourseId());
        round.setRoundSerial(roundSerial);
        roundMapper.addRound(round);
        BigInteger roundId=roundMapper.selectByRound(round);
        return roundId;
    }

    public void updaterRound(Round round){
        List<KlassRound> klassRounds=round.getKlassRounds();
        for (KlassRound klassRound : klassRounds) {
            klassRound.setRoundId(round.getId());
            classRoundMapper.updateClassRound(klassRound);
        }
        roundMapper.updateRound(round);
        return;
    }
}
