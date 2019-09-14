package Management.domain.service;


import java.math.BigInteger;

import Management.entity.User;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
public interface UserService {

    Boolean sendMailPassword(String account);

    Boolean changePassword( User user);

    User getInfo(User user);

    Boolean updateEmail(User user);

    void sendMail(String email,String text);

    Boolean updateFirst(BigInteger id,User user);
}
