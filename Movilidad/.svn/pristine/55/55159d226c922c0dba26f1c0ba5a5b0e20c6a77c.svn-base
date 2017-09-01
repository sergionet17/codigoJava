package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.ComparendoRepository;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumento;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumentoRepo;
import co.gov.movilidadbogota.sipa.data.persona.Persona;
import co.gov.movilidadbogota.sipa.data.persona.PersonaConsulta;
import co.gov.movilidadbogota.sipa.data.persona.PersonaForm;
import co.gov.movilidadbogota.sipa.data.persona.PersonaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

/**
 * PersonaController administra las solicitudes realtivas a Persona
 *
 * @author uthor maria.rodriguez
 */
@Controller
public class PersonaController extends BaseController {

    public static final String CONTROLLER_PATH = "/persona";
    public static final String CONTROLLER_PATH_CONSULTA = CONTROLLER_PATH + "/consulta";
    public static final String CONTROLLER_PATH_BUSCAR = CONTROLLER_PATH + "/consulta/buscar";
    public static final String CONTROLLER_PATH_GENERAL = CONTROLLER_PATH + "/general";
    public static final String CONTROLLER_PATH_ASOCIADOS = CONTROLLER_PATH + "/comparendos-asociados";
    public static final Logger LOG = LoggerFactory.getLogger(PersonaController.class);
    @Autowired
    private
    TipoDocumentoRepo tipoDocumentoRepo;
    @Autowired
    private
    ComparendoRepository comparendoRepository;
    @Autowired
    private PersonaRepo repository;
    @Autowired
    private PersonaFormValidator personaFormValidator;
    @Autowired
    private PersonaConsulta personaConsulta;

    public PersonaController(TipoDocumentoRepo tipoDocumentoRepo, ComparendoRepository comparendoRepository, PersonaRepo repository, PersonaFormValidator personaFormValidator, PersonaConsulta personaConsulta) {
        this.tipoDocumentoRepo = tipoDocumentoRepo;
        this.comparendoRepository = comparendoRepository;
        this.repository = repository;
        this.personaFormValidator = personaFormValidator;
        this.personaConsulta = personaConsulta;
    }

    /**
     * Realiza la consulta de una persona deacuerdo a unos criterios de búsqueda.
     *
     * @param model Defines a holder for model attributes.
     * @return La vista con el formulario de consulta de persona.
     */
    @GetMapping(value = CONTROLLER_PATH_CONSULTA)
    public String consulta(Model model) {


        List<TipoDocumento> tipoDocumento = tipoDocumentoRepo.findAll();
        model.addAttribute("personaForm", new PersonaForm());
        model.addAttribute("tipoDocumento", tipoDocumento);

        return "persona-consulta";
    }

    /**
     * Realiza la consulta de una persona según los criterios ingresados en el formulario.
     *
     * @param personaForm Objeto que mapea los datos ingresados en el formulario de consulta.
     * @param result      Objeto que contiene los resultados de la validación del formulario y contiene los errores en caso de que se hayan presentado.
     * @param model       Defines a holder for model attributes.
     * @return Los resultados de la consulta en caso de no presentarse error o la vista de la consulta indicando los errrores luego de ser validados.
     */
    @PostMapping(value = CONTROLLER_PATH_BUSCAR)
    public String buscar(@Valid @ModelAttribute("personaForm") PersonaForm personaForm, BindingResult result, Model model) {


        personaFormValidator.validate(personaForm, result);
        if (result.hasErrors()) {

            model.addAttribute("tipoDocumento", tipoDocumentoRepo.findAll());
            return "persona-consulta";
        }
        try {

            List<Persona> personas = personaConsulta.findPersona(personaForm);
            if (!personas.isEmpty()) {

                model.addAttribute("personas", personas);
                model.addAttribute("tipoDocumento", tipoDocumentoRepo.findAll());
            } else {

                model.addAttribute("tipoDocumento", tipoDocumentoRepo.findAll());
                model.addAttribute("sinresultados", "sinresultados");
            }
        } catch (Exception e) {
            model.addAttribute("tipoDocumento", tipoDocumentoRepo.findAll());

            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }

        return "persona-consulta";
    }

    /**
     * @param id    de la persona
     * @param model Defines a holder for model attributes.
     * @return La vista general de la persona encontrada.
     */
    @GetMapping(value = CONTROLLER_PATH_GENERAL + "/{id}")
    public String general(@PathVariable(value = "id") UUID id, Model model) {

        Persona persona = repository.findOne(id);
        try {
            if (persona != null) {

                Integer numComparendos = comparendoRepository.countByInfractorId(id);
                model.addAttribute("numComparendos", numComparendos);
                LOG.info("Número de comparendos asociados a la persona" + numComparendos);
                model.addAttribute("persona", persona);
                model.addAttribute("activePill", "consulta");
            } else {
                LOG.info("La persona a consultar no fue encoentrada");
                model.addAttribute("error", "Ocurrió un error intente más tarde");
                model.addAttribute("activePill", "consulta");
            }

        } catch (Exception ex) {

            model.addAttribute("error", "Ocurrió un error intente más tarde");
            model.addAttribute("activePill", "consulta");
        }


        return "persona-general";
    }

    /**
     * @param id    de la pwersona
     * @param model Defines a holder for model attributes.
     * @return La vista con el listado de comparendos asociados a el número de documento de identidad de una persona.
     */
    @GetMapping(value = CONTROLLER_PATH_ASOCIADOS + "/{id}")
    public String comparendos(@PathVariable(value = "id") UUID id, Model model) {

        Persona persona = repository.findOne(id);
        List<Comparendo> comparendos = comparendoRepository.findByInfractorId(id);
        if (!comparendos.isEmpty()) {
            Integer numComparendos = comparendoRepository.countByInfractorId(id);
            model.addAttribute("numComparendos", numComparendos);
            model.addAttribute("activePill", "asociados");
            model.addAttribute("persona", persona);
            model.addAttribute("comparendos", comparendos);
        } else {
            LOG.info("La persona a consultar no fue encoentrada");
            model.addAttribute("error", "Ocurrió un error intente más tarde");
            model.addAttribute("activePill", "asociados");
            model.addAttribute("persona", persona);
            model.addAttribute("numComparendos", 0);

        }

        return "persona-comparendos";
    }

    @GetMapping(value = "/reincidencias")
    public String reincidencias(Model model) {
        model.addAttribute("tab", "reincidencias");
        return "persona-reincidencias";
    }

    @GetMapping(value = "/reincidencias/iniciar")
    public String reincidenciasIniciar(Model model) {
        model.addAttribute("tab", "reincidencias");
        return "persona-reincidencias-iniciar";
    }

    @GetMapping(value = "/reincidencias/crear")
    public String reincidenciasCrear(Model model, @RequestParam("abogado") String abogado, RedirectAttributes redirect) {
        redirect.addFlashAttribute("SUCCESS", "Proceso de reincidencia asignado a " + abogado);
        model.addAttribute("tab", "reincidencias");
        return "redirect:/persona/reincidencias";
    }


}
