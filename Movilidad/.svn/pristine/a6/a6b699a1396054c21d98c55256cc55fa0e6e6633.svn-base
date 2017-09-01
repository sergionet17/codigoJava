package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.Abogado;
import co.gov.movilidadbogota.sipa.data.biz.comp.AbogadoRepo;
import co.gov.movilidadbogota.sipa.data.biz.trans.AbogadoDocumentoRemitido;
import co.gov.movilidadbogota.sipa.data.biz.trans.AbogadoDocumentoRemitidoRepo;
import co.gov.movilidadbogota.sipa.data.biz.trans.DocumentoInfraccion;
import co.gov.movilidadbogota.sipa.data.biz.trans.DocumentoInfraccionRepo;
import co.gov.movilidadbogota.sipa.data.doc.TipoDocumental;
import co.gov.movilidadbogota.sipa.util.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Controlador para registrar los repartos de radicaciones
 * Created by oscar.mellizo on 3/08/2017.
 */
@Controller
public class TransPublicoRepartoController extends BaseController{
    public static final String CONTROLLER_PATH = "/transporte_publico_reparto";
    public static final String CONTROLLER_PATH_CONSULTA = CONTROLLER_PATH + "/general";
    public static final String CONTROLLER_PATH_ASIGNAR = CONTROLLER_PATH +"/asignar";

    private static final Logger LOG = LoggerFactory.getLogger(TransPublicoRepartoController.class);

    @Autowired
    private DocumentoInfraccionRepo documentoInfraccionRepo;
    @Autowired
    private AbogadoRepo abogadoRepo;
    @Autowired
    private AbogadoDocumentoRemitidoRepo abogadoDocumentoRemitidoRepo;


    @GetMapping(value = CONTROLLER_PATH_CONSULTA)
    public String consultar(Model model) {
        List<DocumentoInfraccion> documentosInfraccion = documentoInfraccionRepo.findByDocumentoTipoDocumental(TipoDocumental.INFRACCION_TRANSPORTE_PUBLICO);
        model.addAttribute("documentosInfraccion", documentosInfraccion);
        List<Abogado> abogados = abogadoRepo.findAll();
        model.addAttribute("sustanciadores", abogados);
        model.addAttribute("abogado", new Abogado());

        return "tp-reparto-general";
    }

    @PostMapping(value = CONTROLLER_PATH_ASIGNAR)
    public String asignar(HttpServletRequest request, Model model){
        String suscitadorId = request.getParameter("suscitadorId");
        Abogado abogado = abogadoRepo.findOne(UUID.fromString(suscitadorId));

        String[] infraccionesSelectedIds = request.getParameterValues("infraccionesSelectedIds");

        for (int x = 0; x < infraccionesSelectedIds.length; x++){
            AbogadoDocumentoRemitido documentoRemitido = new AbogadoDocumentoRemitido();
            documentoRemitido.setId(UUID.randomUUID());
            documentoRemitido.setAbogado(abogado);

            DocumentoInfraccion documentoInfraccion = documentoInfraccionRepo.findOne(UUID.fromString(infraccionesSelectedIds[x]));
            documentoRemitido.setDocumentoInfraccion(documentoInfraccion);

            abogadoDocumentoRemitidoRepo.save(documentoRemitido);
        }

        return String.format("redirect:%s", CONTROLLER_PATH_CONSULTA);
    }

    /*@ModelAttribute("abogados")
    public List<Abogado> abogados() {
        return abogadoRepo.findAll();
    }

    @ModelAttribute("abogadosMap")
    public Map<String, String> abogadosMap() {
        List<Abogado> abogados = abogados();
        return ControllerUtils.listToMap(abogados, "persona.primerNombre");
    }*/
}
