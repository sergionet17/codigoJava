package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.aut.Permiso;
import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.aut.UsuarioRepo;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoNotificacion;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoNotificacionRepo;
import co.gov.movilidadbogota.sipa.data.gen.*;
import co.gov.movilidadbogota.sipa.data.util.BaseUtils;
import co.gov.movilidadbogota.sipa.util.ControllerUtils;
import co.gov.movilidadbogota.sipa.util.MailSender;
import co.gov.movilidadbogota.sipa.util.MensajeDto;
import co.gov.movilidadbogota.sipa.util.PlantillaDto;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

/**
 * Created by Admin on 18/07/2017.
 */

@Controller
@RequestMapping(value = AlarmaController.CONTROLLER_PATH)
public class AlarmaController extends BaseController {
    public static final String CONTROLLER_PATH = "";
    public static final String CONTROLLER_PATH_LIST = "/alarmas";
    public static final String CONTROLLER_PATH_EDIT = "/editar";
    public static final String CONTROLLER_PATH_EDITRANGO = "/editarRango";
    private final MensajeRepo mensajeRepo;

    private static final Logger LOGGER = LoggerFactory.getLogger(AlarmaController.class);

    @Autowired
    AlarmaRepo alarmaRepo;
    @Autowired
    TipoDiaRepo tiposDia;
    @Autowired
    TipoNotificacionRepo tipoNotificacionRepo;
    @Autowired
    AlarmaRangoRepo alarmaRangoRepo;
    @Autowired
    MessageSource messages;
    @Autowired
    TipoDiaRepo tipoDiaRepo;
    @Autowired
    UsuarioRepo usuarioRepo;

    @Autowired
    ApplicationContext applicationContext;

    public AlarmaController(MensajeRepo mensajeRepo, TipoNotificacionRepo tipoNotificacionRepo) {
        this.mensajeRepo = mensajeRepo;
        this.tipoNotificacionRepo = tipoNotificacionRepo;
    }


    /**
     * Lista todas las alarmas
     */
    @PreAuthorize("hasAuthority('" + Permiso.MODIFICAR_ALARMA + "')")
    @GetMapping(value = CONTROLLER_PATH_LIST)
    public String list(Model model) {
        try {
            LOGGER.info("Buscar las alarmas");
            model.addAttribute("alarmas", alarmaRepo.findAll());
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "alarma-list";
    }

    /**
     * Se inicializa el formulario para editar la información de una alarma
     *
     * @return Se retorna a la pagina de editar la alarma
     */
    @PreAuthorize("hasAuthority('" + Permiso.MODIFICAR_ALARMA + "')")
    @RequestMapping(path = CONTROLLER_PATH_EDIT + "/{clave}", method = RequestMethod.GET)
    public String edit(@PathVariable("clave") String clave, Model model, RedirectAttributes redirectAttributes,
                       Locale locale) {
        try {
            //Inicializa la forma para realizar la modificacion de una dependencia
            Alarma alarma = alarmaRepo.findOne(clave);
            model.addAttribute("alarmaForm", alarma);
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "alarma-edit";
    }


    @PostMapping(CONTROLLER_PATH_EDIT)
    public String editarAlarmaPost(
            @Valid @ModelAttribute("alarmaForm") Alarma alarma,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes,
            Locale locale) throws IOException {
        Alarma alarmasel = alarmaRepo.findOne(alarma.getClave());
        return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_EDIT);
    }

    /**
     * Se inicializa el formulario para editar la información de una alarma
     *
     * @return Se retorna a la pagina de editar la alarma
     */
    @PreAuthorize("hasAuthority('" + Permiso.MODIFICAR_ALARMA + "')")
    @RequestMapping(path = CONTROLLER_PATH_EDITRANGO + "/{id}", method = RequestMethod.GET)
    public String editarRango(@ModelAttribute("alarmaForm") Alarma alarma, @PathVariable("id") UUID id, Model model, RedirectAttributes redirectAttributes,
                              Locale locale) {
        try {
            //Inicializa la forma para realizar la modificacion de una dependencia
            model.addAttribute("rangoForm", alarmaRangoRepo.findRangoById(id));
            model.addAttribute("tiposNotificacion", ControllerUtils.listToMapWithDefault(tipoNotificacionRepo.findAll(),
                    "nombre", messages, locale));
            model.addAttribute("tiposDia", ControllerUtils.listToMapWithDefault(tipoDiaRepo.findAll(),
                    "nombre", messages, locale));
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "alarma-editarRango";
    }


    @PostMapping(CONTROLLER_PATH_EDITRANGO)
    public String editarRangoPost(
            @Valid @ModelAttribute("rangoForm") AlarmaRango rango, @ModelAttribute("alarmaForm") Alarma alarma,
            BindingResult result,
            Model model,
            RedirectAttributes redirectAttributes,
            Locale locale) throws IOException {
        AlarmaRango rango1 = alarmaRangoRepo.findRangoById(rango.getId());
        rango1.setTipoDia(rango.getTipoDia());
        rango1.setTipoNotificacion(rango.getTipoNotificacion());
       // Alarma alarma1 = alarmaRepo.findOne(alarma.getClave());
        redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                messages.getMessage("alarma.action.edit.success", new Object[]{alarma.getClave()}, locale));
        return String.format("redirect:%s%s", CONTROLLER_PATH, CONTROLLER_PATH_LIST);
    }


    @Scheduled(cron = "${app.alarmas.cron:0 * 13 * * ?}")
    @Transactional
    public void doScheduledWork() {

        Calendar now;
        (now = Calendar.getInstance()).setTime(new Date());
        Date vencimiento = new Date();
        for (Alarma alarma : alarmaRepo.findAll()) {
            AlarmaStrategy strategy = applicationContext.getBean(alarma.getImplementacion(), AlarmaStrategy.class);
            List<AlarmaRango> rangos = alarma.getRangos();
            Collections.sort(rangos, (o1, o2) -> o1.getDias().compareTo(o2.getDias()));
            Set<Map.Entry<UUID, Date>> entrySet = strategy.findDateMap().entrySet();
            for (Map.Entry<UUID, Date> entry : entrySet) {
                for (AlarmaRango rango : rangos) {
                    if (rango.getTipoDia().getId().equals(1)) { // calendario
                        vencimiento = DateUtils.addDays(entry.getValue(), rango.getDias());
                        if (vencimiento.equals(now.getTime())) {
                            if (rango.getTipoNotificacion().equals(4)) { //notificación por pantalla
                                guardarMensaje(strategy.getMessage(entry.getKey()).getDestinatario(),
                                        strategy.getMessage(entry.getKey()).toString(),
                                        rango.getTipoNotificacion(), true);
                            } else if (rango.getTipoNotificacion().equals(5)) {//notificación por correo
                                guardarMensaje(strategy.getMessage(entry.getKey()).getDestinatario(),
                                        strategy.getMessage(entry.getKey()).toString(),
                                        rango.getTipoNotificacion(), false);
                                enviarMensajesxCorreo();
                            } else if (rango.getTipoNotificacion().equals(6)) {//notificacion por pantalla y correo
                                guardarMensaje(strategy.getMessage(entry.getKey()).getDestinatario(),
                                        strategy.getMessage(entry.getKey()).toString(),
                                        rango.getTipoNotificacion(), false);
                                enviarMensajesxCorreo();
                            }
                        }
                    } else if (rango.getTipoDia().getId().equals(2)) {  // habil
                        vencimiento = BaseUtils.sumarDiasHabilesAFecha(entry.getValue(), rango.getDias());
                        if (vencimiento.equals(now.getTime())) {
                            if (rango.getTipoNotificacion().equals(2)) { //notificación por pantalla
                                guardarMensaje(strategy.getMessage(entry.getKey()).getDestinatario(),
                                        strategy.getMessage(entry.getKey()).toString(),
                                        rango.getTipoNotificacion(), true);
                            } else if (rango.getTipoNotificacion().equals(3)) {//notificación por correo
                                guardarMensaje(strategy.getMessage(entry.getKey()).getDestinatario(),
                                        strategy.getMessage(entry.getKey()).toString(),
                                        rango.getTipoNotificacion(), false);
                                enviarMensajesxCorreo();
                            } else if (rango.getTipoNotificacion().equals(4)) {//notificacion por pantalla y correo
                                guardarMensaje(strategy.getMessage(entry.getKey()).getDestinatario(),
                                        strategy.getMessage(entry.getKey()).toString(),
                                        rango.getTipoNotificacion(), false);
                                enviarMensajesxCorreo();
                            }
                        }
                    }
                }
            }
        }
    }


    public Mensaje guardarMensaje(Usuario superior, String mensajeTexto, TipoNotificacion tipoNotificacion, Boolean leido) {
        Usuario root = usuarioRepo.findOneByUsername("root");
        Mensaje mensaje = new Mensaje();
        mensaje.setFechaCreacion(new Date());
        mensaje.setTipoNotificacion(tipoNotificacion);
        mensaje.setId(UUID.randomUUID());
        mensaje.setUsuarioFuente(root);
        mensaje.setUsuarioDestino(superior);
        mensaje.setLeido(leido);
        mensaje.setMensaje(mensajeTexto);
        mensajeRepo.save(mensaje);
        return mensaje;
    }

    public void enviarMensajesxCorreo() {
        List<Mensaje> listaMensajes = mensajeRepo.findAllByTipoNotificacionAndLeido(TipoNotificacion.EMAIL, false);
        for (Mensaje msj : listaMensajes) {
            Usuario user = usuarioRepo.findOne(msj.getUsuarioDestino().getId());
            PlantillaDto plantilla = new PlantillaDto("alertaRango");
            Map<String, Object> parametros = new HashMap<String, Object>();
            parametros.put("msg", msj.getMensaje());
            plantilla.setParametros(parametros);

            MensajeDto mensajeDto = new MensajeDto();
            mensajeDto.setAsunto("Alerta de ejecucion de tareas en curso");
            mensajeDto.setDestino(user.getEmail());
            mensajeDto.setPlantilla(plantilla);

            // Envía el email
            MailSender mailSender = new MailSender();
            if (mailSender.sendMail(mensajeDto)) {
                msj.setLeido(true);
                mensajeRepo.save(msj);
            } else {
                LOGGER.error("Error enviando correo al destino: " + mensajeDto.getDestino()
                        + " Mensaje Id: " + msj.getId());
            }
        }
    }
}

