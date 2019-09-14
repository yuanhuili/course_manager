package Management.entity;

import com.baomidou.mybatisplus.annotations.TableField;

import java.math.BigInteger;

/**
 * @Author: yuanhuili
 * @Date: 2018/11/30 20:07
 * @Version 1.0
 */
public class Student {

    private BigInteger id;

    private String  account;

    private String  password;

    private Boolean active;

    private String studentName;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
