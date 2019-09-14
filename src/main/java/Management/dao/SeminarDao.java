package Management.dao;

import Management.domain.utils.DataUtil;
import Management.entity.Klass;
import Management.entity.KlassSeminar;
import Management.entity.Seminar;
import Management.mapper.ClassMapper;
import Management.mapper.ClassSeminarMapper;
import Management.mapper.SeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/22
 */
@Component
public class SeminarDao {
    @Autowired
    ClassMapper classMapper;
    @Autowired
    SeminarMapper seminarMapper;
    @Autowired
    ClassSeminarMapper classSeminarMapper;
    public BigInteger addSeminar(Seminar seminar) {
        seminarMapper.addSeminar(seminar);
        Seminar seminarId=seminarMapper.selectByName(seminar.getSeminarName());
        if(DataUtil.isNotEmpty(seminarId)){
            List<Klass> classLists=classMapper.querryKlasses(seminar.getCourseId());
            classLists.forEach(klass->{
                KlassSeminar klassSeminar=new KlassSeminar();
                klassSeminar.setKlassId(klass.getId());
                klassSeminar.setSeminarId(seminarId.getId());
                classSeminarMapper.add(klassSeminar);
            });
        }
        return seminarId.getId();
    }

    public List<Seminar> queryByRoundId(BigInteger roundId){
        List<Seminar> seminars=seminarMapper.querryByRoundId(roundId);
        return seminars;
    }
}
