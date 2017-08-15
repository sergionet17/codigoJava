package co.gov.movilidadbogota.sipa.serv.doc;

import co.gov.movilidadbogota.sipa.conf.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by acpreda on 6/27/17.
 */
@Configuration
@EnableConfigurationProperties({ApplicationProperties.class})
public class CmisConfiguration {

    @Bean
    public CmisSessionFactory cmisSessionFactory(@Autowired ApplicationProperties applicationProperties) {
        return new CmisSessionFactory(applicationProperties.getCmis());
    }

}
