package Management.security.filter;

import Management.entity.User;
import Management.security.JwtPayload;
import Management.security.JwtService;
import Management.security.impl.JwtServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author Mazijin
 * @since 2018/12/11
 */

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private static final String ROLE_STUDENT = "ROLE_STUDENT";
    private static final String ROLE_TEACHER = "ROLE_TEACHER";
    private static final String ROLE_ADMIN = "ROLE_ADMIN";

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        String tokenHeader="Bearer ";

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String authToken = header.substring(tokenHeader.length());
        JwtPayload jwtPayload = new JwtServiceImpl().verifyJwt(authToken);

        if (jwtPayload != null && SecurityContextHolder.getContext().getAuthentication() == null
                && jwtPayload.getExp() > System.currentTimeMillis()) {
            User user = jwtPayload.toUser();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.getAccount(),
                    null, getAuthorities(user.getRole()));
            authentication.setDetails(user);
            request.setAttribute("user", user);
            request.setAttribute("userId", user.getId());
            request.setAttribute("name", user.getName());
            request.setAttribute("role", user.getRole());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);

    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            String role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (role.equals("student")) {
            authorities.add(new SimpleGrantedAuthority(ROLE_STUDENT));
        } else if (role.equals("teacher")) {
            authorities.add(new SimpleGrantedAuthority(ROLE_TEACHER));
        }else if (role.equals("admin")) {
            authorities.add(new SimpleGrantedAuthority(ROLE_ADMIN));
        }
        return authorities;
    }

}
