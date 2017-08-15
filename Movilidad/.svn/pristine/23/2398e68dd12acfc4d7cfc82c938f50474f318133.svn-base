package co.gov.movilidadbogota.sipa.conf;


import co.gov.movilidadbogota.sipa.data.gen.Constants;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * Created by Diego Gomez on 27/06/2017.
 */
//@Configuration
//@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/v" + Constants.API_VERSION + "/**"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "SIPA API",
                "Servicios del sistema SIPA",
                "1.0",
                "http://movilidadbogota.gov.co/sipa/terminos",
                new Contact("Ingenian Software SAS", "http://www.ingenian.com", "sipa@ingenian.com"),
                "Licencia de uso exclusivo para el sistema SIPA de la Secretaría Distrital de Movilidad de Bogotá",
                "http://movilidadbogota.gov.co/sipa/licencia",
                new ArrayList<>());
        return apiInfo;
    }

}