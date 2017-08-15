package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.doc.Plantilla;
import co.gov.movilidadbogota.sipa.data.doc.PlantillaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador para la administración de plantillas de documentos
 */
@Controller
public class PlantillaController {

    public static final String PATH = "/plantilla";
    public static final String PATH_LIST = PATH;
    public static final String PATH_NEW = PATH + "/new";

    @Autowired
    private PlantillaRepo plantillaRepo;

    @GetMapping(PATH_LIST)
    public String list(Model model) {
        model.addAttribute("list", plantillaRepo.findAll(new Sort(Sort.Direction.ASC, "nombre")));
        return "plantilla-list";
    }

    @GetMapping(PATH_NEW)
    public String newPlantilla(Plantilla plantilla, BindingResult errors) {
        return "plantilla-new";
    }

}
