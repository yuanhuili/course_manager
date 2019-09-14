package Management.security;

/**
 * @author Mazijin
 * @since 2018/12/11
 */

import Management.domain.utils.DataUtil;
import Management.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin
@Component
@Qualifier("customAuthenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final String ROLE_STUDENT = "ROLE_STUDENT";
    private static final String ROLE_TEACHER = "ROLE_TEACHER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    @Autowired
    private UserDetailsService userDetailsService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomAuthenticationProvider(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    /**
     * 是否可以提供输入类型的认证服务
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }


    /**
     * 验证登录信息,若登陆成功,设置 Authentication
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // 获取认证的用户名 & 密码
        String account = authentication.getName();
        String password = authentication.getCredentials().toString();
        System.out.println(account);
        System.out.println(password);
        //通过用户名从数据库中查询该用户
        User user = userDetailsService.loadUserByAccount(account);
        //判断密码(这里是md5加密方式)是否正确
        String dbPassword = user.getPassword();
        String encoderPassword = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!dbPassword.equals(password)) {
            throw new UsernameNotFoundException("密码错误");
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(account, password, getAuthorities(user.getRole()));
        ((UsernamePasswordAuthenticationToken) auth).setDetails(user);
        return auth;

    }
    private Collection<? extends GrantedAuthority> getAuthorities(
            String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (DataUtil.isEmpty(role)) {
            return authorities;
        } else if (role.equals("student")) {
            authorities.add(new SimpleGrantedAuthority(ROLE_STUDENT));
        } else if (role.equals("teacher")) {
            authorities.add(new SimpleGrantedAuthority(ROLE_TEACHER));
        } else if (role.equals("admin")) {
            authorities.add(new SimpleGrantedAuthority(ROLE_ADMIN));
        }
        return authorities;
    }


}

