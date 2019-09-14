package Management.entity;

import java.math.BigInteger;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
public class User {

    private BigInteger id;

    private String account;

    private String role;

    private String password;

    private Boolean active;

    private String name;

    private String email;

    private int messageInterval;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getMessageInterval() {
        return messageInterval;
    }

    public void setMessageInterval(int messageInterval) {
        this.messageInterval = messageInterval;
    }
}

