package co.gov.movilidadbogota.sipa.conf;


import co.gov.movilidadbogota.sipa.data.gen.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

/**
 * Configuracion del cliente para la autenticacion OAUTH2 SSO
 *
 * @author lorenzo.pinango
 */
@Configuration
@EnableOAuth2Sso
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuthClientConfiguration extends WebSecurityConfigurerAdapter {

    @Value("${app.oauth2-server:http://localhost:8082/sipa-cas}")
    String authPath;

    @Bean
    OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
        return new OAuth2RestTemplate(details, oauth2ClientContext);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().ignoringAntMatchers("/v" + Constants.API_VERSION + "/**");

        http
                .logout()
                .logoutSuccessUrl(authPath + "/logout");
        http.authorizeRequests().antMatchers("/comparendosService").permitAll();
        http.authorizeRequests().anyRequest().fullyAuthenticated();
    }
}
