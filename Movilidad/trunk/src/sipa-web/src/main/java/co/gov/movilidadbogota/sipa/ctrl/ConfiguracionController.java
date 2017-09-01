package co.gov.movilidadbogota.sipa.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Created by acpreda on 3/15/17.
 */
@Controller
@RequestMapping(value = "/conf")
public class ConfiguracionController {

    @RequestMapping(value = "/audiencia/horarios")
    public String audienciaHorarios(Model model) {
        return "conf-audiencia-horarios";
    }

    @RequestMapping(value = "/audiencia/horarios/nuevo")
    public String audienciaHorariosNuevo(Model model) {
        return "conf-audiencia-horarios-nuevo";
    }

    @RequestMapping(value = "/audiencia/horarios/crear")
    public String audienciaHorariosCrear(Model model, RedirectAttributes redirect) {
        redirect.addFlashAttribute("SUCCESS", "Nuevo horario creado");
        return "redirect:/conf/audiencia/horarios";
    }

}
