package Management.domain.service;

import Management.entity.Klass;
import Management.entity.KlassSeminar;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;

/**
 * @author Mazijin
 * @since 2018/12/19
 */
public interface ClassService {
    public void batchImport(String fileName, MultipartFile file, BigInteger courseId,BigInteger classId);

    void deleteClass(BigInteger courseId, BigInteger classId);

    public Klass getMyClass(BigInteger courseId, BigInteger studentId);

    public Klass getClassById(BigInteger id);

    BigInteger getClassSeminar(BigInteger classId, BigInteger seminarId);
}
