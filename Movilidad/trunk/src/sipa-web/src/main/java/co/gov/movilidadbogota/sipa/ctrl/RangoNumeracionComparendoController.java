package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.cli.SipaServicesClient;
import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.biz.comp.OrganismoTransito;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoNotificacion;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoRangoNumeracionRepo;
import co.gov.movilidadbogota.sipa.data.biz.num.EstadoNumeroComparendo;
import co.gov.movilidadbogota.sipa.data.biz.num.NumeroComparendo;
import co.gov.movilidadbogota.sipa.data.biz.num.RangoNumeracion;
import co.gov.movilidadbogota.sipa.data.biz.num.RangoNumeracionRepo;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.doc.TipoDocumental;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.data.gen.Parametro;
import co.gov.movilidadbogota.sipa.data.gen.ValorParametroRepo;
import co.gov.movilidadbogota.sipa.data.util.MensajeHelper;
import co.gov.movilidadbogota.sipa.util.ControllerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

/**
 * Created by victor.hernandez on 16/06/2017.
 */
@Controller
public class RangoNumeracionComparendoController extends BaseController implements ApplicationListener {

    public static final String CONTROLLER_PATH = "/rangos";
    public static final String CONTROLLER_PATH_LIST = CONTROLLER_PATH;
    public static final String CONTROLLER_PATH_CREATE = CONTROLLER_PATH + "/new";
    private static final Logger LOG = LoggerFactory.getLogger(RangoNumeracionComparendoController.class);

    private String PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_PENULTIMA_ASIGNACION;
    private String PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_TEMPRANA;
    private String PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_NORMAL;
    private String PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_URGENTE;

    private String PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_PENULTIMA_ASIGNACION;
    private String PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_TEMPRANA;
    private String PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_NORMAL;
    private String PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_URGENTE;

    private String MENSAJE_ALERTA_TEMPRANA_CONTRAVENCIONES;
    private String MENSAJE_ALERTA_NORMAL_CONTRAVENCIONES;
    private String MENSAJE_ALERTA_URGENTE_CONTRAVENCIONES;
    private String MENSAJE_ALERTA_TEMPRANA_TRANSPORTE_PUBLICO;
    private String MENSAJE_ALERTA_NORMAL_TRANSPORTE_PUBLICO;
    private String MENSAJE_ALERTA_URGENTE_TRANSPORTE_PUBLICO;

    private final ValorParametroRepo valorParametroRepo;
    private final RangoNumeracionRepo rangoNumeracionRepo;
    private final RangoFromValidator rangoFromValidator;
    private final TipoRangoNumeracionRepo tipoRangoNumeracionRepo;
    private final MensajeHelper mensajeHelper;

    @Autowired
    private MessageSource messages;

    @Autowired
    private SipaServicesClient sipaServicesClient;

    public RangoNumeracionComparendoController(RangoNumeracionRepo rangoNumeracionRepo, RangoFromValidator rangoFromValidator,
                                               ValorParametroRepo valorParametroRepo, TipoRangoNumeracionRepo tipoRangoNumeracionRepo, MensajeHelper mensajeHelper) {
        this.rangoNumeracionRepo = rangoNumeracionRepo;
        this.rangoFromValidator = rangoFromValidator;
        this.tipoRangoNumeracionRepo = tipoRangoNumeracionRepo;
        this.valorParametroRepo = valorParametroRepo;
        this.mensajeHelper = mensajeHelper;
    }

    @GetMapping(value = CONTROLLER_PATH_LIST)
    public String getRangos(Model model) {
        LOG.debug("Buscar los rangos de numeracion");
        model.addAttribute("rangos", consultarLosRangosDeNumeracion());
        return "rangos-consulta";
    }

    private List<RangoNumeracion> consultarLosRangosDeNumeracion() {
        return rangoNumeracionRepo.findAll(new Sort("inicio"));
    }

    @GetMapping(value = CONTROLLER_PATH_CREATE)
    public String crearNuevoRango(Model model, Locale locale) {
        LOG.debug("ingresa al metodo de crear un nuevo rango");
        model.addAttribute("rangoForm", new RangoNumeracion());
        model.addAttribute("tiposRangoNumeracion", ControllerUtils.listToMapWithDefault(tipoRangoNumeracionRepo.findAll(), "nombre", messages, locale));
        return "rangos-new";
    }


    @PostMapping(CONTROLLER_PATH_LIST)
    public String guardarRango(@ModelAttribute("rangoForm") RangoNumeracion rangoNumeracion,
                               BindingResult result,
                               @RequestPart(value = "file") MultipartFile evidenciaFile,
                               final RedirectAttributes redirectAttributes, Model model, Locale locale) {
        LOG.debug("ingresa al metodo guardar el rango nuevo de numeración");
        rangoFromValidator.validate(rangoNumeracion, result);
        List<RangoNumeracion> listaRangosVerificadosSuperior;
        List<RangoNumeracion> listaRangosVerificadosInferior;
        List<RangoNumeracion> rangosNumeracion;

        if (evidenciaFile == null || evidenciaFile.isEmpty()) {
            result.rejectValue(
                    "respuestaSolicitud",
                    "rangoFrom.message.fileEmpty",
                    "Debe adjuntar la evidencia."
            );
        }

        if (result.hasErrors()) {
            model.addAttribute("tiposRangoNumeracion", ControllerUtils.listToMapWithDefault(tipoRangoNumeracionRepo.findAll(), "nombre", messages, locale));
            return "rangos-new";
        }
        try {
            listaRangosVerificadosSuperior = rangoNumeracionRepo.findByInicioLessThanEqualAndFinGreaterThanEqualAndTipoRangoNumeracion(rangoNumeracion.getInicio(), rangoNumeracion.getInicio(), rangoNumeracion.getTipoRangoNumeracion());
            if (listaRangosVerificadosSuperior.size() != 0) {
                result.rejectValue("inicio", "rangoFrom.message.rangosSuperpuestosInicio");
            } else {
                listaRangosVerificadosInferior = rangoNumeracionRepo.findByInicioLessThanEqualAndFinGreaterThanEqualAndTipoRangoNumeracion(rangoNumeracion.getFin(), rangoNumeracion.getFin(), rangoNumeracion.getTipoRangoNumeracion());

                if (listaRangosVerificadosInferior.size() != 0) {
                    result.rejectValue("fin", "rangoFrom.message.rangosSuperpuestosFin");
                }
            }

            if (result.hasErrors()) {
                model.addAttribute("tiposRangoNumeracion", ControllerUtils.listToMapWithDefault(tipoRangoNumeracionRepo.findAll(), "nombre", messages, locale));
                return "rangos-new";
            }
            crearListaRangoNumeroComparendos(rangoNumeracion);
            rangoNumeracion.setId(UUID.randomUUID());
            rangoNumeracion.setFechaSolicitud(new Date());
            //rangoNumeracion.setTipoRangoNumeracion(TipoRangoNumeracion.COMPARENDO_TRANSITO);
            rangoNumeracion.setOrganismoTransito(OrganismoTransito.SDM);
            //guarda el documento con la evidencia
            LOG.info("Guardando en documento");
            Documento documento = sipaServicesClient.getDocumentos().addDocumento(
                    new Documento(
                            evidenciaFile.getContentType(),
                            evidenciaFile.getOriginalFilename(),
                            TipoDocumental.EVIDENCIA_REGISTRO_RANGOS
                    ),
                    evidenciaFile.getBytes()
            );
            rangoNumeracion.setRespuestaSolicitud(documento);
            rangoNumeracionRepo.save(rangoNumeracion);

        } catch (Exception e) {
            model.addAttribute("fashError", "Ocurrió un error intente más tarde");
            LOG.error("Ocurrió un error", e);
            redirectAttributes.addFlashAttribute("flashError", "Se presentó un error");
        }

        redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK, "Rango de numeración creado correctamente");
        return String.format("redirect:%s", CONTROLLER_PATH);
    }

    private void crearListaRangoNumeroComparendos(RangoNumeracion rangoNumeracion) {
        List<NumeroComparendo> list = new ArrayList<>();
        for (int i = rangoNumeracion.getInicio(); i <= rangoNumeracion.getFin(); i++) {
            list.add(new NumeroComparendo(rangoNumeracion, i));
        }
        rangoNumeracion.setNumerosComparendo(list);
    }


    /**
     * Programa la tarea diaria de consultar el rango de comparendos que cumplen
     * con las siguientes reglas de negocio:
     * Cuando se detecta que los rangos deben ser solicitados, el sistema crea un conjunto de alertas que se notifican
     * por correo electrónico y aparecen también en la interfaz de usuario como mensajes de notificación.
     * <p>
     * Alerta temprana (verde): Esta notificación se debe crear cuando se haya usado el 70% de los rangos de la última
     * asignación y el porcentaje de la penúltima asignación.
     * <p>
     * Alerta normal (amarillo): Esta notificación se debe crear cuando la última asignación de rangos llegue a 80% y
     * la penúltima asignación de rangos llegue al 100%.
     * <p>
     * Alerta urgente (rojo): Esta notificación se debe crear cuando el último rango llega al 90%. Se debe notificar
     * al Director de procesos administrativos y al Subdirector de contravenciones o de transporte público.
     *
     * @throws Exception Cuando ocurre algún error
     */
    @Scheduled(cron = "0 0 5 * * *")
    public void verificarRangoNumeracionComparendos() throws Exception {

        // Consulta Rango_Numeracion2 seleccionar los numeros de comparendos dentro de el ultimo rango de Transporte Publico
        List<RangoNumeracion> listaRangoNumeracionTransportePublico = rangoNumeracionRepo.findByRangoNumeracionOrdenadoTransportePublico();
        RangoNumeracion rangoRecienteTransportePublico = new RangoNumeracion();
        RangoNumeracion rangoAntiguoTransportePublico = new RangoNumeracion();
        if (listaRangoNumeracionTransportePublico.size() >= 1) {
            rangoRecienteTransportePublico = listaRangoNumeracionTransportePublico.get(0);
        }
        if (listaRangoNumeracionTransportePublico.size() >= 2) {
            rangoAntiguoTransportePublico = listaRangoNumeracionTransportePublico.get(1);
        }
        // Consulta Rango_Numeracion2 seleccionar los numeros de comparendos dentro de el ultimo rango de Transporte Publico
        List<RangoNumeracion> listaRangoNumeracionContravenciones = rangoNumeracionRepo.findByRangoNumeracionOrdenadoContravenciones();
        RangoNumeracion rangoRecienteContravenciones = new RangoNumeracion();
        RangoNumeracion rangoAntiguoContravenciones = new RangoNumeracion();
        if (listaRangoNumeracionContravenciones.size() >= 1) {
            rangoRecienteContravenciones = listaRangoNumeracionContravenciones.get(0);
        }
        if (listaRangoNumeracionContravenciones.size() >= 2) {
            rangoAntiguoContravenciones = listaRangoNumeracionContravenciones.get(1);
        }
        //Se realizan el calculo del porcentaje disponible
        float porcentajeAnteriorTP = calcularPorcentajeComparendosNoDisponibles(rangoAntiguoTransportePublico.getNumerosComparendo());
        float porcentajeRecienteTP = calcularPorcentajeComparendosNoDisponibles(rangoRecienteTransportePublico.getNumerosComparendo());
        float porcentajeAnteriorC = calcularPorcentajeComparendosNoDisponibles(rangoRecienteContravenciones.getNumerosComparendo());
        float porcentajeRecienteC = calcularPorcentajeComparendosNoDisponibles(rangoAntiguoContravenciones.getNumerosComparendo());
        LOG.info("Se busca la informacion del usuario configurado para SIPA");
        if (porcentajeAnteriorTP == Integer.valueOf(PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_PENULTIMA_ASIGNACION)
                && porcentajeRecienteTP < Integer.valueOf(PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_NORMAL)
                && porcentajeRecienteTP >= Integer.valueOf(PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_TEMPRANA)) {
            LOG.info(MENSAJE_ALERTA_TEMPRANA_TRANSPORTE_PUBLICO);
            mensajeHelper.enviarMensaje(Usuario.ROOT, MENSAJE_ALERTA_TEMPRANA_TRANSPORTE_PUBLICO, TipoNotificacion.PANTALLA, TipoNotificacion.EMAIL);
        } else if (porcentajeAnteriorTP == Integer.valueOf(PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_PENULTIMA_ASIGNACION)
                && porcentajeRecienteTP < Integer.valueOf(PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_URGENTE)
                && porcentajeRecienteTP >= Integer.valueOf(PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_NORMAL)) {
            LOG.info(MENSAJE_ALERTA_NORMAL_TRANSPORTE_PUBLICO);
            mensajeHelper.enviarMensaje(Usuario.ROOT, MENSAJE_ALERTA_NORMAL_TRANSPORTE_PUBLICO, TipoNotificacion.PANTALLA, TipoNotificacion.EMAIL);
        } else if (porcentajeAnteriorTP == Integer.valueOf(PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_PENULTIMA_ASIGNACION)
                && porcentajeRecienteTP >= Integer.valueOf(PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_URGENTE)) {
            LOG.info(MENSAJE_ALERTA_URGENTE_CONTRAVENCIONES);
            mensajeHelper.enviarMensaje(Usuario.ROOT, MENSAJE_ALERTA_URGENTE_TRANSPORTE_PUBLICO, TipoNotificacion.PANTALLA, TipoNotificacion.EMAIL);
        }

        /*
        Si sólo hay un rango entonces se asume que el anterior está completo
         */
        if (rangoAntiguoContravenciones.getNumerosComparendo() == null || rangoAntiguoContravenciones.getNumerosComparendo().size() == 0) {
            porcentajeRecienteC = porcentajeAnteriorC;
            porcentajeAnteriorC = 100;
        }

        if (porcentajeAnteriorC == Integer.valueOf(PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_PENULTIMA_ASIGNACION)
                && porcentajeRecienteC < Integer.valueOf(PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_NORMAL)
                && porcentajeRecienteC >= Integer.valueOf(PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_TEMPRANA)) {
            LOG.info(MENSAJE_ALERTA_TEMPRANA_CONTRAVENCIONES);
            mensajeHelper.enviarMensaje(Usuario.ROOT, MENSAJE_ALERTA_TEMPRANA_CONTRAVENCIONES, TipoNotificacion.PANTALLA, TipoNotificacion.EMAIL);
        } else if (porcentajeAnteriorC == Integer.valueOf(PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_PENULTIMA_ASIGNACION)
                && porcentajeRecienteC < Integer.valueOf(PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_URGENTE)
                && porcentajeRecienteC >= Integer.valueOf(PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_NORMAL)) {
            LOG.info(MENSAJE_ALERTA_NORMAL_CONTRAVENCIONES);
            mensajeHelper.enviarMensaje(Usuario.ROOT, MENSAJE_ALERTA_NORMAL_CONTRAVENCIONES, TipoNotificacion.PANTALLA, TipoNotificacion.EMAIL);
        } else if (porcentajeAnteriorC == Integer.valueOf(PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_PENULTIMA_ASIGNACION)
                && porcentajeRecienteC >= Integer.valueOf(PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_URGENTE)) {
            LOG.info(MENSAJE_ALERTA_URGENTE_CONTRAVENCIONES);
            mensajeHelper.enviarMensaje(Usuario.ROOT, MENSAJE_ALERTA_URGENTE_CONTRAVENCIONES, TipoNotificacion.PANTALLA, TipoNotificacion.EMAIL);
        }
    }

    private float calcularPorcentajeComparendosNoDisponibles(List<NumeroComparendo> listaComparendo) {
        if (listaComparendo == null)
            return 0;
        int contador = 0;
        for (NumeroComparendo numComparendo : listaComparendo) {
            if (Objects.equals(numComparendo.getEstado().getId(), EstadoNumeroComparendo.DISPONIBLE.getId())) {
                contador = contador + 1;
            }
        }
        if (contador == 0)
            return 100;
        return 100 - (float) contador / (float) listaComparendo.size() * 100;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {

        if (!(applicationEvent instanceof ContextRefreshedEvent))
            return;

        PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_PENULTIMA_ASIGNACION = valorParametroRepo.findValorVigenteByClave(Parametro.PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_PENULTIMA_ASIGNACION, new Date()).getValor();
        PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_TEMPRANA = valorParametroRepo.findValorVigenteByClave(Parametro.PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_TEMPRANA, new Date()).getValor();
        PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_NORMAL = valorParametroRepo.findValorVigenteByClave(Parametro.PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_NORMAL, new Date()).getValor();
        PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_URGENTE = valorParametroRepo.findValorVigenteByClave(Parametro.PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_URGENTE, new Date()).getValor();

        PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_PENULTIMA_ASIGNACION = valorParametroRepo.findValorVigenteByClave(Parametro.PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_PENULTIMA_ASIGNACION, new Date()).getValor();
        PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_TEMPRANA = valorParametroRepo.findValorVigenteByClave(Parametro.PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_TEMPRANA, new Date()).getValor();
        PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_NORMAL = valorParametroRepo.findValorVigenteByClave(Parametro.PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_NORMAL, new Date()).getValor();
        PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_URGENTE = valorParametroRepo.findValorVigenteByClave(Parametro.PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_URGENTE, new Date()).getValor();

        MENSAJE_ALERTA_TEMPRANA_CONTRAVENCIONES = "Se ha consumido el " + PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_TEMPRANA + "% del último rango de numeración de comparendos de tránsito.";
        MENSAJE_ALERTA_NORMAL_CONTRAVENCIONES = "Se ha consumido el " + PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_NORMAL + "% del último rango de numeración de comparendos de tránsito.";
        MENSAJE_ALERTA_URGENTE_CONTRAVENCIONES = "Se ha consumido el " + PORCENTAJE_ASIGNACION_RANGOS_CONTRAVENCIONES_ALERTA_URGENTE + "% del último rango de numeración de comparendos de tránsito.";
        MENSAJE_ALERTA_TEMPRANA_TRANSPORTE_PUBLICO = "Se ha consumido el " + PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_TEMPRANA + "% del último rango de numeración de informes de infracción de transporte público.";
        MENSAJE_ALERTA_NORMAL_TRANSPORTE_PUBLICO = "Se ha consumido el " + PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_NORMAL + "% del último rango de numeración de informes de infracción de transporte público.";
        MENSAJE_ALERTA_URGENTE_TRANSPORTE_PUBLICO = "Se ha consumido el " + PORCENTAJE_ASIGNACION_RANGOS_TRANSPORTE_PUBLICO_ALERTA_URGENTE + "% del último rango de numeración de informes de infracción de transporte público.";

    }
}
