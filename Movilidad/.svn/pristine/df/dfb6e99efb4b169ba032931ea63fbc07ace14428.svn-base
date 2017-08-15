package co.gov.movilidadbogota.sipa.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * TODO: Sergio, por favor documentar
 *
 * @author arturo.cruz
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Autowired
    UserInfoInterceptor userInfoInterceptor;

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userInfoInterceptor);
    }
}
