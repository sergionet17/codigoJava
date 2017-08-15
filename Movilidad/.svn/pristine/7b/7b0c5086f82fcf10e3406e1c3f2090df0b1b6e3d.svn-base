package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoNotificacion;
import co.gov.movilidadbogota.sipa.data.gen.Mensaje;
import co.gov.movilidadbogota.sipa.data.gen.MensajeRepo;
import co.gov.movilidadbogota.sipa.util.SecurityHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Controlador para el home
 *
 * @autor: lorenzo.pinango
 */
@Controller
public class HomeController extends BaseController {

    @Autowired
    MensajeRepo mensajeRepo;

    @Autowired
    SecurityHelper securityHelper;

    @RequestMapping("/")
    String home(Model model) {
        List<Mensaje> mensajes = mensajeRepo.findAllByUsuarioDestinoAndTipoNotificacionOrderByFechaCreacionDesc(
                securityHelper.getUsuario(Usuario.class),
                TipoNotificacion.PANTALLA
        );
        model.addAttribute("mensajes", mensajes);
        return "index";
    }

    @RequestMapping("/construccion")
    String enConstruccion() {
        return "construccion";
    }

}
