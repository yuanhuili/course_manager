package Management.controller.vo;

import Management.entity.Student;

import java.math.BigInteger;

/**
 * @author Mazijin
 * @since 2018/12/16
 */
public class StudentVO {
    private BigInteger id;
    private String account;
    private String studentName;
    private String email;

    public StudentVO(){};

    public StudentVO(Student student) {
        this.account = student.getAccount();
        this.id=student.getId();
        this.studentName=student.getStudentName();
        this.email=student.getEmail();
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
