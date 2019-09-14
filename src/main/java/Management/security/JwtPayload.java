package Management.security;


import Management.entity.User;

import java.math.BigInteger;

/**
 * @author Mazijin
 * @since 2018/12/14
 */
public class JwtPayload {
    private BigInteger id;
    private String role;
    private String name;
    private Long exp;

    public JwtPayload() {

    }

    public JwtPayload(BigInteger id, String role, Long exp) {
        this.id = id;
        this.role = role;
        this.exp = exp;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }
    public User toUser() {
        User user = new User();
        user.setName(name);
        user.setId(id);
       user.setRole(role);
        return user;
    }



}
