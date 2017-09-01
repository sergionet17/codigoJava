package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.trans.AveriguacionPreliminar;
import co.gov.movilidadbogota.sipa.data.biz.trans.AveriguacionPreliminarRepo;
import co.gov.movilidadbogota.sipa.data.biz.trans.DocumentoInfraccion;
import co.gov.movilidadbogota.sipa.data.doc.TipoDocumental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class TransPublicoAveriguacionesPreliminaresController {
    public static final String CONTROLLER_PATH = "/transporte_publico_averiguaciones";
    public static final String CONTROLLER_PATH_CONSULTA =  CONTROLLER_PATH + "/general";

    @Autowired
    private AveriguacionPreliminarRepo averiguacionPreliminarRepo;

    @GetMapping(value = CONTROLLER_PATH_CONSULTA)
    public String consultar(Model model) {

        List<AveriguacionPreliminar> averiguaciones = averiguacionPreliminarRepo.findAll();
        model.addAttribute("averiguacionesPreliminares", averiguaciones);

        return "tp-averiguacion-preliminar";
    }
}
