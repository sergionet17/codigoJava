package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.cli.SipaServicesClient;
import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.biz.coa.DeudorRepo;
import co.gov.movilidadbogota.sipa.data.biz.coa.FuenteTituloEjecutivoRepo;
import co.gov.movilidadbogota.sipa.data.biz.coa.TituloEjecutivoReferenciaRepo;
import co.gov.movilidadbogota.sipa.data.biz.coa.TituloEjecutivoRepo;
import co.gov.movilidadbogota.sipa.data.biz.comp.*;
import co.gov.movilidadbogota.sipa.data.biz.trans.TipoProcesoContravencional;
import co.gov.movilidadbogota.sipa.data.biz.trans.TipoProcesoContravencionalRepo;
import co.gov.movilidadbogota.sipa.data.biz.trans.TipoPruebaProcesoContravencional;
import co.gov.movilidadbogota.sipa.data.biz.trans.TipoPruebaProcesoContravencionalRepo;
import co.gov.movilidadbogota.sipa.data.doc.*;
import co.gov.movilidadbogota.sipa.data.fin.Entrada;
import co.gov.movilidadbogota.sipa.data.fin.EntradaRepo;
import co.gov.movilidadbogota.sipa.data.fin.FinancieroUtils;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumento;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumentoRepo;
import co.gov.movilidadbogota.sipa.data.loc.Direccion;
import co.gov.movilidadbogota.sipa.data.loc.Email;
import co.gov.movilidadbogota.sipa.data.loc.Telefono;
import co.gov.movilidadbogota.sipa.data.persona.Persona;
import co.gov.movilidadbogota.sipa.data.persona.PersonaRepo;
import co.gov.movilidadbogota.sipa.data.persona.TipoPersona;
import co.gov.movilidadbogota.sipa.util.ControllerUtils;
import co.gov.movilidadbogota.sipa.util.SecurityHelper;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * ComparendoController administra las solicitudes realtivas a comparendos
 *
 * @author maria.rodriguez
 */
@Controller
public class ComparendoController extends BaseController {

    public static final String CONTROLLER_PATH = "/comparendo";
    public static final String CONTROLLER_PATH_CONSULTA = CONTROLLER_PATH + "/consulta";
    public static final String CONTROLLER_PATH_BUSCAR = CONTROLLER_PATH + "/consulta/buscar";
    public static final String CONTROLLER_PATH_GENERAL = CONTROLLER_PATH + "/general";
    public static final String CONTROLLER_PATH_CARTERA = CONTROLLER_PATH + "/cartera";
    public static final String CONTROLLER_PATH_EXPEDIENTE = CONTROLLER_PATH + "/expediente";
    public static final String CONTROLLER_PATH_AUDIENCIAS = CONTROLLER_PATH + "/audiencias";
    public static final String CONTROLLER_PATH_CARTERA_VOLANTE = CONTROLLER_PATH + "/crearVolantePago/";


    private static final Logger LOG = LoggerFactory.getLogger(Comparendo.class);
    @Autowired
    ComparendoRepository comparendoRepository;
    @Autowired
    PersonaRepo personaRepo;
    @Autowired
    AudienciaComparendoRepo audienciaComparendoRepo;
    @Autowired
    private
    ComparendoRepository repository;
    @Autowired
    private
    ComparendoConsulta comparendoConsulta;
    @Autowired
    private
    TipoDocumentoRepo tipoDocumentoRepo;
    @Autowired
    private ComparendoFormValidator comparendoFormValidator;
    @Autowired
    private EntradaRepo entradaRepo;

    @Autowired
    ExpedienteDocumentoRepo expedienteDocumentoRepo;
    @Autowired
    private TituloEjecutivoRepo tituloEjecutivoRepo;
    @Autowired
    private FuenteTituloEjecutivoRepo fuenteTituloEjecutivoRepo;
    @Autowired
    private DeudorRepo deudorRepo;
    @Autowired
    private TituloEjecutivoReferenciaRepo tituloEjecutivoReferenciaRepo;
    @Autowired
    private ConstanciaEjecutoriaAutomaticaRepo constanciaEjecutoriaAutomaticaRepo;
    @Autowired
    private ResolucionAutomaticaDeFalloRepo resolucionAutomaticaDeFalloRepo;

    @Autowired
    private SecurityHelper securityHelper;

    @Autowired
    private SipaServicesClient sipaServicesClient;
    @Autowired
    private TipoPruebaProcesoContravencionalRepo tipoPruebaProcesoContravencionalRepo;

    @Autowired
    private TipoProcesoContravencionalRepo tipoProcesoContravencionalRepo;


    public ComparendoController(ComparendoRepository repository, ComparendoConsulta comparendoConsulta, TipoDocumentoRepo tipoDocumentoRepo, ComparendoFormValidator comparendoFormValidator, TipoPruebaProcesoContravencionalRepo tipoPruebaProcesoContravencionalRepo) {
        this.repository = repository;
        this.comparendoConsulta = comparendoConsulta;
        this.tipoDocumentoRepo = tipoDocumentoRepo;
        this.comparendoFormValidator = comparendoFormValidator;
        this.tipoPruebaProcesoContravencionalRepo = tipoPruebaProcesoContravencionalRepo;
    }

    /**
     * Permite ingresar los parámetros de la consulta de comparendo.
     *
     * @param model
     * @return La vista con el formulario para realizar la consulta.
     */
    @RequestMapping(value = CONTROLLER_PATH_CONSULTA)
    public String consulta(Model model) {
        model.addAttribute("comparendoForm", new ComparendoForm());
        return "comparendo-consulta";
    }

    /**
     * Realiza la consulta deacuerdo a los criterios ingresados en el formulario.
     *
     * @param comparendoForm     Objeto que mapea los campos usados en los criterios de la consulta.
     * @param result             Objeto que contiene los resultados de la validación del formulario y contiene los errores en caso de que se hayan presentado.
     * @param model
     * @param redirectAttributes
     * @return En caso de no devolver errores, devuelve la vista con el listado de comparendos o  comparendo.
     * En caso de encontrar errores, retorna a la vista de consulta indicando los errores en los datos ingresados para consult
     */
    @GetMapping(value = CONTROLLER_PATH_BUSCAR)
    @Transactional
    public String consulta(@Valid @ModelAttribute("comparendoForm") ComparendoForm comparendoForm, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

        if (LOG.isDebugEnabled())
            LOG.debug("Criterios de búsqueda de comparendo: {}", comparendoForm);

        // Verififca si el formulario contiene errores
        comparendoFormValidator.validate(comparendoForm, result);
        if (result.hasErrors()) {
            return "comparendo-consulta";
        }

        try {
            List<Comparendo> c = comparendoConsulta.findComparendo(comparendoForm);
            if (!c.isEmpty()) {
                model.addAttribute("comparendos", c);
            } else {
                model.addAttribute("sinresultados", "No se encontraron resultados, intente nuevamente");
            }
        } catch (Exception e) {
            model.addAttribute(Constants.FLASH_MESSAGE_ERROR, "Ocurrió un error intente más tarde");
            LOG.error("Ocurrió un error", e);
        }

        return "comparendo-consulta";
    }

    /**
     * @param id    Número del comparendo
     * @param model
     * @return Devuelve la vista general del comparendo.
     */
    @RequestMapping(value = CONTROLLER_PATH_GENERAL + "/{id}")
    @Transactional
    public String general(@PathVariable(value = "id") UUID id, Model model) {

        Comparendo comparendo = repository.findOne(id);
        try {
            if (comparendo != null) {
                model.addAttribute("comparendo", comparendo);
                model.addAttribute("activePill", "consulta");
            } else
                model.addAttribute("activePill", "consulta");

        } catch (Exception ex) {
            LOG.error("Ocurrio un error en la comnsulta por número de comparendo");

        }

        return "comparendo-general";
    }

    /**
     * Permite la visualización de la cartera de un comparendo.
     *
     * @param id    del comparendo
     * @param model
     * @return la vista de la cartera correspondiente al número de comparendo.
     */
    @RequestMapping(value = CONTROLLER_PATH_CARTERA + "/{id}")
    @Transactional
    public String cartera(@PathVariable(value = "id") UUID id, Model model) {
        Comparendo comparendo = repository.findOne(id);
        try {
            if (comparendo != null) {
                comparendo.setCartera(FinancieroUtils.entradas(comparendo.getId(), entradaRepo));
                if (!comparendo.getCartera().isEmpty()) {
                    model.addAttribute("comparendo", comparendo);
                    model.addAttribute("cartera", comparendo.getCartera());
                    model.addAttribute("saldo", comparendo.getCartera().stream()
                            .map(Entrada::getValor)
                            .reduce(BigDecimal.ZERO, (x, y) -> x.add(y))
                    );
                    model.addAttribute("activePill", "cartera");
                } else {
                    model.addAttribute("activePill", "cartera");
                }
                model.addAttribute("comparendo", comparendo);
                List<ExpedienteDocumento> relaciones = expedienteDocumentoRepo.findByExpedienteIdOrigen(comparendo.getId());
                model.addAttribute("relaciones", relaciones);
            }

        } catch (Exception ex) {
            LOG.error("Ocurrio un error en la consulta por número de comparendo");

        }
        return "comparendo-cartera";
    }

    /**
     * Permite realizar la consulta del expediente correspondiente al número de comparendo.
     *
     * @param id    del comaprendo
     * @param model
     * @return La vista del expediente correspondiente al número de comparendo.
     */
    @RequestMapping(value = CONTROLLER_PATH_EXPEDIENTE + "/{id}")
    @Transactional
    public String expediente(@PathVariable(value = "id") UUID id, Model model) {
        Comparendo comparendo = repository.findOne(id);
        try {
            if (comparendo != null) {
                List<ExpedienteDocumento> relaciones = expedienteDocumentoRepo.findByExpedienteIdOrigen(comparendo.getId());
                model.addAttribute("relaciones", relaciones);
                model.addAttribute("activePill", "expediente");
                model.addAttribute("comparendo", comparendo);
            }

        } catch (Exception ex) {
            LOG.error("Ocurrio un error en la consulta por número de comparendo");
            model.addAttribute(
                    Constants.FLASH_MESSAGE_ERROR,
                    "Ocurrio un error en la consulta por número de comparendo"
            );
        }
        return "comparendo-expediente";
    }

    @RequestMapping(value = CONTROLLER_PATH_AUDIENCIAS + "/{id}")
    @Transactional
    public String audiencias(@PathVariable(value = "id") UUID id, Model model) {

        try {

            model.addAttribute("activePill", "audiencias");

            Comparendo comparendo = repository.findOne(id);
            model.addAttribute("comparendo", comparendo);

            List<AudienciaComparendo> audiencias = audienciaComparendoRepo.findByComparendo(comparendo);
            model.addAttribute("audiencias", audiencias);

        } catch (Exception ex) {
            LOG.error("Ocurrio un error en la consulta por número de comparendo", ex);
            model.addAttribute(Constants.FLASH_MESSAGE_ERROR, "Ocurrio un error en la consulta por número de comparendo");
        }
        return "comparendo-audiencias";
    }

    @GetMapping(CONTROLLER_PATH + "/{id}/audiencia-aceptacion")
    public String audienciaAceptacion(
            @PathVariable("id") UUID comparendoId,
            Model model
    ) {
        Comparendo c = comparendoRepository.findOne(comparendoId);
        model.addAttribute("comparendo", c);
        model.addAttribute("activePill", "audiencias");
        AudienciaComparendo audienciaComparendo = new AudienciaComparendo();
        audienciaComparendo.setTipo(TipoAudienciaComparendo.AUDIENCIA_ACEPTACION);
        audienciaComparendo.setAbogado(securityHelper.getUsuario(Usuario.class));
        audienciaComparendo.setComparendo(c);
        audienciaComparendo.setFecha(new Date());
        model.addAttribute("audiencia", audienciaComparendo);
        return "comparendo-audiencia-aceptacion";
    }

    @GetMapping(CONTROLLER_PATH + "/{comparendoId}/audiencia/{audienciaId}/sustanciar")
    @Transactional
    public String audienciaAceptacionSustanciar(
            @PathVariable("comparendoId") UUID comparendoId,
            @PathVariable("audienciaId") UUID audienciaId,
            Model model
    ) {
        AudienciaComparendo audienciaComparendo = audienciaComparendoRepo.getOne(audienciaId);
        model.addAttribute("audiencia", audienciaComparendo);
        model.addAttribute("comparendo", audienciaComparendo.getComparendo());
        model.addAttribute("activePill", "audiencias");
        try {
            InputStream in = null;
            if (audienciaComparendo.getTipo().equals(TipoAudienciaComparendo.AUDIENCIA_ACEPTACION)) {
                in = this.getClass().getResourceAsStream("/doc-templates/audiencia-aceptacion.docx");
            } else {
                in = this.getClass().getResourceAsStream("/doc-templates/audiencia-impugnacion.docx");
            }

            //InputStream in = new FileInputStream("/home/acpreda/projects/sipa/sipa-web/src/main/resources/doc-templates/audiencia-aceptacion.docx");
            if (in == null) {
                throw new Exception("No se encontró la plantilla");
            }
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(in, TemplateEngineKind.Freemarker);
            IContext context = report.createContext();
            context.put("audiencia", audienciaComparendo);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            //OutputStream out = new FileOutputStream(new File("/home/acpreda/tmp/resultado.docx"));
            report.process(context, out);

            Documento response = sipaServicesClient.getDocumentos().addDocumento(
                    new Documento(
                            Constants.MEDIA_TYPE_MSWORD,
                            "audiencia-comparendo.docx",
                            TipoDocumental.AUDIENCIA
                    ),
                    new Expediente(audienciaComparendo.getComparendo().getId()),
                    out.toByteArray()
            );
            if (response == null) {
                LOG.error("Respuesta nula!");
            } else {
                LOG.info("Respuesta: {}, {}", response.getId(), response.getOriginalName());
                audienciaComparendo.setDocumento(response);
                // TODO: Cambio al esquema de expediente
                /*
                carpetaDocumentoRepo.save(new CarpetaDocumento(
                        audienciaComparendo.getComparendo().getCarpeta(),
                        audienciaComparendo.getDocumento()
                ));
                */
            }
        } catch (Exception e) {
            LOG.error("Generando el documento", e);
        }

        return "comparendo-audiencia-aceptacion-sustanciar";
    }

    @PostMapping(CONTROLLER_PATH + "/{id}/audiencia-aceptacion")
    public String audienciaAceptacion(
            @PathVariable("id") UUID comparendoId,
            @Valid @ModelAttribute("audiencia") AudienciaComparendo audienciaComparendo,
            HttpServletRequest request
    ) {

        if (StringUtils.isBlank(request.getParameter("tieneDeudorSolidario")))
            audienciaComparendo.setDeudorSolidario(null);
        if (StringUtils.isBlank(request.getParameter("menorDeEdad")))
            audienciaComparendo.setRepresentanteLegal(null);

        // Campos que superan el modelo y deben recibirse por separado
        if (audienciaComparendo.getDeudorSolidario() != null) {
            Persona deudor = audienciaComparendo.getDeudorSolidario();
            String direccionDeudor = request.getParameter("deudorSolidario.direccion");
            if (StringUtils.isNotBlank(direccionDeudor))
                deudor.setDirecciones(Arrays.asList(new Direccion(deudor, direccionDeudor)));
            String telefonoDeudor = request.getParameter("deudorSolidario.telefono");
            if (StringUtils.isNotBlank(telefonoDeudor))
                deudor.setTelefonos(Arrays.asList(new Telefono(deudor, telefonoDeudor)));
            String emailDeudor = request.getParameter("deudorSolidario.correo");
            if (StringUtils.isNotBlank(emailDeudor))
                deudor.setEmails(Arrays.asList(new Email(deudor, emailDeudor)));

            Persona x = personaRepo.findByTipoDocumentoIdentidadAndNumeroDocumentoIdentidad(
                    deudor.getTipoDocumentoIdentidad(),
                    deudor.getNumeroDocumentoIdentidad()
            );
            if (x != null) {
                audienciaComparendo.setDeudorSolidario(x);
            } else {
                deudor.setId(UUID.randomUUID());
                deudor.setTipoPersona(TipoPersona.NATURAL);
                personaRepo.save(deudor);
            }
        }
        if (audienciaComparendo.getRepresentanteLegal() != null) {
            Persona rep = audienciaComparendo.getRepresentanteLegal();
            String direccionRep = request.getParameter("representanteLegal.direccion");
            if (StringUtils.isNotBlank(direccionRep))
                rep.setDirecciones(Arrays.asList(new Direccion(rep, direccionRep)));
            String telefonoRep = request.getParameter("representanteLegal.telefono");
            if (StringUtils.isNotBlank(telefonoRep))
                rep.setTelefonos(Arrays.asList(new Telefono(rep, telefonoRep)));
            String emailRep = request.getParameter("representanteLegal.correo");
            if (StringUtils.isNotBlank(emailRep))
                rep.setEmails(Arrays.asList(new Email(rep, emailRep)));

            Persona x = personaRepo.findByTipoDocumentoIdentidadAndNumeroDocumentoIdentidad(
                    rep.getTipoDocumentoIdentidad(),
                    rep.getNumeroDocumentoIdentidad()
            );
            if (x != null) {
                audienciaComparendo.setRepresentanteLegal(x);
            } else {
                rep.setId(UUID.randomUUID());
                rep.setTipoPersona(TipoPersona.NATURAL);
                personaRepo.save(rep);
            }
        }

        // Obtiene el comparendo para cambiarle el estado y poner la decisión de contraventor
        Comparendo comparendo = comparendoRepository.findOne(comparendoId);
        comparendo.setDecision(DecisionComparendo.CONTRAVENTOR);
        comparendo.setEstado(EstadoComparendo.EN_FIRME);

        // Pone los valores por defecto para este tipó de audiencia
        audienciaComparendo.setTipo(TipoAudienciaComparendo.AUDIENCIA_ACEPTACION);
        audienciaComparendo.setAbogado(securityHelper.getUsuario(Usuario.class));
        audienciaComparendo.setComparendo(comparendo);
        audienciaComparendo.setFecha(new Date());

        audienciaComparendo.setId(UUID.randomUUID());
        audienciaComparendoRepo.save(audienciaComparendo);

        return String.format(
                "redirect:%s/%s/audiencia/%s/sustanciar",
                CONTROLLER_PATH,
                comparendoId,
                audienciaComparendo.getId()
        );
    }

    @GetMapping(CONTROLLER_PATH + "/{id}/audiencia-impugnacion")
    public String audienciaImpugnacion(
            @PathVariable("id") UUID comparendoId,
            Model model
    ) {
        Comparendo comparendo = comparendoRepository.findOne(comparendoId);
        model.addAttribute("comparendo", comparendo);
        model.addAttribute("activePill", "audiencias");
        AudienciaComparendo audienciaComparendo = new AudienciaComparendo();

        audienciaComparendo.setTipo(TipoAudienciaComparendo.AUDIENCIA_IMPUGNACION);
        audienciaComparendo.setAbogado(securityHelper.getUsuario(Usuario.class));
        audienciaComparendo.setComparendo(comparendo);
        audienciaComparendo.setFecha(new Date());
        model.addAttribute("audiencia", audienciaComparendo);
        return "comparendo-audiencia-impugnacion";
    }

    @PostMapping(CONTROLLER_PATH + "/{id}/audiencia-impugnacion")
    public String audienciaImpugnacion(
            @PathVariable("id") UUID comparendoId,
            @Valid @ModelAttribute("audiencia") AudienciaComparendo audienciaComparendo,
            HttpServletRequest request
    ) {


        // Campos que superan el modelo y deben recibirse por separado
        if (audienciaComparendo.getApoderado() != null) {
            Persona apoderado = audienciaComparendo.getApoderado();
            String direccionApoderado = request.getParameter("apoderado.direccion");
            if (StringUtils.isNotBlank(direccionApoderado))
                apoderado.setDirecciones(Arrays.asList(new Direccion(apoderado, direccionApoderado)));
            String telefonoApoderado = request.getParameter("apoderado.telefono");
            if (StringUtils.isNotBlank(telefonoApoderado))
                apoderado.setTelefonos(Arrays.asList(new Telefono(apoderado, telefonoApoderado)));
            String emailApoderado = request.getParameter("apoderado.correo");
            if (StringUtils.isNotBlank(emailApoderado))
                apoderado.setEmails(Arrays.asList(new Email(apoderado, emailApoderado)));

            Persona persona = personaRepo.findByTipoDocumentoIdentidadAndNumeroDocumentoIdentidad(
                    apoderado.getTipoDocumentoIdentidad(),
                    apoderado.getNumeroDocumentoIdentidad()
            );
            if (persona != null) {
                audienciaComparendo.setApoderado(persona);
            } else {
                apoderado.setId(UUID.randomUUID());
                apoderado.setTipoDocumentoIdentidad(TipoDocumento.CC);
                apoderado.setTipoPersona(TipoPersona.NATURAL);
                personaRepo.save(apoderado);
            }
        }
        if (audienciaComparendo.getRepresentanteLegal() != null) {
            Persona representanteLegal = audienciaComparendo.getRepresentanteLegal();
            String direccionRep = request.getParameter("representanteLegal.direccion");
            if (StringUtils.isNotBlank(direccionRep))
                representanteLegal.setDirecciones(Arrays.asList(new Direccion(representanteLegal, direccionRep)));
            String telefonoRep = request.getParameter("representanteLegal.telefono");
            if (StringUtils.isNotBlank(telefonoRep))
                representanteLegal.setTelefonos(Arrays.asList(new Telefono(representanteLegal, telefonoRep)));
            String emailRep = request.getParameter("representanteLegal.correo");
            if (StringUtils.isNotBlank(emailRep))
                representanteLegal.setEmails(Arrays.asList(new Email(representanteLegal, emailRep)));

            Persona personaRepresentante = personaRepo.findByTipoDocumentoIdentidadAndNumeroDocumentoIdentidad(
                    representanteLegal.getTipoDocumentoIdentidad(),
                    representanteLegal.getNumeroDocumentoIdentidad()
            );
            if (personaRepresentante != null) {
                audienciaComparendo.setRepresentanteLegal(personaRepresentante);
            } else {
                representanteLegal.setId(UUID.randomUUID());
                representanteLegal.setTipoPersona(TipoPersona.NATURAL);
                personaRepo.save(representanteLegal);
            }
        }

        // Obtiene el comparendo para cambiarle el estado y poner la decisión de contraventor
        Comparendo comparendo = comparendoRepository.findOne(comparendoId);
        comparendo.setDecision(DecisionComparendo.CONTRAVENTOR);
        comparendo.setEstado(EstadoComparendo.EN_FIRME);

        // Pone los valores por defecto para este tipó de audiencia
        audienciaComparendo.setTipo(TipoAudienciaComparendo.AUDIENCIA_IMPUGNACION);
        audienciaComparendo.setAbogado(securityHelper.getUsuario(Usuario.class));
        audienciaComparendo.setComparendo(comparendo);
        audienciaComparendo.setFecha(new Date());

        audienciaComparendo.setId(UUID.randomUUID());
        audienciaComparendoRepo.save(audienciaComparendo);

        return String.format("redirect:%s/%s/audiencia/%s/sustanciar", CONTROLLER_PATH, comparendoId, audienciaComparendo.getId());
    }

    @GetMapping(CONTROLLER_PATH + "/{id}/audiencia-impugnacion-decision")
    public String audienciaImpugnacionDecision(
            @PathVariable("id") UUID comparendoId,
            Model model
    ) {
        Comparendo c = comparendoRepository.findOne(comparendoId);
        model.addAttribute("comparendo", c);
        model.addAttribute("activePill", "audiencias");
        AudienciaComparendo audienciaComparendo = new AudienciaComparendo();
        model.addAttribute("audiencia", audienciaComparendo);
        return "comparendo-impugnacion-decision";
    }

    @GetMapping(CONTROLLER_PATH + "/{id}/audiencia-impugnacion-pruebas")
    public String audienciaPruebaDocumento(
            @PathVariable("id") UUID comparendoId,
            Model model
    ) {
        Comparendo c = comparendoRepository.findOne(comparendoId);
        model.addAttribute("comparendo", c);
        model.addAttribute("activePill", "comparendo");
        return "comparendo-impugnacion-pruebas";
    }


    @PostMapping(CONTROLLER_PATH + "/{id}/audiencia-impugnacion-pruebas")
    public String audienciaPruebaDocumento(
            @RequestParam("id") UUID comparendoId,
            @ModelAttribute("comparendo") Comparendo comparendo,
            Locale locale, Model model,
            @RequestPart("documento") MultipartFile documentoMultipart,
            HttpServletRequest request) {

        Documento documento = null;

        try {
            documento = sipaServicesClient.getDocumentos().addDocumento(
                    new Documento(
                            documentoMultipart.getContentType(),
                            documentoMultipart.getOriginalFilename(),
                            TipoDocumental.COMPARENDO
                    ),
                    new Expediente(comparendoId),
                    documentoMultipart.getBytes()
            );

            TipoProcesoContravencional tipoProcesoContravencional = new TipoProcesoContravencional();
            tipoProcesoContravencional.setId(UUID.randomUUID());
            tipoProcesoContravencional.setDocumento(documento);
            if (comparendo.getTipoPruebaProcesoContravencional().getId().equals(TipoPruebaProcesoContravencional.OTRO.getId())) {
                String otros = request.getParameter("otros");
                tipoProcesoContravencional.setOtros(otros);
            }
            tipoProcesoContravencional.setTipoPruebaProcesoContravencional(tipoPruebaProcesoContravencionalRepo.findOne(comparendo.getTipoPruebaProcesoContravencional().getId()));


            tipoProcesoContravencionalRepo.save(tipoProcesoContravencional);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return String.format("redirect:%s/%s", CONTROLLER_PATH_EXPEDIENTE, comparendoId);
    }


    @ModelAttribute("tipoDocumento")
    public List<TipoDocumento> tipoDocumento() {
        return tipoDocumentoRepo.findAll();
    }

    @ModelAttribute("tipoDocumentoMap")
    public Map<String, String> tipoDocumentoMap() {
        List<TipoDocumento> list = tipoDocumento();
        return ControllerUtils.listToMap(list, "sigla,nombre");
    }

    @ModelAttribute("tipoPrueba")
    public List<TipoPruebaProcesoContravencional> tipoPrueba() {
        return tipoPruebaProcesoContravencionalRepo.findAll();
    }

    @ModelAttribute("tipoPruebaMap")
    public Map<String, String> tipoPruebaMap() {
        List<TipoPruebaProcesoContravencional> list = tipoPrueba();
        return ControllerUtils.listToMap(list, "nombre");
    }

    @GetMapping(CONTROLLER_PATH_CARTERA_VOLANTE + "/{comparendoId}")
    public String generarVolantePago(
            @PathVariable("comparendoId") UUID comparendoId,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Documento documento = sipaServicesClient.getVolantePago().generarVolantePago(comparendoId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "comparendo-cartera";
    }
}