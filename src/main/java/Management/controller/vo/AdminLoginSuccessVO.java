package Management.controller.vo;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;

/**
 * @author Mazijin
 * @since 2018/12/12
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AdminLoginSuccessVO {
    private BigInteger id;
    private String jwt;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
