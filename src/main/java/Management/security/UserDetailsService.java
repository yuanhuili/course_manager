package Management.security;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import Management.entity.User;

/**
 * @author Mazijin
 * @since 2018/12/11
 */
public interface UserDetailsService {
    /**
     * 获取用户信息
     * @param account
     * @return
     * @throws UsernameNotFoundException
     */
    User loadUserByAccount(String account) throws UsernameNotFoundException;
}
