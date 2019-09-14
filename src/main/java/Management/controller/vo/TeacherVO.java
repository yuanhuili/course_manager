package Management.controller.vo;

import java.math.BigInteger;

/**
 * @author Mazijin
 * @since 2018/12/16
 */
public class TeacherVO {
    private BigInteger id;
    private String account;
    private String teacherName;
    private String email;

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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
