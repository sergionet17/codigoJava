package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.cli.SipaServicesClient;
import co.gov.movilidadbogota.sipa.commons.log.LogUtils;
import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.util.ControllerUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Controlador de todas las herramientas que se usan para probar el sistema. Este controlador no debe estar en
 * el ambiente productivo ya que sólo se debe usar para la fase de pruebas.
 */
@Controller
public class TestController extends BaseController {

    public static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    public static final String PATH = "/test";
    public static final String PATH_CARGA_COMPARENDOS = PATH + "/comparendos";
    public static final String PATH_EJECUTAR_ALARMA = PATH + "/alarmas";

    private static final String[] FIELD_NAMES = new String[]{
            "id", "consecutivo", "numero", "fecha", "hora", "tipo", "direccion", "localidad", "municipio",
            "departamento", "codigoInfraccion", "placaVehiculo", "tipoVehiculo", "claseServicioVehiculo",
            "modeloVehiculo", "pasajerosVehiculo", "tipoIdentificacionInfractor", "numeroIdentificacionInfractor",
            "nombresInfractor", "apellidosInfractor", "numeroLicencia", "categoriaLicencia", "direccionInfractor",
            "municipioDireccionInfractor", "edadInfractor", "telefonoInfractor", "emailInfractor", "tipoInfractor",
            "organismoTransito", "tipoIdentificacionPropietario", "numeroIdentificacionPropietario",
            "nombresPropietario", "apellidosPropietario", "nitEmpresa", "nombreEmpresa", "tarjetaOperacion",
            "nombresAgente", "apellidosAgente", "placaAgente", "entidadAgente", "numeroPatio", "direccionPatio",
            "numeroGrua", "placaGrua", "numeroInmovilizacion", "observaciones", "tipoIdentificacionTestigo",
            "numeroIdentificacionTestigo", "nombresTestigo", "apellidosTestigo", "direccionTestigo", "telefonoTestigo"
    };

    @Autowired
    private Configuration freemarkerConfiguration;

    @Autowired
    private SipaServicesClient sipaServicesClient;

    @Autowired
    private ObjectMapper jsonObjectMapper;

    @Autowired
    private AlarmaController alarmaController;

    @Autowired
    private RangoNumeracionComparendoController rangoNumeracionComparendoController;

    /**
     * Muestra la página con todas las herramientas para pruebas
     *
     * @return El nombre de la vista
     */
    @GetMapping(PATH)
    public String index() {
        return "test-index";
    }

    /**
     * Muestra el formulario para la carga de comparendos mediante un archivo CSV y un archivo que representa la imagen
     * del comparendo.
     *
     * @param model El modelo para la vista
     * @return El nombre de la vista
     */
    @GetMapping(PATH_CARGA_COMPARENDOS)
    public String cargaComparendos(Model model) {
        return "test-carga-comparendos";
    }

    @PostMapping(PATH_CARGA_COMPARENDOS)
    public String cargaComparendos(@RequestPart(value = "datos") MultipartFile archivoDatos,
                                   @RequestPart(value = "imagen") MultipartFile imagenComparendo,
                                   Model model, RedirectAttributes redirectAttributes) {

        /*
          Obtiene los bytes de la imagen de comparendo
         */
        byte[] bytesImagen = null;
        try {
            bytesImagen = imagenComparendo.getBytes();
        } catch (IOException e) {
            ControllerUtils.handleException(LOGGER, model, e);
            return "test-carga-comparendos";
        }

        /*
          Lee el archivo de datos y por cada uno de los registros presentes, realiza el envío del formato
          correspondiente al servicio de comparendos.
         */
        try {
            byte[] finalBytesImagen = bytesImagen;
            Long count = new CSVReader(new InputStreamReader(archivoDatos.getInputStream()), ',', '"')
                    .readAll().stream()
                    .map(this::buildMap).map(this::buildJsonMessage).map(this::buildFormatoComparendo)
                    .map(formato -> {
                        try {
                            return sipaServicesClient.getComparendos().imponerComparendoManual(formato, finalBytesImagen);
                        } catch (Exception e) {
                            LogUtils.logAndRethrow(LOGGER, new RuntimeException("Error imponiendo el comparendo", e));
                            return null;
                        }
                    }).count()
            ;
            redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK,
                    "Comparendos cargados con éxito: " + count);
        } catch (Exception e) {
            ControllerUtils.handleException(LOGGER, model, e);
            return "test-carga-comparendos";
        }

        return String.format("redirect:%s", PATH);
    }

    private FormatoComparendo buildFormatoComparendo(String json) {
        try {
            return jsonObjectMapper.readValue(json, FormatoComparendo.class);
        } catch (IOException e) {
            LogUtils.logAndRethrow(LOGGER, new RuntimeException("Error leyendo JSON de comparendo", e));
        }
        return null;
    }

    private String buildJsonMessage(Map<String, String> map) {
        Template t = null;
        try {
            t = freemarkerConfiguration.getTemplate("test-carga-comparendos-json-message.ftl");
        } catch (Exception e) {
            LogUtils.logAndRethrow(LOGGER,
                    new RuntimeException("No se encuentra la plantilla 'test-carga-comparendos-json-message'", e));
        }

        try {
            StringWriter writer = new StringWriter();
            assert t != null;
            t.process(map, writer);
            return writer.toString();
        } catch (Exception e) {
            LogUtils.logAndRethrow(LOGGER,
                    new RuntimeException("Error combinando 'test-carga-comparendos-json-message'", e));
        }
        return null;
    }

    private Map<String, String> buildMap(String[] values) {
        HashMap<String, String> map = new HashMap<>(values.length);
        for (int i = 0; i < values.length; i++) {
            map.put(FIELD_NAMES[i], values[i]);
        }
        return map;
    }

    @GetMapping(PATH_EJECUTAR_ALARMA)
    public String ejecutarAlarmas(RedirectAttributes redirectAttributes) {
        try {
            /*
            Ejecuta la verificación de uso de los rangos de numeración
             */
            rangoNumeracionComparendoController.verificarRangoNumeracionComparendos();

            /*
            Ejecuta todas las alertas
             */
            alarmaController.doScheduledWork();
            redirectAttributes.addFlashAttribute(
                    Constants.FLASH_MESSAGE_OK,
                    "Se ejecutaron todas las alarmas"
            );
        } catch (Exception e) {
            ControllerUtils.handleException(LOGGER, redirectAttributes, e);
        }
        return "redirect:" + PATH;
    }

}
