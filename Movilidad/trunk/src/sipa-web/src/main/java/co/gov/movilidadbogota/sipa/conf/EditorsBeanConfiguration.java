package co.gov.movilidadbogota.sipa.conf;

import co.gov.movilidadbogota.sipa.bean.MunicipioParametroEditor;
import co.gov.movilidadbogota.sipa.util.ParametroEditor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuracion de los beans de los editores de parámetros
 *
 * @author lorenzo.pinango
 */
@Configuration
public class EditorsBeanConfiguration {

    @Bean(name = "municipoParametroEditor")
    public ParametroEditor getMunicipioParametroEditor() {
        return new MunicipioParametroEditor();
    }

}
