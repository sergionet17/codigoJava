package co.gov.movilidadbogota.sipa.conf;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoNotificacion;
import co.gov.movilidadbogota.sipa.data.biz.num.RangoNumeracion;
import co.gov.movilidadbogota.sipa.data.biz.num.RangoNumeracionRepo;
import co.gov.movilidadbogota.sipa.data.util.MensajeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Clase que verifica la configuración del sistema
 */
@Component
public class InitialCheck {

    /**
     * Se relaciona aquí únicamente para asegurar que el trabajo de carga de datos se realiza antes de las
     * verificaciones
     */
    @Autowired
    DatabaseInitialLoader databaseInitialLoader;

    @Autowired
    RangoNumeracionRepo rangoNumeracionRepo;

    @Autowired
    MensajeHelper mensajeHelper;

    @PostConstruct
    public void init() {
        checkExistenciaRangosNumeracion();
    }

    private void checkExistenciaRangosNumeracion() {
        List<RangoNumeracion> rangos = rangoNumeracionRepo.findAll();
        if (rangos.size() == 0) {
            mensajeHelper.enviarMensaje(Usuario.ROOT,
                    "No hay rangos de numeración creados en el sistema",
                    TipoNotificacion.PANTALLA, TipoNotificacion.EMAIL);
        }
    }

}
