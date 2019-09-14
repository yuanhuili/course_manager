package Management.controller.utils;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

/**
 * @author 马子晋
 * @since 2018/12/5
 */
@Configuration
public class FilterConfig {


    private FilterRegistrationBean getFilterBean(Filter filter, String filterName) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        setFilterRegistrationBeanParam(registrationBean);
        registrationBean.setName(filterName);
        return registrationBean;
    }

    private void setFilterRegistrationBeanParam(FilterRegistrationBean registrationBean) {
        registrationBean.addInitParameter("allowOrigin", "*");
        registrationBean.addInitParameter("allowMethods", "GET,POST,PUT,DELETE,OPTIONS");
        registrationBean.addInitParameter("allowCredentials", "true");
        registrationBean.addInitParameter("allowHeaders", "Origin,Content-Type,X-Token,X-Username,Accept,X-Requested-With,X_Requested_With");
        registrationBean.addUrlPatterns("/*");
    }
}
