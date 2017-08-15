package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.cli.SipaServicesClient;
import co.gov.movilidadbogota.sipa.data.biz.comp.Comparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.ComparendoRepository;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoRangoNumeracionRepo;
import co.gov.movilidadbogota.sipa.data.biz.num.AnulacionNumeracion;
import co.gov.movilidadbogota.sipa.data.biz.num.AnulacionNumeracionRepo;
import co.gov.movilidadbogota.sipa.data.biz.num.NumeroComparendo;
import co.gov.movilidadbogota.sipa.data.biz.num.NumeroComparendoRepository;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.serv.api.CommandContext;
import co.gov.movilidadbogota.sipa.serv.api.DocumentoService;
import co.gov.movilidadbogota.sipa.util.ControllerUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

/**
 * Created by Admin on 22/06/2017.
 */
@Controller
public class RangoAnulacionNumeracionComparendoController extends BaseController {

    public static final String CONTROLLER_PATH = "/rangosAnulacion";
    public static final String CONTROLLER_PATH_LIST = CONTROLLER_PATH + "";
    public static final String CONTROLLER_PATH_VERIFY = CONTROLLER_PATH + "/verficarRangoAnulacion";

    private static final Logger LOG = LoggerFactory.getLogger(RangoNumeracionComparendoController.class);

    @Autowired
    private AnulacionNumeracionRepo anulacionNumeracionRepo;

    @Autowired
    private NumeroComparendoRepository numeroComparendoRepository;

    @Autowired
    private ComparendoRepository comparendoRepository;

    @Autowired
    private TipoRangoNumeracionRepo tipoRangoNumeracionRepo;

    @Autowired
    private MessageSource messages;

    @Autowired
    private SipaServicesClient sipaServicesClient;

    public RangoAnulacionNumeracionComparendoController(AnulacionNumeracionRepo anulacionNumeracionRepo, NumeroComparendoRepository numeroComparendoRepository, ComparendoRepository comparendoRepository, TipoRangoNumeracionRepo tipoRangoNumeracionRepo) {
        this.anulacionNumeracionRepo = anulacionNumeracionRepo;
        this.numeroComparendoRepository = numeroComparendoRepository;
        this.comparendoRepository = comparendoRepository;
        this.tipoRangoNumeracionRepo = tipoRangoNumeracionRepo;
    }

    @GetMapping(value = CONTROLLER_PATH_LIST)
    public String irRangosAnulacion(Model model, Locale locale) {
        LOG.debug("ingresa a la pantalla de anulacion de rangos");
        model.addAttribute("anulacionNumeracionForm", new AnulacionNumeracion());
        model.addAttribute("tiposRangoNumeracion", ControllerUtils.listToMapWithDefault(tipoRangoNumeracionRepo.findAll(), "nombre", messages, locale));
        return "verificar-rango-anulacion";
    }

    @PostMapping(value = CONTROLLER_PATH_VERIFY)
    public String verificarAnulacionRangosNumeracionComparendos(
            @ModelAttribute("anulacionNumeracionForm") AnulacionNumeracion anulacionNumeracion,
            BindingResult result, Model model, RedirectAttributes redirectAttributes, Locale locale) {
        // List<Integer> numerosParaAnular = new ArrayList();
        List<NumeroComparendo> numerosComparendosAnular;
        List<Comparendo> comparendosAnular = null;
        List<AnulacionNumeracion> numeroComparendosAnularList;
        List<String> valores = new ArrayList<>();
        NumeroComparendo numeroComparendo;
        boolean rangoTextoAceptado = false;
        String rangoTexto;
        String[] splitTexto;
        int numeroInicialRango;
        int numeroFinalRango;
        String numeroTexto = "";
        try {
            LOG.debug("ingresa a la verifcación de los rangos ");
            rangoTexto = anulacionNumeracion.getRangoTexto().trim();
            rangoTextoAceptado = verificarRangoTexto(rangoTexto);
            if (!rangoTextoAceptado) {
                result.rejectValue("rangoTexto", "anulacionNumeracionForm.message.cadenaTextoNoCumple");
                return "verificar-rango-anulacion";

            }
            if (anulacionNumeracion.getTipoRangoNumeracion() == null) {
                model.addAttribute("tiposRangoNumeracion", ControllerUtils.listToMapWithDefault(tipoRangoNumeracionRepo.findAll(), "nombre", messages, locale));
                result.rejectValue("tipoRangoNumeracion", "anulacionNumeracionForm.message.SeleccionarTipoRangoNumeracion");
                return "verificar-rango-anulacion";

            }

            if (rangoTexto.contains("-")) {
                if (rangoTexto.substring(0, 1).contains("[") ||
                        rangoTexto.substring(rangoTexto.length() - 1, rangoTexto.length()).contains("[")) {
                    rangoTexto = rangoTexto.substring(1, rangoTexto.length());
                    rangoTexto = rangoTexto.substring(0, rangoTexto.length() - 1);
                }
                splitTexto = rangoTexto.split("-");
                numeroInicialRango = Integer.valueOf(splitTexto[0]);
                numeroFinalRango = Integer.valueOf(splitTexto[1]);
                for (int i = numeroInicialRango; i <= numeroFinalRango; i++) {
                    valores.add(String.format("11001000%s", StringUtils.leftPad("" + i, 12, '0')));
                }
            } else {
                splitTexto = rangoTexto.split(",");
                for (int i = 0; i < splitTexto.length; i++) {
                    String numero = splitTexto[i];
                    if (StringUtils.isBlank(numero))
                        continue;
                    numero = numero.trim();
                    if (numero.contains("[")) {
                        valores.add(String.format("11001000%s", StringUtils.leftPad(numero.substring(1, 2), 12, '0')));
                    } else if (numero.contains("[")) {
                        valores.add(String.format("11001000%s", StringUtils.leftPad(numero.substring(0, 1), 12, '0')));
                    } else {
                        valores.add(String.format("11001000%s", StringUtils.leftPad(numero, 12, '0')));
                    }

                }
            }

            numerosComparendosAnular = numeroComparendoRepository.findByValorInAndRangoNumeracionTipoRangoNumeracion(valores, anulacionNumeracion.getTipoRangoNumeracion());
            numeroComparendosAnularList = anulacionNumeracionRepo.findByNumerosInAndTipoRangoNumeracion(
                    numerosComparendosAnular,
                    anulacionNumeracion.getTipoRangoNumeracion()
            );
            if (numeroComparendosAnularList.size() == 0) {
                anulacionNumeracion.setNumeros(numerosComparendosAnular);
                anulacionNumeracion.setId(UUID.randomUUID());
                anulacionNumeracion.setFecha(new Date());
                anulacionNumeracionRepo.save(anulacionNumeracion);
                /*
                Generación automática de la resolución de anulación
                comparendosAnular = comparendoRepository.findByNumeroIn(numerosComparendosAnular);
                 */
                CommandContext response = sipaServicesClient.getDocumentos().generarDocumento(
                        "resolucion-automatica-anulacion",
                        new CommandContext().put("idAnulacionNumeracion", anulacionNumeracion.getId())
                );
                UUID documentoId = UUID.fromString((String) response.get(DocumentoService.CTX_DOCUMENTO_ID));
                anulacionNumeracion.setResolucionAutomatica(new Documento(documentoId));
                anulacionNumeracionRepo.save(anulacionNumeracion);
            } else {
                LOG.error("Se intento insertar un rango que ya existe");
                result.rejectValue("rangoTexto", "anulacionNumeracionForm.message.RangoAnulacionExiste");
                model.addAttribute("tiposRangoNumeracion", ControllerUtils.listToMapWithDefault(tipoRangoNumeracionRepo.findAll(), "nombre", messages, locale));
                return "verificar-rango-anulacion";
            }

            model.addAttribute("comparendosAnular", comparendosAnular);
            model.addAttribute("numerosComparendosAnular", numerosComparendosAnular);

        } catch (DataIntegrityViolationException dive) {
            LOG.error("Se intento insertar un rango que ya existe", dive);
            result.rejectValue("rangoTexto", "anulacionNumeracionForm.message.RangoAnulacionExiste");
            model.addAttribute("tiposRangoNumeracion", ControllerUtils.listToMapWithDefault(tipoRangoNumeracionRepo.findAll(), "nombre", messages, locale));
            return "verificar-rango-anulacion";
        } catch (NumberFormatException nfe) {
            LOG.error("los numeros de rangos que se desean validar son muy grandes", nfe);
            result.rejectValue("rangoTexto", "anulacionNumeracionForm.message.NumerosMuyGrandes");
            model.addAttribute("tiposRangoNumeracion", ControllerUtils.listToMapWithDefault(tipoRangoNumeracionRepo.findAll(), "nombre", messages, locale));
            return "verificar-rango-anulacion";
        } catch (Exception e) {
            model.addAttribute("fashError", "Ocurrió un error intente más tarde");
            LOG.error("Ocurrió un error", e);
            redirectAttributes.addFlashAttribute("flashError", "Se presentó un error");
        }

        return "confirmacion-rango-anulacion";
    }

    private boolean verificarRangoTexto(String rangoText) {
        boolean rangoTextoAceptado;
        int contador = 0;
        char[] arregloCaracteresRangoTexto = rangoText.toCharArray();
        rangoTextoAceptado = estadoInicial(arregloCaracteresRangoTexto, contador);

        return rangoTextoAceptado;
    }

    private boolean estadoInicial(char[] arregloCaracteresRangoTexto, int contador) {
        LOG.debug("entra a el estado inicial");
        boolean cadenaAceptada = false;
        if (contador < arregloCaracteresRangoTexto.length) {
            if (arregloCaracteresRangoTexto[contador] == '[') {
                contador++;
                cadenaAceptada = estadoNumeroUno(arregloCaracteresRangoTexto, contador);
            } else if (Character.isDigit(arregloCaracteresRangoTexto[contador])) {
                contador++;
                cadenaAceptada = estadoNumeroDos(arregloCaracteresRangoTexto, contador);
            }
        }
        return cadenaAceptada;
    }

    private boolean estadoNumeroUno(char[] arregloCaracteresRangoTexto, int contador) {
        LOG.debug("entra a el estado numero uno");
        boolean cadenaAceptada = false;
        if (contador < arregloCaracteresRangoTexto.length
                && Character.isDigit(arregloCaracteresRangoTexto[contador])) {
            contador++;
            cadenaAceptada = estadoNumeroDos(arregloCaracteresRangoTexto, contador);
        }
        return cadenaAceptada;
    }

    private boolean estadoNumeroDos(char[] arregloCaracteresRangoTexto, int contador) {
        LOG.debug("entra a el estado numero dos");
        boolean cadenaAceptada = false;
        if (contador < arregloCaracteresRangoTexto.length) {
            if (arregloCaracteresRangoTexto[contador] == ',') {
                contador++;
                cadenaAceptada = estadoNumeroUno(arregloCaracteresRangoTexto, contador);
            } else if (arregloCaracteresRangoTexto[contador] == '-') {
                contador++;
                cadenaAceptada = estadoNumeroTres(arregloCaracteresRangoTexto, contador);
            } else if (arregloCaracteresRangoTexto[contador] == ']') {
                contador++;
                cadenaAceptada = estadoNumeroCuatro(arregloCaracteresRangoTexto, contador);
            } else if (Character.isDigit(arregloCaracteresRangoTexto[contador])) {
                contador++;
                cadenaAceptada = estadoNumeroDos(arregloCaracteresRangoTexto, contador);
            }
        } else {
            cadenaAceptada = true;
        }
        return cadenaAceptada;
    }

    private boolean estadoNumeroCuatro(char[] arregloCaracteresRangoTexto, int contador) {
        LOG.debug("entra a el estado numero cuatro");
        boolean cadenaAceptada = false;
        if (contador == arregloCaracteresRangoTexto.length) {
            cadenaAceptada = true;
        }
        return cadenaAceptada;
    }


    private boolean estadoNumeroTres(char[] arregloCaracteresRangoTexto, int contador) {
        LOG.debug("entra a el estado numero tres");
        boolean cadenaAceptada = false;
        if (contador < arregloCaracteresRangoTexto.length
                && Character.isDigit(arregloCaracteresRangoTexto[contador])) {
            contador++;
            cadenaAceptada = estadoNumeroDos(arregloCaracteresRangoTexto, contador);
        }
        return cadenaAceptada;
    }

}
