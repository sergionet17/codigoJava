package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.AgendamientoAudiencia;
import co.gov.movilidadbogota.sipa.data.biz.comp.AgendamientoAudienciaRepo;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoAudienciaComparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoAudienciaComparendoRepo;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.data.gen.FestivoRepo;
import co.gov.movilidadbogota.sipa.data.gen.Parametro;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Controlador que se encarga de Parametrizar los periodos de tiempo dentro de los cuales se pueden
 * hacer audiencias de continuación.
 * Created by paola.charry on 28/06/2017.
 */
@Controller
@RequestMapping(value = AgendamientoAudienciaController.CONTROLLER_PATH)
public class AgendamientoAudienciaController extends BaseController {

    public static final String CONTROLLER_PATH = "/agendamiento-audiencia";
    public static final String CONTROLLER_PATH_CREATE = "/create";
    public static final String CONTROLLER_PATH_SAVE = "/save";
    public static final String CONTROLLER_PATH_LIST = "/list";
    public static final String CONTROLLER_PATH_MODIFY = "/modify";
    public static final String CONTROLLER_PATH_SAVE_MODIFY = "/saveModify";

    private static final Logger logger = LoggerFactory.getLogger(AgendamientoAudienciaController.class);

    @Autowired
    AgendamientoAudienciaRepo agendamientoAudienciaRepo;
    @Autowired
    TipoAudienciaComparendoRepo tipoAudienciaComparendoRepo;
    @Autowired
    FestivoRepo festivoRepo;
    @Autowired
    ValorParametroRepo valorParametroRepo;
    @Autowired
    MessageSource messages;

    /**
     * @param model
     * @return
     */
    @GetMapping(value = "")
    public String listar(Model model) {
        try {
            List<AgendamientoAudiencia> agendamientoAudiencias = agendamientoAudienciaRepo.findByFechaCierreIsNull();
            model.addAttribute("agendamientoForm", agendamientoAudiencias);
            //Inicializa la forma para realiza
        } catch (Exception e) {
            logger.error("Error mientras se carga la parametrización de las audiencias", e);
            model.addAttribute("error", "Ocurrió un error intente más tarde");
            return "agendamiento-audiencia-list";
        }
        return "agendamiento-audiencia-list";
    }

    /**
     * Se encarca de la captura de la información de los periodos de audiencia
     *
     * @return Se direcciona ala pagina de consulta del comparendo en caso de ser exitoso la adición del agendamiento, de lo contrario
     * se direcciona  para realizar de nuevo la adicion
     */
    @GetMapping(value = CONTROLLER_PATH_CREATE)
    public String create(Model model, Locale locale) {
        try {
            List<AgendamientoAudiencia> agendamientoAudiencias = agendamientoAudienciaRepo.findByFechaCierreIsNull();

            //Se busca los tipo de de audiencia
            logger.debug("Buscando los tipos de audiencia");
            List<TipoAudienciaComparendo> tipo_audiencia = tipoAudienciaComparendoRepo.findAll();
            //Busca los tipos de audiencia con agendas parametrizadas
            agendamientoAudiencias.forEach(agenda -> {
                        if (tipo_audiencia.contains(agenda.getTipo())) {
                            tipo_audiencia.remove(agenda.getTipo());
                        }
                    }
            );
            if (tipo_audiencia.size() == 0) {
                model.addAttribute("error", "No hay más tipos de audiencia para parametrizar");
                return "agendamiento-audiencia";
            }
            if (tipo_audiencia != null) {
                model.addAttribute("tiposAudiencia", ControllerUtils.listToMapWithDefault(tipo_audiencia, "nombre", messages, locale));
            } else {
                model.addAttribute("tiposAudiencia", "");
            }

            //Se carga lista de dias de acuerdo a la cantidad maxima de días
            logger.error("Se carga la cantidad de días permitidas para la audiencía");
            String valor = valorParametroRepo.findValorVigenteByClave(Parametro.DIAS_MAXIMO_AUDIENCIA, new Date()).getValor();
            Integer maximoDías = valor == null ? 0 : new Integer(valor);
            List<Integer> dias = new ArrayList<>();
            int contador = 0;
            while (contador <= maximoDías) {
                dias.add(contador);
                contador = contador + 1;
            }
            model.addAttribute("diasForm", dias);

            //Inicializa la forma
            model.addAttribute("agendamientoForm", new AgendamientoAudiencia());

        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
            return String.format("redirect:%s", CONTROLLER_PATH);
        }
        return "agendamiento-audiencia";
    }

    /**
     * Se encarca de guardar la configuracion de los rangos de tiempo de acuerdo al tipo de audiencia
     *
     * @param agendamientoAudiencia
     * @param result
     * @param model
     * @param redirectAttributes
     * @param locale
     * @return Un nuevo horario de acuerdo al tipo de agendamiento
     */
    @PostMapping(CONTROLLER_PATH_SAVE)
    public String saveAgenda(
            @Valid @ModelAttribute("agendamientoForm") AgendamientoAudiencia agendamientoAudiencia,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes,
            Locale locale) {

        try {
            //Valida reglas de negocio y de obligatoriedad
            new AgendamientoAudienciaValidator().validate(agendamientoAudiencia, result);

            if (result.hasErrors()) {
                List<TipoAudienciaComparendo> tipo_audiencia = tipoAudienciaComparendoRepo.findAll();
                if (tipo_audiencia.size() != 0) {
                    model.addAttribute("tiposAudiencia", ControllerUtils.listToMapWithDefault(tipo_audiencia, "nombre", messages, locale));
                } else {
                    model.addAttribute("tiposAudiencia", new ArrayList<TipoAudienciaComparendo>());
                }

                logger.debug(String.format("Se encontraron errores en las validaciones de forma: %s", result));
                return "agendamiento-audiencia";
            }

            agendamientoAudienciaRepo.save(agendamientoAudiencia);

        } catch (Exception e) {
            logger.error("Error mientras se persiste la agenda", e);
            model.addAttribute("error", "Ocurrió un error intente más tarde");
            return String.format("redirect:%s", CONTROLLER_PATH);
        }
        redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                messages.getMessage("audiencia.action.create.success",
                        new Object[]{agendamientoAudiencia.getTipo().getNombre()},
                        locale));
        return String.format("redirect:%s", CONTROLLER_PATH);
    }

    /**
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = CONTROLLER_PATH_MODIFY + "/{id}")
    public String modificarAgenda(@PathVariable("id") Integer id,
                                  Model model) {

        try {
            //Se busca los tipo de de audiencia
            logger.debug("Buscando los tipos de audiencia");
            AgendamientoAudiencia agendamientoAudiencia = agendamientoAudienciaRepo.findOne(id);
            model.addAttribute("agendamientoForm", agendamientoAudiencia);
            //Se carga lista de dias de acuerdo a la cantidad maxima de días
            logger.error("Se carga la cantidad de días permitidas para la audiencía");
            String valor = valorParametroRepo.findValorVigenteByClave(
                    Parametro.DIAS_MAXIMO_AUDIENCIA, new Date()).getValor();
            Integer maximoDías = valor == null ? 0 : new Integer(valor);
            List<Integer> dias = new ArrayList<>();
            int contador = 0;
            while (contador <= maximoDías) {
                dias.add(contador);
                contador = contador + 1;
            }
            model.addAttribute("diasForm", dias);
            //
            TipoAudienciaComparendo tipo_audienciatipo = tipoAudienciaComparendoRepo.findOne(agendamientoAudiencia.getTipo().getId());
            List<TipoAudienciaComparendo> agendasAudiencia = new ArrayList<>();
            agendasAudiencia.add(tipo_audienciatipo);
            //
            if (agendasAudiencia != null) {
                model.addAttribute("tiposAudiencia", ControllerUtils.listToMap(agendasAudiencia, "nombre"));
            } else {
                model.addAttribute("tiposAudiencia", "");
            }
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
            return String.format("redirect:%s", CONTROLLER_PATH);
        }
        return "agendamiento-audiencia-modify";
    }

    /**
     * Modifica la información de un horario existente
     *
     * @param agendamientoAudiencia
     * @param result
     * @param model
     * @param redirectAttributes
     * @param locale
     * @return Un nuevo horario de acuerdo al tipo de agendamiento y cierra el anterior horario
     */

    @PostMapping(CONTROLLER_PATH_SAVE_MODIFY)
    public String saveModifyAgenda(
            @Valid @ModelAttribute("agendamientoForm") AgendamientoAudiencia agendamientoAudiencia,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes,
            Locale locale) {

        try {
            //Valida reglas de negocio y de obligatoriedad
            logger.debug(String.format("Se encontraron errores en las validaciones de forma: %s", result));
            new AgendamientoAudienciaValidator().validate(agendamientoAudiencia, result);

            //Valida que haya realizado algun cambio en la informacion existente

            AgendamientoAudiencia agendamientoAudienciaSave = agendamientoAudienciaRepo.findOne(agendamientoAudiencia.getId());
            if (agendamientoAudienciaSave.getDiaInicial() == agendamientoAudiencia.getDiaInicial() &&
                    agendamientoAudienciaSave.getDiaFinal() == agendamientoAudiencia.getDiaFinal()) {
                result.rejectValue("diaInicial", "audiencia.dia.nochange");
            }

            if (result.hasErrors()) {
                List<TipoAudienciaComparendo> tipo_audiencia = tipoAudienciaComparendoRepo.findAll();
                if (tipo_audiencia.size() != 0) {
                    model.addAttribute("tiposAudiencia", ControllerUtils.listToMapWithDefault(tipo_audiencia, "nombre", messages, locale));
                } else {
                    model.addAttribute("tiposAudiencia", new ArrayList<TipoAudienciaComparendo>());
                }
                logger.debug(String.format("Se encontraron errores en las validaciones de forma: %s", result));
                return "agendamiento-audiencia-modify";
            }
            //Cierra el registro anterior
            logger.debug(String.format("Cierra el horario con ID : %s", agendamientoAudienciaSave.getId()));
            agendamientoAudienciaSave.setFechaCierre(new Date());
            agendamientoAudienciaRepo.save(agendamientoAudienciaSave);
            //Crea un nuevo registro
            logger.debug(String.format("Inserta un horario de tipo de audiencia : %s", agendamientoAudienciaSave.getTipo().getNombre()));
            agendamientoAudienciaRepo.save(agendamientoAudiencia);

        } catch (Exception e) {
            logger.error("Error mientras se persiste la agenda", e);
            model.addAttribute("error", "Ocurrió un error intente más tarde");
            return String.format("redirect:%s", CONTROLLER_PATH);
        }
        redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                messages.getMessage("audiencia.action.modify.success", new Object[]{
                        agendamientoAudiencia.getTipo().getNombre()
                }, locale));
        return String.format("redirect:%s", CONTROLLER_PATH);
    }
}
