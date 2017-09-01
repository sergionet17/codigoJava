package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.Abogado;
import co.gov.movilidadbogota.sipa.data.biz.comp.AbogadoRepo;
import co.gov.movilidadbogota.sipa.data.biz.trans.DocumentoInfraccion;
import co.gov.movilidadbogota.sipa.data.biz.trans.DocumentoInfraccionRepo;
import co.gov.movilidadbogota.sipa.data.doc.TipoDocumental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Controlador para registrar los repartos de radicaciones
 * Created by oscar.mellizo on 3/08/2017.
 */
@Controller
public class TransPublicoRepartoController {
    public static final String CONTROLLER_PATH = "/transporte_publico_reparto";
    public static final String CONTROLLER_PATH_CONSULTA =  CONTROLLER_PATH + "/general";
    public static final String CONTROLLER_PATH_ASIGNAR =   CONTROLLER_PATH + "/assign";

    @Autowired
    private DocumentoInfraccionRepo documentoInfraccionRepo;
    @Autowired
    private AbogadoRepo abogadoRepo;


    @GetMapping(value = CONTROLLER_PATH_CONSULTA)
    public String consultar(Model model) {
        List<DocumentoInfraccion> documentosInfraccion = documentoInfraccionRepo.findByDocumentoTipoDocumental(TipoDocumental.INFRACCION_TRANSPORTE_PUBLICO);
        model.addAttribute("documentosInfraccion", documentosInfraccion);
        List<Abogado> abogados = abogadoRepo.findAll();
        model.addAttribute("sustanciadores", abogados);

        return "tp-reparto-general";
    }

    @RequestMapping(value = CONTROLLER_PATH_ASIGNAR, method = RequestMethod.POST)
    public String asignar(HttpServletRequest request, Model model){
        String[] paramValues = request.getParameterValues("selec");
        return "tp-reparto-general";
    }
}
