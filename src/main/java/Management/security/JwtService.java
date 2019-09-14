package Management.security;


import Management.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Mazijin
 * @since 2018/12/14
 */
@Component
public interface JwtService {


    String generateJwt(User user);

    /**
     * hujhu
     * @author zzj
     * @param jwtString
     * @return
     */
    JwtPayload verifyJwt(String jwtString);
}
