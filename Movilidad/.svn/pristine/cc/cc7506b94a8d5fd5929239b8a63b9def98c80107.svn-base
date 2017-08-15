package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.biz.comp.*;
import co.gov.movilidadbogota.sipa.util.ControllerUtils;
import co.gov.movilidadbogota.sipa.util.SecurityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

/**
 * Controlador que administra las audiencias
 * @author maria.rodruguez
 */
@Controller
@RequestMapping(value = AudienciaController.CONTROLLER_PATH)
public class AudienciaController extends BaseController {

    public static final String CONTROLLER_PATH = "/audiencia";
    public static final String CONTROLLER_PATH_LIST = "/list";

    private static final Logger logger = LoggerFactory.getLogger(AgendamientoAudienciaController.class);

    @Autowired
    AgendamientoAudienciaRepo agendamientoAudienciaRepo;
    @Autowired
    AudienciaComparendoRepo audienciaComparendoRepo;
    @Autowired
    private ComparendoRepository comparendoRepository;
    @Autowired
    private SecurityHelper securityHelper;

    @RequestMapping(value = "/continuar")
    public String continuar(Model model) {
        model.addAttribute("tab", "audiencias");
        return "audiencia-continuar";
    }

    @RequestMapping(value = "/continuar/crear")
    public String continuarCrear(Model model, RedirectAttributes redirect) {
        redirect.addFlashAttribute("SUCCESS", "AudienciaComparendo reprogramada");
        return "redirect:/comparendo/audiencias";
    }

    /**
     * En lista todos losagendamientos pendientes
     *
     * @param model
     * @return Formulario con la lista los agendamientos vigentes
     */

    @RequestMapping(value = CONTROLLER_PATH_LIST + "/{numero}")
    public String list(@PathVariable("numero") Integer numero, Model model, Principal principal, RedirectAttributes redirectAttributes) {
        Comparendo comparendo = comparendoRepository.findByNumeroConsecutivo(numero);

        model.addAttribute("agendas", ControllerUtils.listToMap(agendamientoAudienciaRepo.findAll(), "tipo.nombre"));
        model.addAttribute("agendas2", agendamientoAudienciaRepo.findAll());
        //Carga la informacion del usuario
        Usuario usuario = securityHelper.getUsuario(principal.getName(), Usuario.class);
        if (usuario != null) {
            List<AudienciaComparendo> audienciaComparendos = audienciaComparendoRepo.findByAbogadoId(usuario.getId());
            model.addAttribute("comparendos", audienciaComparendos);
        } else {
            logger.debug("No cuenta con primera audiencias");
        }

        return "programar-audiencia";
    }
}
