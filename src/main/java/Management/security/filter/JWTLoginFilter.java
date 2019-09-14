package Management.security.filter;

import Management.controller.vo.AdminLoginSuccessVO;
import Management.controller.vo.LoginSuccessVO;
import Management.entity.User;
import Management.security.impl.JwtServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Mazijin
 * @since 2018/12/11
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {


    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            User user = new ObjectMapper()
                    .readValue(req.getInputStream(), User.class);

            UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(
                    user.getAccount(),
                    user.getPassword(),
                    new ArrayList<>());
            setDetails(req,token);
            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authResult;
        User user= (User) auth.getDetails();
        String jwtString = new JwtServiceImpl().generateJwt(user);
        if(user.getRole().equals("admin"))
        {
            AdminLoginSuccessVO vo = new AdminLoginSuccessVO();
            vo.setJwt(jwtString);
            BeanUtils.copyProperties(user,vo);
            ObjectMapper objectMapper = new ObjectMapper();
            res.setContentType("application/json;charset=utf-8");
            res.getWriter().print(objectMapper.writeValueAsString(vo));

        }
        else {
            LoginSuccessVO vo = new LoginSuccessVO();
            vo.setJwt(jwtString);
            BeanUtils.copyProperties(user,vo);
            ObjectMapper objectMapper = new ObjectMapper();
            res.setContentType("application/json;charset=utf-8");
            res.getWriter().print(objectMapper.writeValueAsString(vo));
            //res.addHeader("Access-Control-Allow-Origin", "*");
            res.addHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS, TRACES");
            res.addHeader("Access-Control-Max-Age", "3600");
            res.addHeader("Access-Control-Allow-Headers", "*");
            res.setStatus(200);

        }

    }

}
