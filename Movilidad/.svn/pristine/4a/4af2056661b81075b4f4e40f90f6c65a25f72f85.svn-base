package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.cli.SipaServicesClient;
import co.gov.movilidadbogota.sipa.data.biz.num.AnulacionNumeracion;
import co.gov.movilidadbogota.sipa.data.biz.num.AnulacionNumeracionRepo;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.doc.TipoDocumental;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Controller
@RequestMapping(value = RegistroEvidenciaAnulacionRangosController.CONTROLLER_PATH)
public class RegistroEvidenciaAnulacionRangosController extends BaseController {
    public static final String CONTROLLER_PATH_EVIDENCIA = "/subirEvidencia";
    public static final String CONTROLLER_PATH = "/evidenciaAnulacion";
    public static final String CONTROLLER_PATH_LIST = "";
    public static final String CONTROLLER_PATH_GUARDAR_EVIDENCIA = "/guardarEvidencia";

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioController.class);


    @Autowired
    AnulacionNumeracionRepo anulacionNumeracionRepo;

    @Autowired
    MessageSource messages;

    @Autowired
    SipaServicesClient sipaServicesClient;

    @GetMapping(value = CONTROLLER_PATH_LIST)
    public String getAnulacionRangos(Model model) {
        List<AnulacionNumeracion> anulacionRangos;

        LOG.debug("Buscar los rangos de numeracion");
        anulacionRangos = consultarLosRangosDeNumeracion();
        model.addAttribute("anulacionRangos", anulacionRangos);
        return "anulacion-rangos";
    }


    private List<AnulacionNumeracion> consultarLosRangosDeNumeracion() {
        return anulacionNumeracionRepo.findAll();
    }

    @GetMapping(value = CONTROLLER_PATH_EVIDENCIA + "/{id}")
    public String getAnulacionItem(@PathVariable(value = "id") UUID id, Model model) {

        AnulacionNumeracion anulacionNumeracion = anulacionNumeracionRepo.findOne(id);
        LOG.debug("Buscar anulación rango de numeracion");
        model.addAttribute("anulacionNumeracion", anulacionNumeracion);
        return "evidencia-anulacion";
    }

    @PostMapping(value = CONTROLLER_PATH_GUARDAR_EVIDENCIA)
    public String saveAnulacionItem(@ModelAttribute("anulacionNumeracion") AnulacionNumeracion anulacionNumeracion,
                                    Model model, @RequestParam("documento") MultipartFile imagenEvidencia,
                                    BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {

        List<AnulacionNumeracion> anulacionRangos;
        Documento documento = null;

        try {
            if (!result.hasErrors() && imagenEvidencia != null && !imagenEvidencia.isEmpty()) {
                documento = sipaServicesClient.getDocumentos().addDocumento(
                        new Documento(
                                imagenEvidencia.getContentType(),
                                imagenEvidencia.getOriginalFilename(),
                                TipoDocumental.EVIDENCIA_ANULACION_RANGOS
                        ),
                        imagenEvidencia.getBytes()
                );
            }

            if (documento != null) {
                AnulacionNumeracion anulacionNumeracionDB = anulacionNumeracionRepo.findOne(anulacionNumeracion.getId());
                anulacionNumeracionDB.setResolucion(documento);
                anulacionNumeracionRepo.save(anulacionNumeracionDB);
                LOG.debug("Buscar anulación rango de numeracion");

                redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                        messages.getMessage("evidencia.create.success", new Object[]{}, locale));
                return String.format("redirect:%s", CONTROLLER_PATH_LIST);
            } else {
                redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_ERROR,
                        messages.getMessage("evidencia.create.error", new Object[]{}, locale));
                return String.format("redirect:%s", CONTROLLER_PATH_LIST);
            }
            // return "anulacion-rangos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_ERROR,
                    messages.getMessage("evidencia.create.error", new Object[]{}, locale));
            return String.format("redirect:%s", CONTROLLER_PATH_LIST);
        }


    }

}
