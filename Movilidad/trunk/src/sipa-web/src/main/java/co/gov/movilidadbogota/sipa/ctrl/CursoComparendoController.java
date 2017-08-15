package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.*;
import co.gov.movilidadbogota.sipa.data.fin.EntradaRepo;
import co.gov.movilidadbogota.sipa.data.fin.FinancieroUtils;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controlador de la asignación de un curso pedagogico a un comparendo
 * Created by paola.charry on 26/05/2017.
 */

@Controller
@RequestMapping(value = CursoComparendoController.CONTROLLER_PATH)
public class CursoComparendoController extends UtilController {

    public static final String CONTROLLER_PATH = "/curso_comparendo";
    public static final String CONTROLLER_PATH_LIST_COM = "";
    public static final String CONTROLLER_PATH_SAVE = "/save";
    public static final String CONTROLLER_CANCELAR = "/cancelar";

    private static final Logger logger = LoggerFactory.getLogger(CursoComparendoController.class);

    @Autowired
    private CursoComparendoRepo cursoComparendoRepo;
    @Autowired
    private ComparendoRepository comparendoRepository;
    @Autowired
    private CursoPedagogicoRepo cursoPedagogicoRepo;
    @Autowired
    private EntradaRepo entradaRepo;

    /**
     * En lista todos los cursos pedagogicos para asignarlo a un comparendo
     *
     * @param model
     * @return Formulario con la lista de los cursos pedagogicos  registrados vigentes
     */

    @RequestMapping(value = CONTROLLER_PATH_LIST_COM + "/{id}")
    public String cursosInscripcion(@PathVariable("id") UUID id, Model model, RedirectAttributes redirectAttributes) {
        Comparendo comparendo = comparendoRepository.findOne(id);
        if (comparendo != null) {
            BigDecimal porcentajeDescuento = porcentajeDescuento(comparendo);
            if (porcentajeDescuento == BigDecimal.ZERO) {
                logger.debug("Ya no cuenta con descuento para inscribirse al curso pedagogico");
                redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK, "Ya no cuenta con descuento para inscribirse el curso pedagógico");
                return String.format("redirect:/comparendo/general/%s", id);
            } else {
                if (cursoComparendoRepo.countByComparendoId(comparendo.getId()) > 0) {
                    logger.debug("El comparendo se encuentra inscrito a un curso");
                    redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK, "El comparendo se encuentra inscrito a un curso");
                    return String.format("redirect:/comparendo/general/%s", id);
                } else {
                    model.addAttribute("comparendo", comparendo);
                    //Calcula el porcentaje de descuento de acuerdo al tipo de comparendo y su fecha de imposicion y notificacion
                    model.addAttribute("porcentaje_descuento", porcentajeDescuento.multiply(new BigDecimal("100")));
                    //Se calcula el saldo de la deuda
                    BigDecimal saldo = FinancieroUtils.saldoDeudaComparendo(comparendo.getId(), entradaRepo);
                    //Se calcula el valor del descuento de acuero al saldo
                    BigDecimal descuento = valorDescuento(saldo, porcentajeDescuento);
                    model.addAttribute("descuento", descuento);
                    //Consulta los cursos vigentes y con cupos
                    logger.debug("Buscar los cursos vigentes para ser visualizados en el formulario");
                    //Consulta los cursos pedagogicos vigentes
                    try {
                        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN_DDMMYY);
                        String fechaActual = formatter.format(new Date());
                        List<Curso> cursos = cursoPedagogicoRepo.findByFechaAfterAndEstadoIdOrFechaAndEstadoId(
                                formatter.parse(fechaActual),
                                EstadoCurso.VIGENTE.getId(),
                                formatter.parse(fechaActual),
                                EstadoCurso.VIGENTE.getId());

                        List<Curso> cursosForma = validaDisponibilidad(cursos);
                        model.addAttribute("cursos", cursosForma);
                        //Inicializa la forma para realiza
                        model.addAttribute("cursoForm", new Curso());
                        //Consulta los comparendos vigentes
                        logger.debug("Buscar las infraciones vigentes");
                        return "curso-comparendo";

                    } catch (ParseException e) {
                        logger.error("Error mientras se persiste el comparendo con el curso", e);
                        model.addAttribute("error", "Ocurrió un error intente más tarde");
                        return "curso-comparendo";
                    }
                }
            }
        } else {
            logger.debug("No se encuentra información del comparendo");
            redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK, "No se encuentra información del comparendo");
            return String.format("redirect:/comparendo/general/%s", id);
        }
    }

    /**
     * Calcula si el cupo esta completo en el curso
     *
     * @param cursos Lista de curso vigente
     * @return Lista de cursos disponibles
     */
    private List<Curso> validaDisponibilidad(List<Curso> cursos) {
        List<Curso> cursosForma = new ArrayList<>();
        if (cursos != null) {
            for (Curso curso : cursos) {
                if (curso != null) {
                    logger.debug("Busca la cantidad de asistentes registrados a un curso" + curso.getId());
                    //Consulta la cantidad de asistentes
                    Integer asistentesRegistrados = cursoComparendoRepo.countByCursoId(curso.getId());
                    if (asistentesRegistrados > 0) {
                        logger.debug("Busca la cantidad maxima de asistentes a un curso vs los asistentes registrados");
                        //Consulta la cantidad maxima de asistentes
                        if (curso.getMaximoAsistentes() > asistentesRegistrados) {
                            logger.debug("Adiciona curso al formulario");
                            cursosForma.add(curso);
                        }
                    } else {
                        logger.debug("Adiciona curso a la forma con cupos disponibles");
                        cursosForma.add(curso);
                    }
                }
            }
        }
        return cursosForma;
    }

    /**
     * Se raliza las validaciones de tipo de dato y de negocio.
     * Se persiste un curso {@link CursoComparendo}
     *
     * @param model
     * @param redirectAttributes
     * @param locale
     * @return en caso de no pasar las validaciones de tipo de dato y de negocio se retorna al formulario de registro
     * en caso contrario se persiste un {@link CursoComparendo}
     */
    @RequestMapping(value = CONTROLLER_PATH_SAVE + "/{id}/{id}")
    public String insertarCursoComparendo(@PathVariable("id") UUID id, @PathVariable("idComparendo") UUID idComparendo,
                                          Model model,
                                          RedirectAttributes redirectAttributes,
                                          Locale locale) {

        logger.debug("Se inicia el registro de un asistente a un curso");
        try {
            //Se Consulta el Id seleccionado
            logger.debug("Consultando cursos vigentes para ser visualizados");
            Curso curso = cursoPedagogicoRepo.findById(id);
            //Consulta la informacion del comparendo para relacionarla con el curso vigente
            logger.debug("Consultando comparendos para ser visualizados");
            Comparendo comparendo = comparendoRepository.findOne(idComparendo);
            logger.debug("Cargando información de los cursos y  comparendos asociados");
            //Se inicia la persistencia de la relacion entre el curso y el comparendo.
            CursoComparendo cursoComparendo = new CursoComparendo();
            cursoComparendo.setComparendo(comparendo);
            cursoComparendo.setCurso(curso);
            cursoComparendo.setFechaRegistro(new Date());
            cursoComparendo.setEstado(EstadoAsistenciaCurso.INSCRITO);
            cursoComparendoRepo.save(cursoComparendo);
            logger.debug("Se cargó información exitodamente");
        } catch (Exception e) {
            logger.error("Error mientras se persiste el comparendo con el curso", e);
            model.addAttribute("error", "Ocurrió un error intente más tarde");
            return String.format("%s%s/%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST_COM, idComparendo);
        }
        redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK, "El curso ha sido asignado");
        return String.format("redirect:/comparendo/general/%s", idComparendo);
    }

    /**
     * Funcion que retorna al formulario anterior
     *
     * @return
     */
    @RequestMapping(value = CONTROLLER_CANCELAR + "/{id}")
    private String cancelar(@PathVariable(value = "id") UUID id) {
        return String.format("redirect:/comparendo/general/%s", id);
    }
}
