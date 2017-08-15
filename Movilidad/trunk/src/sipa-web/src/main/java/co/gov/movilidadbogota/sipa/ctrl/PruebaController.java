package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.loc.DependenciaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by acpreda on 7/7/17.
 */
@Controller
@RequestMapping("/prueba")
public class PruebaController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(PruebaController.class);

    @Autowired
    DependenciaRepo dependenciaRepo;

    @GetMapping("/dependencias")
    public String dependencias(Model model) {
        model.addAttribute(
                "dependencias",
                dependenciaRepo.findAllDependenciasPrimarias()
        );
        return "prueba-dependencias";
    }

    @PostMapping("/dependencias")
    public String dependenciasPost(
            @ModelAttribute("idDependencia")
            @RequestParam("idDependencia")
                    UUID idDependencia,
            Model model
    ) {
        LOG.debug("Dependencia seleccionada: {}", idDependencia);
        model.addAttribute("idDependencia", idDependencia);
        return "prueba-dependencias-post";
    }

}
