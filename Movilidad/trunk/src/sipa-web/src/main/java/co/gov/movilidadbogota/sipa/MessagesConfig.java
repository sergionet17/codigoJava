package co.gov.movilidadbogota.sipa;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class MessagesConfig {

    @Value("${sipa.dev.rootPath:}")
    String devRootPath;

    @Bean
    @Profile("development")
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource b = new ReloadableResourceBundleMessageSource();
        b.setBasename("file://" + devRootPath + "/sipa-web/src/main/resources/messages");
        b.setDefaultEncoding("UTF-8");
        b.setCacheSeconds(0);
        return b;
    }

}
