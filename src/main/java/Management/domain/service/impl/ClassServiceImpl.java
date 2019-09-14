package Management.domain.service.impl;

import Management.dao.ClassDao;
import Management.dao.KlassStudentDao;
import Management.dao.StudentDao;
import Management.domain.service.ClassService;
import Management.domain.utils.FlywayCodeEnum;
import Management.entity.Klass;
import Management.entity.KlassSeminar;
import Management.mapper.ClassMapper;
import Management.mapper.ClassSeminarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;

/**
 * @author Mazijin
 * @since 2018/12/19
 */
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    StudentDao studentDao;

    @Autowired
    KlassStudentDao klassStudentDao;
    @Autowired
    ClassMapper classMapper;

    @Autowired
    ClassDao classDao;
    @Autowired
    ClassSeminarMapper classSeminarMapper;
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public void batchImport(String fileName, MultipartFile file,BigInteger courseId,BigInteger classId) {
        boolean notNull = false;
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw FlywayCodeEnum.EXCEL_ERROR.getException();
        }
        try {
            studentDao.importStudent(fileName,file);
            klassStudentDao.importKlassStudent(fileName,file,courseId,classId);

        }catch (Exception e){
            throw FlywayCodeEnum.EXCEL_ERROR.getException();
        }

    }

    @Override
    public void deleteClass(BigInteger courseId, BigInteger classId) {
        classMapper.deleteClass(courseId,classId);
        return ;
    }

    @Override
    public Klass  getMyClass(BigInteger courseId,BigInteger studentId){
        Klass klass=classDao.getKlassInfo(courseId,studentId);
        return klass;
    }
    @Override
    public Klass getClassById(BigInteger id){
        Klass klass=classMapper.selectById(id);
        return klass;
    }

    @Override
    public BigInteger getClassSeminar(BigInteger classId, BigInteger seminarId) {
        return classSeminarMapper.selectId(classId,seminarId);
    }
}
