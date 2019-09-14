package Management.dao;

import Management.domain.utils.DataUtil;
import Management.domain.utils.FlywayCodeEnum;
import Management.entity.KlassStudentTable;
import Management.entity.Student;
import Management.mapper.StudentMapper;
import org.apache.ibatis.annotations.Param;
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
 * @since 2018/12/16
 */
@Component
public class StudentDao {

    @Autowired
    private StudentMapper studentMapper;

    public Student selectByAccount(String account){
        return studentMapper.selectByAccount(account);
    }

    public void updatePasswordById( BigInteger id, String password){
        studentMapper.updatePasswordById(id, password);
        return;
    }


    public Student selectById( BigInteger id){
        Student student=studentMapper.selectById(id);
        return student;
    }

    public void updateEmail(BigInteger id,String email){
        studentMapper.updateEmail(id, email);
        return;
    }

    public void importStudent(String fileName, MultipartFile file) {
        List<Student> studentList = new ArrayList<Student>();
        //List<KlassStudentTable> linklist = new ArrayList<KlassStudentTable>();
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

                String name = row.getCell(1).getStringCellValue().replace(" ","");

                Student student=new Student();
                KlassStudentTable link=new KlassStudentTable();
                student.setAccount(account);
                student.setStudentName(name);

                studentList.add(student);
            }
            for (Student student : studentList) {
                String name = student.getStudentName();
                Student student1 = studentMapper.selectByAccount(student.getAccount());
                if (DataUtil.isEmpty(student1)) {
                    studentMapper.addStudent(student);
                    System.out.println(" 插入 " + student.getStudentName());
                } else {
                    studentMapper.updateStudent(student);
                    System.out.println(" 更新 " + student.getStudentName());
                }
            }

        }catch (Exception e){
            throw FlywayCodeEnum.SEND_ERROR.getException();
        }
    }

}
