package Management.dao;

import Management.entity.Klass;
import Management.mapper.ClassMapper;
import Management.mapper.KlassStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/12/23 20:14
 * @Version 1.0
 */
@Component
public class ClassDao {
    @Autowired
    ClassMapper classMapper;
    @Autowired
    KlassStudentMapper classStudentMapper;

    public Klass getKlassInfo(BigInteger courseId,BigInteger studentId){
        BigInteger klassId=new BigInteger("0");
        if(classStudentMapper.selectMyKlassId(courseId,studentId)!=null)
            klassId=classStudentMapper.selectMyKlassId(courseId,studentId).getKlassId();
        Klass klass=classMapper.queryStudentClass(klassId);
        return klass;
    }

    public BigInteger insertClass(Klass klass){
        classMapper.insertClass(klass);
        return klass.getId();
    }
}
