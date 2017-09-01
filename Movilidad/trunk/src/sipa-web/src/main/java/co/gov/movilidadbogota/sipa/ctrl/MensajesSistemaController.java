package co.gov.movilidadbogota.sipa.ctrl;


import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoNotificacion;
import co.gov.movilidadbogota.sipa.data.gen.Mensaje;
import co.gov.movilidadbogota.sipa.data.gen.MensajeRepo;
import co.gov.movilidadbogota.sipa.util.SecurityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

/**
 * Created by diego.gomez on 18/07/2017.
 */
@Controller
@RequestMapping(value = MensajesSistemaController.CONTROLLER_PATH)
public class MensajesSistemaController extends BaseController {

    public static final String CONTROLLER_PATH = "/mensajes";
    public static final String CONTROLLER_PATH_LIST = "";
    public static final String CONTROLLER_PATH_CREATE = "/crearNuevoRango";
    private static final Logger LOG = LoggerFactory.getLogger(MensajesSistemaController.class);

    @Autowired
    MensajeRepo mensajeRepo;

    @Autowired
    private SecurityHelper securityHelper;

    @GetMapping(value = CONTROLLER_PATH_LIST)
    public String getMensajes(Model model, Principal principal) {
        List<Mensaje> listaMensajes;
        LOG.debug("Buscar los mensajes del sistema");
        listaMensajes = consultarMensajesSistema(principal);
        model.addAttribute("mensajes", listaMensajes);
        return "mensajes-consulta";
    }

    private List<Mensaje> consultarMensajesSistema(Principal principal) {
        Usuario usuario = securityHelper.getUsuario(principal.getName(), Usuario.class);
        if (null != usuario) {
                return mensajeRepo.findAllByUsuarioDestinoAndTipoNotificacionOrderByFechaCreacionDesc(usuario, TipoNotificacion.PANTALLA);
        } else {
            LOG.error(String.format("Error al cargar los datos del usuario"));
        }
        return null;
    }
}
