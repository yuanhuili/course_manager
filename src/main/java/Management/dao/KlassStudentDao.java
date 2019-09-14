package Management.dao;

import Management.domain.utils.DataUtil;
import Management.domain.utils.FlywayCodeEnum;
import Management.entity.KlassStudentTable;
import Management.entity.Student;
import Management.mapper.KlassStudentMapper;
import Management.mapper.StudentMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/19
 */
@Component
public class KlassStudentDao {
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private KlassStudentMapper klassStudentMapper;

    public void importKlassStudent(String fileName, MultipartFile file, BigInteger courseId, BigInteger classId) {
        List<KlassStudentTable> linklist = new ArrayList<KlassStudentTable>();
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        try {
            InputStream is = file.getInputStream();
            Workbook wb = null;
            if (isExcel2003) {
                wb = new HSSFWorkbook(is);
            } else {
                wb = new XSSFWorkbook(is);
            }
            Sheet sheet = wb.getSheetAt(0);

            for (int r = 2; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) {
                    continue;
                }
                String account = row.getCell(0).getStringCellValue().replace(" ","");
                String name = row.getCell(1).getStringCellValue();
                Student student=studentMapper.selectByAccount(account);
                KlassStudentTable klassStudentTable=new KlassStudentTable();
                klassStudentTable.setKlassId(classId);
                klassStudentTable.setCourseId(courseId);
                klassStudentTable.setStudentId(student.getId());
                linklist.add(klassStudentTable);
            }
            for (KlassStudentTable link : linklist) {
                KlassStudentTable klassStudentTable = klassStudentMapper.selectByCourseAndStudent(link);
                if (DataUtil.isEmpty(klassStudentTable)) {
                    klassStudentMapper.add(link);

                } else {
                    klassStudentMapper.update(link);
                }
            }

        }catch (Exception e){
        }
    }

    }

