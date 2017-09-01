package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.*;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.data.gen.ValorParametroRepo;
import co.gov.movilidadbogota.sipa.util.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Controlador de Cursos Pedagogicos
 * autor: paola.charry
 */

@Controller
@RequestMapping(value = CursoPedagogicoController.CONTROLLER_PATH)
public class CursoPedagogicoController extends BaseController {

    public static final String CONTROLLER_PATH = "/cursos";
    public static final String CONTROLLER_PATH_LIST = "";
    public static final String CONTROLLER_PATH_CREATE = "/create";
    public static final String CONTROLLER_PATH_SEDES = "/sedes";

    private static final Logger logger = LoggerFactory.getLogger(CursoPedagogicoController.class);

    @Autowired
    CursoPedagogicoRepo cursoPedagogicoRepo;
    @Autowired
    SedeRepo sedesRepository;
    @Autowired
    SalonRepo salonRepo;
    @Autowired
    InstructorRepository instructorRepository;
    @Autowired
    MessageSource messages;
    @Autowired
    ValorParametroRepo valorParametroRepo;

    /**
     * En lista todos los cursos pedagogicos
     *
     * @param model
     * @return Formulario con la lista de los cursos pedagogicos  registrados vigentes
     */
    @GetMapping(value = CONTROLLER_PATH_LIST)
    public String list(Model model) {
        try {
            logger.debug("Buscar los cursos vigentes para ser visualizados");
            SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN_DDMMYY);
            String fechaActual = formatter.format(new Date());
            model.addAttribute("cursos", cursoPedagogicoRepo.findByFechaAfterAndEstadoIdOrFechaAndEstadoId(
                    formatter.parse(fechaActual),
                    EstadoCurso.VIGENTE.getId(),
                    formatter.parse(fechaActual),
                    EstadoCurso.VIGENTE.getId()));
            //Inicializa la forma para realiza
            model.addAttribute("cursoForm", new Curso());
        } catch (Exception e) {
            logger.error("Error mientras se carga los cursos pedagogicos vigentes", e);
            model.addAttribute("error", "Ocurrió un error intente más tarde");
            return "cursos-list";
        }
        return "cursos-list";
    }

    /**
     * Se inicializa el formulario para la captura de la información del curso
     *
     * @return Se retorna a la pagina de registro al curso
     */
    @GetMapping(value = CONTROLLER_PATH_CREATE)
    public String create(Model model, Locale locale) {

        try {
            //Inicializa la forma para realiza una inscripcion de un curso nuevo
            model.addAttribute("cursoForm", new Curso());
            //Se carga todas las sedes
            model.addAttribute("sedes", ControllerUtils.listToMapWithDefault(sedesRepository.findAll(), "nombre", messages, locale));
            //Se carga los instructores
            model.addAttribute("instructores", ControllerUtils.listToMapWithDefault(instructorRepository.findAll(), "persona.primerNombre,persona.segundoNombre,persona.primerApellido,persona.segundoApellido", messages, locale));
            logger.debug("Formulario de registro de un curso");

        } catch (Exception e) {
            logger.error("Error mientras se carga el formulario para la creacion de un curso", e);
            model.addAttribute("error", "Ocurrió un error intente más tarde");
            return "cursos-create";
        }
        return "cursos-create";
    }

    /**
     * Se raliza las validaciones de tipo de dato y de negocio.
     * Se persiste un curso {@link Curso}
     *
     * @param curso
     * @param result
     * @param model
     * @param redirectAttributes
     * @param locale
     * @return en caso de no pasar las validaciones de tipo de dato y de negocio se retorna al formulario de registro
     * en caso contrario se persiste un {@link Curso}
     */
    @PostMapping(CONTROLLER_PATH_LIST)
    public String insertaCurso(
            @Valid @ModelAttribute("cursoForm") Curso curso,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes,
            Locale locale) {

        try {
            // Si existen errores de formato retorna al mismo formulario
            logger.debug("Se realiza las validaciones de negocio");
            new CursoValidator(valorParametroRepo).validate(curso, result);
            if (result.hasErrors()) {
                //Se carga todas las sedes
                model.addAttribute("sedes", ControllerUtils.listToMapWithDefault(sedesRepository.findAll(), "nombre", messages, locale));
                //Se carga los instructores
                model.addAttribute("instructores", ControllerUtils.listToMapWithDefault(instructorRepository.findAll(), "persona.primerNombre,persona.segundoNombre,persona.primerApellido,persona.segundoApellido", messages, locale));

                logger.debug(String.format("Se encontraron errores en las validaciones de forma: %s", result));
                if (curso.getSalon().getId() != null && curso.getSalon().getSede() != null) {
                    model.addAttribute("salones", sedesRepository.findOne(curso.getSalon().getSede().getId()));
                }
                return "cursos-create";
            }
            //Valida que el salon se encuentre disponible en la hora indicada
            List<Curso> cursosCreados = cursoPedagogicoRepo.findBySalonAndFecha(curso.getSalon().getId(), curso.getFecha());
            if (cursosCreados.size() > 0) {
                SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                Date horaInicial = df.parse(curso.getHoraInicial());
                Date horaFinal = df.parse(curso.getHoraFinal());
                for (Curso cursoTemp : cursosCreados) {
                    Date horaInicialT = df.parse(cursoTemp.getHoraInicial());
                    Date horaFinalT = df.parse(cursoTemp.getHoraFinal());
                    if (horaInicialT.getTime() >= horaInicial.getTime() && horaFinalT.getTime() < horaInicial.getTime()) {
                        result.rejectValue("horaInicial", "curso.hora.nodisponible");
                    } else if (horaInicialT.getTime() < horaFinal.getTime() && horaFinalT.getTime() >= horaFinal.getTime()) {
                        result.rejectValue("horaInicial", "curso.hora.nodisponible");
                    }
                }
            }
            if (result.hasErrors()) {
                logger.debug(String.format("Se encontraron errores en las validaciones de forma: %s", result));
                return "cursos-create";
            }
            //Se guarda la información de la forma en el objeto Curso
            logger.debug(String.format("Información del curso: %s", curso));
            curso.setId(UUID.randomUUID());
            curso.setEstado(EstadoCurso.VIGENTE);
            cursoPedagogicoRepo.save(curso);
        } catch (Exception e) {
            logger.error("Error mientras se carga el formulario para la creacion de un curso", e);
            model.addAttribute("error", "Ocurrió un error intente más tarde");
            return "cursos-list";
        }
        redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                messages.getMessage("curso.action.create.success", new Object[]{curso.getTema()}, locale));
        return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
    }

    /**
     * Se busca salon de acuerdo a una sede {@link Sede}
     *
     * @param id representa el identificador de la sede
     * @return Se retorna una coleccion de sedes de acuerdo a un ID
     */
    @GetMapping(value = CONTROLLER_PATH_SEDES + "/{id}", produces = "application/json")
    @ResponseBody
    public Sede sedes(@PathVariable("id") UUID id) {
        logger.debug(String.format("Buscando la sede %s", id));
        try {
            return sedesRepository.findOne(id);
        } catch (Exception e) {
            logger.error(String.format("Mientras buscaba la sede %s", id), e);
            return null;
        }
    }
}
