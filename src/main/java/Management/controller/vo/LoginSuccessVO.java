package Management.controller.vo;

import java.math.BigInteger;

/**
 * @author Mazijin
 * @since 2018/12/12
 */

public class LoginSuccessVO {
    private BigInteger id;
    private String account;
    private String role;
    private String name;
    private Boolean active;
    private String jwt;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setJwt(String header, String payload, String signature) {
        this.jwt = header + '.' + payload + '.' + signature;
    }

    @Override
    public String toString() {
        return "LoginSuccessVO{" +
                "id=" + id +
                ", type='" + role + '\'' +
                ", jwt='" + jwt + '\'' +
                '}';
    }
}
