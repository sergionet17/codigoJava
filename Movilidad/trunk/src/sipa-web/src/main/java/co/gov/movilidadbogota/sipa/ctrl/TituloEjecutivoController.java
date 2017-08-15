package co.gov.movilidadbogota.sipa.ctrl;


import co.gov.movilidadbogota.sipa.cli.SipaServicesClient;
import co.gov.movilidadbogota.sipa.data.biz.coa.*;
import co.gov.movilidadbogota.sipa.data.biz.num.AnulacionNumeracion;
import co.gov.movilidadbogota.sipa.data.doc.Actuacion;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.doc.Expediente;
import co.gov.movilidadbogota.sipa.data.doc.TipoDocumental;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumento;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumentoRepo;
import co.gov.movilidadbogota.sipa.data.loc.DependenciaRepo;
import co.gov.movilidadbogota.sipa.data.loc.Direccion;
import co.gov.movilidadbogota.sipa.data.loc.Email;
import co.gov.movilidadbogota.sipa.data.loc.Telefono;
import co.gov.movilidadbogota.sipa.data.persona.Persona;
import co.gov.movilidadbogota.sipa.data.persona.PersonaRepo;
import co.gov.movilidadbogota.sipa.data.persona.TipoPersona;
import co.gov.movilidadbogota.sipa.util.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

@Controller
public class TituloEjecutivoController extends BaseController {

    public static final String CONTROLLER_PATH = "/titulosEjecutivos";
    public static final String CONTROLLER_PATH_CREATE = CONTROLLER_PATH + "/new";
    public static final String CONTROLLER_PATH_LIST = CONTROLLER_PATH + "/list";
    public static final String CONTROLLER_PATH_SAVE_DOCUMENT = CONTROLLER_PATH + "/saveDocument";
    public static final String CONTROLLER_PATH_SAVE_PERSONA = CONTROLLER_PATH + "/savePersona";
    public static final String CONTROLLER_PATH__GET_PERSONA = CONTROLLER_PATH + "/getPersona";
    public static final String CONTROLLER_PATH_SAVE_TITULO = CONTROLLER_PATH + "/saveTitulo";


    private static final Logger LOG = LoggerFactory.getLogger(TituloEjecutivoController.class);
    @Autowired
    public TituloEjecutivoRepo tituloEjecutivoRepo;

    @Autowired
    public DependenciaRepo dependenciaRepo;

    @Autowired
    private
    TipoDocumentoRepo tipoDocumentoRepo;

    @Autowired
    private PersonaRepo personaRepo;

    @Autowired
    private DeudorRepo deudorRepo;

    @Autowired
    private FuenteTituloEjecutivoRepo fuenteTituloEjecutivoRepo;

    @Autowired
    private SipaServicesClient sipaServicesClient;


    @GetMapping(value = CONTROLLER_PATH_LIST)
    public String titulosEjecutivosConsulta(Model model) {
        List<TituloEjecutivo> tituloEjecutivos;
        try {
            tituloEjecutivos = tituloEjecutivoRepo.findAll();
            model.addAttribute("titulosEjecutivos", tituloEjecutivos);
        } catch (Exception e) {

        }
        return "titulos-ejecutivos-consulta";
    }


    @GetMapping(value = CONTROLLER_PATH_CREATE)
    public String crearNuevoTituloEjecutivo(Model model) {
        List<Documento> documentos = new ArrayList<>();
        List<Deudor> deudores = new ArrayList<>();

        try {
            model.addAttribute("documentos", documentos);
            model.addAttribute("deudores", deudores);
            model.addAttribute("tituloEjecutivo", new TituloEjecutivo());
            model.addAttribute("personaModal", new Persona());
            model.addAttribute("dependencias", dependenciaRepo.findAllDependenciasPrimarias());
            model.addAttribute("tiposIdentificacion", ControllerUtils.listToMap(tipoDocumentoRepo.findAll(), "nombre"));
            model.addAttribute("personas", ControllerUtils.listToMap(personaRepo.findAll(),
                    "primerNombre,segundoNombre," +
                            "primerApellido,segundoApellido"));
            model.addAttribute("tiposFuente",
                    ControllerUtils.listToMap(fuenteTituloEjecutivoRepo.
                            findByIdIn(Arrays.asList(FuenteTituloEjecutivo.OTROS_COBROS_OBJ.getId(),
                                    FuenteTituloEjecutivo.MULTAS_OBJ.getId())), "nombre"));

        } catch (Exception e) {

        }

        return "titulo-ejecutivo-new";
    }



  /*  @PostMapping(value=CONTROLLER_PATH_SAVE_DOCUMENT, produces = "application/json")
    @ResponseBody
    public String crearNuevoTituloEjecutivo(@RequestParam("documento") MultipartFile soporte,
                                            BindingResult result ,
                                            RedirectAttributes redirectAttributes,
                                            Locale locale){
        try {
            if (!result.hasErrors() && soporte != null && !soporte.isEmpty()) {
                Carpeta carpeta;
                carpeta = new Carpeta();
            }
        }catch (Exception e){

        }
        return "";
    }*/



   /* @RequestMapping(value =CONTROLLER_PATH_SAVE_DOCUMENT, method = RequestMethod.POST )
    public @ResponseBody String executeSampleService( @RequestParam("documento") MultipartFile file) {
        Carpeta carpeta;
        carpeta = new Carpeta();
        LOG.debug("llego");
        return ""; //projectService.executeSampleService(project, file);
    }
*/

    @PostMapping(value = CONTROLLER_PATH_SAVE_DOCUMENT, produces = "application/json")
    @ResponseBody
    public Documento saveDocument(Locale locale, Model model,
                                  @RequestParam("documento") MultipartFile documentoMultipart) {
        LOG.debug("llego");
        List<AnulacionNumeracion> anulacionRangos;
        Documento documento = null;


        DataBinder dataBinder = new DataBinder(model);
        BindingResult result = dataBinder.getBindingResult();

        try {
            documento = sipaServicesClient.getDocumentos().addDocumento(
                    new Documento(
                            documentoMultipart.getContentType(),
                            documentoMultipart.getOriginalFilename(),
                            TipoDocumental.TITULO_EJECUTIVO_DOC
                    ),
                    documentoMultipart.getBytes()
            );
         /*   if (documentoMultipart != null && !documentoMultipart.isEmpty() ){
                documento = guardarDocumento(
                        documentoMultipart,
                        new String[]{Constants.MIME_TYPE_PDF, Constants.MIME_TYPE_MSWORD},
                        result,
                        "resolucion",
                        null);
            }*/
        } catch (Exception e) {
            LOG.debug(e.getMessage());
            crearNuevoTituloEjecutivo(model);
        }
        return documento;
    }


    @PostMapping(value = CONTROLLER_PATH_SAVE_PERSONA, produces = "application/json")
    @ResponseBody
    public Deudor savePersona(Locale locale, Model model, @ModelAttribute("personaModal") Persona persona,
                              @ModelAttribute("porcentajePersona") String PorcentajeDeudor,
                              @ModelAttribute("telefonoPersona") String telefono,
                              @ModelAttribute("correoPersona") String correo,
                              @ModelAttribute("direccionPersona") String direccionPersona) {
        Deudor deudor;
        deudor = new Deudor();
        try {
            LOG.debug("llego aqui");
            Persona personaDb = personaRepo.findByTipoDocumentoIdentidadAndNumeroDocumentoIdentidad
                    (persona.getTipoDocumentoIdentidad(), persona.getNumeroDocumentoIdentidad());

            deudor = new Deudor();


            if (personaDb == null) {
                persona.setTipoDocumentoIdentidad(tipoDocumentoRepo.findOne(persona.getTipoDocumentoIdentidad().getId()));
                persona.setId(UUID.randomUUID());
                persona.setTelefonos(Arrays.asList(new Telefono(persona, telefono)));
                persona.setEmails(Arrays.asList(new Email(persona, correo)));
                persona.setDirecciones(Arrays.asList(new Direccion(persona, direccionPersona)));
                persona.setTipoPersona(TipoPersona.NATURAL);
                deudor.setPersona(persona);
            } else {
                deudor.setPersona(personaDb);

            }
            deudor.setPorcentajeParticipacion(Integer.valueOf(PorcentajeDeudor));
            deudor.setId(UUID.randomUUID());
            deudorRepo.save(deudor);

        } catch (Exception e) {

        }

        return deudor;
    }

    @PostMapping(value = CONTROLLER_PATH_SAVE_TITULO)
    public String saveTitulo(
            @RequestParam("idsDeudores") String idsDeudores,
            @RequestParam("idsDocumentos") String idsDocumentos,
            @Valid @ModelAttribute("tituloEjecutivo") TituloEjecutivo tituloEjecutivo,
            BindingResult result,
            Model model,
            final RedirectAttributes redirectAttributes
    ) {

        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("saveTitulo(idsDedudores=%s, idsDocumentos=%s, tituloEjecutivo=%s)",
                    idsDeudores, idsDocumentos, tituloEjecutivo
            ));
        }

        try {
            tituloEjecutivo.setId(UUID.randomUUID());
            String[] deudoresArray = idsDeudores.split(",");
            String[] documentosArray = idsDocumentos.split(",");

            // List<Deudor> deudores = Arrays.stream(deudoresArray)
            // .map(x -> deudorRepo.findOne(UUID.fromString(x))).collect(Collectors.toList());
            List<Deudor> deudores = new ArrayList();
            Deudor deudor;
            for (String deudorId :
                    deudoresArray
                    ) {
                if (!deudorId.isEmpty()) {
                    deudor = deudorRepo.findOne(UUID.fromString(deudorId));
                    deudores.add(deudor);
                }
            }
            Documento documento;
            List<Documento> documentos = new ArrayList();
            for (String documentoId : documentosArray) {
                if (!documentoId.isEmpty()) {
                    documento = sipaServicesClient.getDocumentos().getDocumento(UUID.fromString(documentoId));
                    documentos.add(documento);
                }
            }


            tituloEjecutivo.setDependencia(dependenciaRepo.findById(tituloEjecutivo.getDependencia().getId()));


            // List<Documento> documentos = Arrays.stream(documentosArray)
            //    .map(x -> documentoRepo.findOne(UUID.fromString(x))).collect(Collectors.toList());

            tituloEjecutivo.setDeudores(deudores);
            tituloEjecutivo.setSoporte(documentos);
            tituloEjecutivo.setEstado(EstadoTitulo.VIGENTE);
            tituloEjecutivo.setEtapaTituloEjecutivo(EtapaTituloEjecutivo.COBRO_PERSUASIVO);
            tituloEjecutivo.setFuenteTituloEjecutivo(fuenteTituloEjecutivoRepo.findById(tituloEjecutivo.getFuenteTituloEjecutivo().getIdentifier()));

            //tituloEjecutivo.setDeudores();
            tituloEjecutivoRepo.save(tituloEjecutivo);

            Expediente expediente = sipaServicesClient.getDocumentos().addExpediente(new Expediente(tituloEjecutivo.getId(), TipoDocumental.TITULO_EJECUTIVO));

            for (Documento documentoAsociar :
                    tituloEjecutivo.getSoporte()) {
                sipaServicesClient.getDocumentos().asociarDocumento(documentoAsociar, expediente, Actuacion.REGISTRO_TITULO_EJECUTIVO);

            }

        } catch (Exception e) {
            //  redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK, "Título ejecutivo creado correctamente");
            //  return String.format("redirect:%s", CONTROLLER_PATH_LIST);
            model.addAttribute("fashError", "Ocurrió un error intente más tarde");
            LOG.error("Ocurrió un error", e);
            redirectAttributes.addFlashAttribute("flashError", "Se presentó un error");

        }
        List<TituloEjecutivo> tituloEjecutivos = tituloEjecutivoRepo.findAll();
        model.addAttribute("titulosEjecutivos", tituloEjecutivos);
        titulosEjecutivosConsulta(model);
        redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK, "Título ejecutivo creado correctamente");
        return String.format("redirect:%s", CONTROLLER_PATH_LIST);
    }


    @GetMapping(value = CONTROLLER_PATH__GET_PERSONA + "/{tipo}/{numero}", produces = "application/json")
    @ResponseBody
    public Persona getPersona(@PathVariable("tipo") Integer idTipoId,
                              @PathVariable("numero") String numeroId) {
        Persona persona;
        TipoDocumento tipoDocumento = tipoDocumentoRepo.findOne(idTipoId);
        persona = personaRepo.findByTipoDocumentoIdentidadAndNumeroDocumentoIdentidad(tipoDocumento, numeroId);
        return persona;
    }

}
