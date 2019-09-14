package Management.controller.vo;

import java.math.BigInteger;

/**
 * @author Mazijin
 * @since 2018/12/16
 */
public class UserVOExt {
    private BigInteger id;
    private String account;
    private String role;
    private String name;
    private String email;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
