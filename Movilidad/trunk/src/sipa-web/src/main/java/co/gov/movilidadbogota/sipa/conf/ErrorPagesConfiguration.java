package co.gov.movilidadbogota.sipa.conf;

import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * Created by acpreda on 7/6/17.
 */
@Configuration
public class ErrorPagesConfiguration {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {

        return container -> {
            ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401");
            ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403");
            ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
            ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500");

            container.addErrorPages(error401Page, error403Page, error404Page, error500Page);
        };
    }

}
