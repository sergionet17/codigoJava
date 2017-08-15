package co.gov.movilidadbogota.sipa.conf;

import co.gov.movilidadbogota.sipa.serv.externos.DuppsWebServicesImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Clase de configuración para el servicio de DUUPS
 *
 * @author arturo.cruz
 */
@Configuration
public class DuupsConfig {

    @Bean
    DuppsWebServicesImpl duupsRepo() {
        return new DuppsWebServicesImpl();
    }

}
