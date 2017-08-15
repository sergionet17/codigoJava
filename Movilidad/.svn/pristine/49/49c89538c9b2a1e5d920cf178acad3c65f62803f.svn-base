package co.gov.movilidadbogota.sipa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@EnableScheduling
@EnableJdbcHttpSession
@EnableConfigurationProperties
public class WebApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebApplication.class);

    public static void main(String[] args) {
        String version = WebApplication.class.getPackage().getImplementationVersion();
        LOGGER.info("Versión: {}", version);
        SpringApplication.run(WebApplication.class, args);
    }

}
