package Management.domain.utils;

/**
 * @author Mazijin
 * @since 2018/11/30
 */
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.ResourceBundle;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

public final class WebUtil {
    private Logger logger = LoggerFactory.getLogger(WebUtil.class);

    private WebUtil() {
    }

    public static final String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
        Cookie cookie = WebUtils.getCookie(request, cookieName);
        return cookie == null ? defaultValue : cookie.getValue();
    }

    public static final void saveCurrentUser(Object user) {
        setSession("CURRENT_USER", user);
    }

    public static final Integer getCurrentUser() {
        Subject currentUser = SecurityUtils.getSubject();
        Integer userId = null;
        if (null != currentUser) {
            userId = (Integer)currentUser.getPrincipal();
        }

        return userId;
    }

    public static final void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }

    }

    public static final void removeCurrentUser(HttpServletRequest request) {
        request.getSession().removeAttribute("CURRENT_USER");
    }

    public static final String getApplicationResource(String key, HttpServletRequest request) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("ApplicationResources", request.getLocale());
        return resourceBundle.getString(key);
    }

    public static final Map<String, Object> getParameterMap(HttpServletRequest request) {
        return WebUtils.getParametersStartingWith(request, (String)null);
    }

    public static final String getHost(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }

        if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if ("127.0.0.1".equals(ip)) {
            InetAddress inet = null;

            try {
                inet = InetAddress.getLocalHost();
            } catch (UnknownHostException var4) {
                var4.printStackTrace();
            }

            ip = inet.getHostAddress();
        }

        if (ip != null && ip.length() > 15 && ip.indexOf(",") > 0) {
            ip = ip.substring(0, ip.indexOf(","));
        }

        return ip;
    }
}
