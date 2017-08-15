package co.gov.movilidadbogota.sipa.ctrl;
import co.gov.movilidadbogota.sipa.cli.SipaServicesClient;
import co.gov.movilidadbogota.sipa.data.biz.comp.*;
import co.gov.movilidadbogota.sipa.data.doc.DocumentoRepo;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.util.SecurityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Controlador de registrar asistencia a curso pedagógico.
 * Created by paola.charry on 12/06/2017.
 */
@Controller
@RequestMapping(value = CursoAsistenciaController.CONTROLLER_PATH)
public class CursoAsistenciaController extends BaseController {

    public static final String CONTROLLER_PATH = "/curso_asistencia";
    public static final String CONTROLLER_PATH_LIST = "";
    public static final String CONTROLLER_PATH_SEARCH = "/consultar";
    public static final String CONTROLLER_PATH_SAVE = "/save";

    private static final Logger logger = LoggerFactory.getLogger(CursoAsistenciaController.class);

    MessageSource messages;
    @Autowired
    private CursoComparendoRepo cursoComparendoRepo;
    @Autowired
    private ComparendoRepository comparendoRepository;
    @Autowired
    private CursoPedagogicoRepo cursoPedagogicoRepo;
    @Autowired
    private DocumentoRepo documentoRepo;
    @Autowired
    private SecurityHelper securityHelper;
    @Autowired
    private SipaServicesClient sipaServicesClient;

    /**
     * En lista todos los cursos vigentes para el día actual
     *
     * @param model Define los atributos del modelo
     * @return Formulario con la lista de los cursos pedagogicos  registrados vigentes
     */
    @GetMapping(value = CONTROLLER_PATH_LIST)
    public String consultar(Model model) {

        try {
            //Consulta los cursos vigentes y con cupos
            List<Curso> cursosForma = new ArrayList<>();
            logger.debug("Buscar los cursos vigentes");
            //Consulta los cursos pedagogicos vigentes
            SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT_PATTERN_DDMMYY);
            String fechaActual = formatter.format(new Date());
            List<Curso> cursos = null;
            cursos = cursoPedagogicoRepo.findByFechaAndEstadoId(formatter.parse(fechaActual),
                    EstadoCurso.VIGENTE.getId());
            model.addAttribute("cursos", cursos);

        } catch (ParseException e) {
            logger.error("Error mientras se persiste el comparendo con el curso", e);
            model.addAttribute("error", "Ocurrió un error intente más tarde");
            return "asistencia-list";
        }
        return "asistencia-list";
    }


    /**
     * En lista todos los asistentes al curso seleccionado
     *
     * @param model Define los atributos del modelo
     * @return Formulario con la lista de los cursos pedagogicos  registrados vigentes
     */
    @RequestMapping(value = CONTROLLER_PATH_SEARCH + "/{id}")
    public String cursoAsistencia(@PathVariable(value = "id") String id, Model model) {
        //Consulta los cursos vigentes y con cupos
        List<Curso> cursosForma = new ArrayList<>();
        logger.debug("Buscar los cursos vigentes");
        //Consulta los cursos pedagogicos vigentes
        Curso curso = cursoPedagogicoRepo.findById(UUID.fromString(id));
        if (curso != null) {
            //Consulta los comparendos vigentes
            logger.debug("Buscar las infraciones vigentes");
            model.addAttribute("comparendos", cursoComparendoRepo.findByCursoId(curso.getId()));
            model.addAttribute("curso", curso);
        }
        //Inicializa la forma para realiza
        return "asistencia-save";
    }

    /**
     * Guarda los infractores que asistieron al curso pedagogico
     *
     * @param asistente          Arreglo de los infractores que asistieron al curso
     * @param redirectAttributes Permite redireccionar los atributos creados para los mensajes locales
     * @param locale             Permite retornar los mensajes de exito y de fallo
     * @param principal          Permite obtener el usuario que se encuentra autenticado
     * @return Retorna mensaje exitoso de los asistentes y  generación del certificado
     * del curso pedagogico, en caso contrario retorna un mensaja notificado que no se
     * realizo la acción
     */
    @PostMapping(value = CONTROLLER_PATH_SAVE)
    public String save(@RequestParam(value = "asistente", required = false) String[] asistente,
                       RedirectAttributes redirectAttributes,
                       Locale locale, Principal principal, Model model) {
        try {
            if (asistente == null) {
                redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_ERROR, "No cuenta con asistentes asociados al curso seleccionado");
                return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
            } else {
                CursoComparendo cursoComparendo = null;
                if (asistente[0] != null) {
                    for (int i = 0; i <= asistente.length - 1; i++) {
                        cursoComparendo = cursoComparendoRepo.findBycomaprendoId(UUID.fromString(asistente[i]));
                        generarCertificadoAsistenciaCurso(cursoComparendo, principal, redirectAttributes, model);
                    }
                    cursoPedagogicoRepo.updateCursobyId(cursoComparendo.getCurso().getId(), EstadoCurso.TERMINADO.getId());
                    redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK, "Se actualizo la informacion correctamente");
                } else {
                    model.addAttribute("error", "Se debe seleccionar al menos un asistente para guardar la asistencia");
                    return "asistencia-list";
                }
            }
        } catch (Exception e) {
            logger.error("Ocurrió un error intente más tarde", e);
            redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_ERROR, "Ocurrió un error intente más tarde");
            return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
        }
        return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
    }

    /**
     * Genera el certificado de asistencia del curso pedagogico
     *
     * @param cursoComparendo    Objeto con la data del {@link CursoComparendo}
     * @param principal          Permite obtener el usuario que se encuentra autenticado
     * @param redirectAttributes
     * @param model
     */

    private void generarCertificadoAsistenciaCurso(CursoComparendo cursoComparendo, Principal principal, RedirectAttributes redirectAttributes, Model model) {
        /*
        List<CursoComparendo> cursoComparendos = new ArrayList<>();
        cursoComparendos.add(cursoComparendo);
        ReportBuilder reportBuilder = new ReportBuilder();
        reportBuilder.templatePath = "classpath:reports";
        Map<String, Object> parametros = new HashMap<>();
        //Carga la informacion de la diferencia entre horas
        parametros.put("cantidad_horas", obtenerDiferenciaHoras(cursoComparendo.getCurso()));
        //Carga la informacion del usuario
        Usuario usuario = securityHelper.getUsuario(principal.getName(), Usuario.class);
        parametros.put("nombre_usuario", usuario.getPersona().obtenerNombreCompleto());
        //Carga la Firma del usuario
        parametros.put("firma", new ByteArrayInputStream(usuario.getFirma()));
        //Genera el PDF de acuerdo a la data y parametros enviados
        byte[] bytes = reportBuilder.pdf(
                "certificado-asistencia-curso",
                cursoComparendos,
                parametros
        );
        try {
            // Se realiza la persistencia del documento
            Documento doc = sipaServicesClient.getDocumentos().addDocumento(
                    new Documento(
                            MediaType.APPLICATION_PDF.toString(),
                            "certificado-asistencia-curso.pdf",
                            TipoDocumental.CERTIFICADO_ASISTENCIA_CURSO
                    ),
                    new Expediente(cursoComparendo.getComparendo().getId()),
                    bytes
            );
            //Actualiza la asistencia y la generacion del certificado
            cursoComparendoRepo.setCursoComparendoByComparendoId(cursoComparendo.getComparendo().getId(), EstadoAsistenciaCurso.ASISTIDO.getId(), doc);
        } catch (Exception e) {
            model.addAttribute("error", "Se debe seleccionar al menos un asistente para guardar la asistencia");
        }
        */
    }

    /**
     * Obtiene la diferencia entre las horas del curso
     *
     * @param curso Ingresa el objeto curso que contiene las horas del curso pedagogico
     * @return Retorna un objeto Integer con la diferencia de horas del curso
     */

    private Object obtenerDiferenciaHoras(Curso curso) {

        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        //Realizamos la validacion
        Date horaInicialT, horaFinalT;
        try {
            horaInicialT = df.parse(curso.getHoraInicial());
            horaFinalT = df.parse(curso.getHoraFinal());
            long difference = horaFinalT.getTime() - horaInicialT.getTime();
            int horas = Math.toIntExact((difference / (1000 * 60)) / 60);
            return new Integer(horas);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}