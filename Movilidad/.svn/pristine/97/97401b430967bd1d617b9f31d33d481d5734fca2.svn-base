package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.biz.comp.AcuseRecibo;
import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendoRepository;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.trans.InputAtribute;
import co.gov.movilidadbogota.sipa.util.ControllerUtils;
import co.gov.movilidadbogota.sipa.util.GeneradorCSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Marcel.Bohorquez on 3/14/17.
 */
@Controller
@RequestMapping(value = "/inconsistencias")
public class InconsistenciasController extends UtilController {


    private static final String ESTADO_FORMATO_COMPARENDO_INCONSISTENTE = "3";


    private static final Logger LOG = LoggerFactory.getLogger(InconsistenciasConsulta.class);
    @Autowired
    MessageSource messages;
    SimpleDateFormat formateador = new SimpleDateFormat("yyyyMMdd");
    List<FormatoComparendo> c = new ArrayList<>();
    @Autowired
    private
    FormatoComparendoRepository formatoComparendoRepository;
    /* @Autowired
     private InconsistenciasFormValidator inconsistenciasFormValidator;*/
    @Autowired
    private
    InconsistenciasConsulta inconsistenciasConsulta;
    @Value("${sipa.serv.comparendos.registroComparendo}")
    private String registroCompaendoUrl;

    @RequestMapping(value = "/consulta")
    public String consulta(Model model) {
        try {
            LOG.debug("Buscar los comparendos inconsistentes");
            model.addAttribute("inconsistencias",
                    formatoComparendoRepository.findByEstado(FormatoComparendo.ESTADO_INCONSISTENTE));
            //Inicializa la forma
            // model.addAttribute("inconsistenciasForm", new InconsistenciasForm());
        } catch (Exception e) {
            model.addAttribute("error", "Ocurrió un error intente más tarde");
        }
        return "inconsistencias-consulta";
    }

    @RequestMapping(value = "/consulta/buscar", method = RequestMethod.POST)
    public String consulta(@Valid @ModelAttribute("inconsistenciasForm") InconsistenciasForm inconsistenciasForm, BindingResult result, Model model, final RedirectAttributes redirectAttributes) {

        new InconsistenciasFormValidator().validateQuery(inconsistenciasForm, result);

        if (result.hasErrors()) {
            model.addAttribute("inconsistencias", formatoComparendoRepository.findAll());
            return "inconsistencias-consulta";
        }

        try {
            c = inconsistenciasConsulta.findFormatoComparendo(inconsistenciasForm);
            if (!c.isEmpty()) {
                LOG.info("Listado de numeros de comparendo encontrados");
                for (FormatoComparendo comp : c) {
                    LOG.info("Comparendo número:" + comp.getNumero());
                }
                model.addAttribute("inconsistencias", c);
            } else {
                model.addAttribute("sinresultados", "No se encontraron resultados, intente nuevamente");
            }
        } catch (Exception e) {
            model.addAttribute("fashError", "Ocurrió un error intente más tarde");
            LOG.error("Ocurrió un error", e);
            redirectAttributes.addFlashAttribute("flashError", "Se presentó un error");
        }
        return "inconsistencias-consulta";
    }

    @RequestMapping(value = "/inconsistencias-modificar/{id}")
    public String modificarFormatoComparendo(@PathVariable(value = "id") UUID id, Model model) {
        HashMap<String, String> incosisteciasFormato;
        List<ObjectError> incosistenciasErrores;
        List<InputAtribute> atributos;
        HashMap<String, String> camposAmodificarIncosistencias;
        try {
            FormatoComparendo formatoComparendo = formatoComparendoRepository.findOne(id);
            model.addAttribute("formatoComparendo", formatoComparendo);
            incosistenciasErrores = deserializarInconsistencias(formatoComparendo);
            atributos = crearCamposConIncosisteciasInputs(incosistenciasErrores);
            model.addAttribute("atributos", atributos);
        } catch (Exception e) {
            ControllerUtils.handleException(LOG, model, e);
        }
        return "inconsistencias-modificar";
    }

    private HashMap<String, String> deserializarInconsistenciasTest() {
        HashMap<String, String> pruebaInputs;
        pruebaInputs = new HashMap();

        pruebaInputs.put("placaAgente", "sfdfsd");
        pruebaInputs.put("placaGrua", "sdfsdfsdf");
        pruebaInputs.put("placaVehiculo", "sdfsdfsdf");
        pruebaInputs.put("fecha", "asdasda");
        return pruebaInputs;
    }

    private List<List<String>> crearCamposConIncosistecias(HashMap<String, String> incosisteciasFormato) {
        List<List<String>> camposAmodificarIncosistencias;

        camposAmodificarIncosistencias = new ArrayList();
        for (String key :
                incosisteciasFormato.keySet()) {
            List<String> atributos = new ArrayList();
            atributos.add(key);
            atributos.add("formatoComparendo." + key);
            atributos.add("formatoComparendo." + key);
            atributos.add(key);
            camposAmodificarIncosistencias.add(atributos);
        }
        return camposAmodificarIncosistencias;
    }


    private List<InputAtribute> crearCamposConIncosisteciasInputsTest(HashMap<String, String> incosisteciasFormato) {
        List<InputAtribute> camposAmodificarIncosistencias;

        camposAmodificarIncosistencias = new ArrayList();
        for (String key :
                incosisteciasFormato.keySet()) {
            InputAtribute atributos = new InputAtribute();
            atributos.setBindingInput("formatoComparendo." + key);
            atributos.setIdInput(key);
            atributos.setLabelname(key.replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2"));
            camposAmodificarIncosistencias.add(atributos);
        }
        return camposAmodificarIncosistencias;
    }

    private List<InputAtribute> crearCamposConIncosisteciasInputs(List<ObjectError> incosistenciasErrores) {
        List<InputAtribute> camposAmodificarIncosistencias;

        camposAmodificarIncosistencias = new ArrayList();
        for (ObjectError error :
                incosistenciasErrores) {
            InputAtribute atributos = new InputAtribute();
            atributos.setBindingInput("formatoComparendo." + ((FieldError) error).getField());
            atributos.setIdInput(((FieldError) error).getField());
            atributos.setLabelname(((FieldError) error).getField().replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2"));
            camposAmodificarIncosistencias.add(atributos);
        }
        return camposAmodificarIncosistencias;
    }

    private HashMap<String, String> crearCamposConIncosisteciasMap(HashMap<String, String> incosisteciasFormato) {
        HashMap<String, String> camposAmodificarIncosistencias;

        camposAmodificarIncosistencias = new HashMap();
        for (String key :
                incosisteciasFormato.keySet()) {
            camposAmodificarIncosistencias.put(key, "formatoComparendo." + key);
        }
        return camposAmodificarIncosistencias;
    }

    private HashMap<String, String> deserializarInconsistenciasTest3(FormatoComparendo formatoComparendo) throws IOException, ClassNotFoundException {
        HashMap<String, String> camposAModificar;
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream;

        byteArrayInputStream = new ByteArrayInputStream(formatoComparendo.getResultadoValidacion());
        objectInputStream = new ObjectInputStream(byteArrayInputStream);
        camposAModificar = (HashMap<String, String>) objectInputStream.readObject();

        return camposAModificar;
    }


    private List<ObjectError> deserializarInconsistencias(FormatoComparendo formatoComparendo) throws IOException, ClassNotFoundException {
        List<ObjectError> camposAModificar;
        ByteArrayInputStream byteArrayInputStream;
        ObjectInputStream objectInputStream;

        byteArrayInputStream = new ByteArrayInputStream(formatoComparendo.getResultadoValidacion());
        objectInputStream = new ObjectInputStream(byteArrayInputStream);
        camposAModificar = (List<ObjectError>) objectInputStream.readObject();

        return camposAModificar;
    }

    @PostMapping(value = "/inconsistencias-guardar/{id}")
    public String guardarFormatoComparendo(@PathVariable(value = "id") UUID id,
                                           @Valid @ModelAttribute("formatoComparendo") FormatoComparendo fComp,
                                           BindingResult result,
                                           Model model,
                                           RedirectAttributes redirectAttributes,
                                           Locale locale,
                                           OAuth2Authentication auth) {
        //LOG.debug("Se registrara la modificación de un comparendo inconsistente. Id: " + id);
        FormatoComparendo formatoComparendoEnBaseDeDatos = new FormatoComparendo();
        List<ObjectError> incosistenciasErrores;
        FieldError fieldError;
        String campoSet;
        try {
            LOG.debug("Se realiza las validaciones de negocio");
            LOG.debug("Realizando la modificación.");
            formatoComparendoEnBaseDeDatos = formatoComparendoRepository.findOne(id);
            incosistenciasErrores = this.deserializarInconsistencias(formatoComparendoEnBaseDeDatos);
            for (ObjectError error :
                    incosistenciasErrores) {
                fieldError = ((FieldError) error);
                realizarSetFormatoComparendo(formatoComparendoEnBaseDeDatos, fComp, fieldError.getField());
            }
            formatoComparendoEnBaseDeDatos.setCorregido(true);
            LOG.info("No comparendo a corregido: " + formatoComparendoEnBaseDeDatos.toString());

            MapBindingResult errors = new MapBindingResult(new HashMap<>(), "fmt");

            RestTemplate restTemplate = new RestTemplate();
            MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
            if (auth != null && auth.getDetails() instanceof OAuth2AuthenticationDetails) {
                OAuth2AuthenticationDetails oauth = (OAuth2AuthenticationDetails) auth.getDetails();
                if (org.apache.commons.lang3.StringUtils.isNotBlank(oauth.getTokenValue())) {
                    String token = String.format("%s %s", oauth.getTokenType(), oauth.getTokenValue());
                    headers.add("Authorization", token);
                }
            }
            headers.add("Content-Type", "application/json");
            HttpEntity<FormatoComparendo> request = new HttpEntity<>(formatoComparendoEnBaseDeDatos, headers);
            LOG.info(String.format("Formato comparendo service: %s", request));
            AcuseRecibo acuse = restTemplate.postForObject(registroCompaendoUrl, request, AcuseRecibo.class);

//            if (!acuse.getErrores().isEmpty()) {
//                LOG.warn("errors: " + acuse.getErrores());
//            } else {
//                model.addAttribute("inconsistencias", formatoComparendoRepository.findByEstadoNumeroComparendo("" + EstadoNumeroComparendo.INCONSISTENTE.getId()));
//
//            }

            model.addAttribute("inconsistencias",
                    formatoComparendoRepository.findByEstado(FormatoComparendo.ESTADO_INCONSISTENTE));
            model.addAttribute("error", "mensaje de prueba");

        } catch (Exception e) {
            LOG.error(String.format("Error al modificar información del comparendo."), e);
            return null;
        }
        redirectAttributes.addFlashAttribute(Constants.FLASH_MESSAGE_OK, "El comparendo ha sido modificado!");

        return "inconsistencias-consulta";
    }

    private void realizarSetFormatoComparendo(FormatoComparendo formatoComparendoEnBaseDeDatos, FormatoComparendo fComp, String field) {
        String campoGet = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
        String campoSet = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
        Method[] metodos;
        Method metodoSet;
        Object valorModificar;

        metodos = formatoComparendoEnBaseDeDatos.getClass().getMethods();
        for (final Method metodo : metodos) {
            LOG.info("\nNombre del MÉTODO: " + metodo.getName());

            try {

                if (metodo.getName().equals(campoGet)) {
                    valorModificar = metodo.invoke(fComp);
                    metodoSet = formatoComparendoEnBaseDeDatos.getClass().getMethod(campoSet, String.class);
                    metodoSet.invoke(formatoComparendoEnBaseDeDatos, valorModificar);
                }

            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }

    }

    @RequestMapping(value = "/inconsistencias-descarga/")
    public String descargarInconsistencias(HttpServletResponse response, Model model,
                                           Locale locale) {
        List<FormatoComparendo> c = formatoComparendoRepository.findAll();
        GeneradorCSV generarCSV = new GeneradorCSV();
        byte[] bytes = generarCSV.ConstructorFileCSV(c, new StringArrayMapper<FormatoComparendo>() {
            @Override
            public String[] build(FormatoComparendo formatoComparendo) {
                if (formatoComparendo.getOrganismoTransito() == null) {
                    formatoComparendo.setOrganismoTransito("");
                }
                if (formatoComparendo.getCategoriaLicencia() == null) {
                    formatoComparendo.setCategoriaLicencia("");
                }
                if (formatoComparendo.getTipoViaSecundaria() == null) {
                    formatoComparendo.setTipoViaSecundaria("");
                }
                if (formatoComparendo.getTipoViaPrincipal() == null) {
                    formatoComparendo.setTipoViaPrincipal("");
                }
                if (formatoComparendo.getTipo() == null) {
                    formatoComparendo.setTipo("");
                }
                if (formatoComparendo.getTipoInfractor() == null) {
                    formatoComparendo.setTipoInfractor("");
                }
                if (formatoComparendo.getTipoIdentificacionTestigo() == null) {
                    formatoComparendo.setTipoIdentificacionTestigo("");
                }
                if (formatoComparendo.getTipoIdentificacionPropietario() == null) {
                    formatoComparendo.setTipoIdentificacionPropietario("");
                }
                if (formatoComparendo.getTipoIdentificacionInfractor() == null) {
                    formatoComparendo.setTipoIdentificacionInfractor("");
                }
                if (formatoComparendo.getTipoIdentificacionInfractor() == null) {
                    formatoComparendo.setTipoIdentificacionInfractor("");
                }
                if (formatoComparendo.getTelefonoTestigo() == null) {
                    formatoComparendo.setTelefonoTestigo("");
                }
                if (formatoComparendo.getTelefonoInfractor() == null) {
                    formatoComparendo.setTelefonoInfractor("");
                }
                if (formatoComparendo.getClaseServicioVehiculo() == null) {
                    formatoComparendo.setClaseServicioVehiculo("");
                }
                if (formatoComparendo.getTarjetaOperacion() == null) {
                    formatoComparendo.setTarjetaOperacion("");
                }
                if (formatoComparendo.getPlacaVehiculo() == null) {
                    formatoComparendo.setPlacaVehiculo("");
                }
                if (formatoComparendo.getPlacaAgente() == null) {
                    formatoComparendo.setPlacaAgente("");
                }
                if (formatoComparendo.getNumero() == null) {
                    formatoComparendo.setNumero("");
                }
                if (formatoComparendo.getNumeroLicencia() == null) {
                    formatoComparendo.setNumeroLicencia("");
                }
                if (formatoComparendo.getNumeroInmovilizacion() == null) {
                    formatoComparendo.setNumeroInmovilizacion("");
                }
                if (formatoComparendo.getNumeroGrua() == null) {
                    formatoComparendo.setNumeroGrua("");
                }
                if (formatoComparendo.getNumeroPatio() == null) {
                    formatoComparendo.setNumeroPatio("");
                }
                if (formatoComparendo.getNitEmpresa() == null) {
                    formatoComparendo.setNitEmpresa("");
                }
                if (formatoComparendo.getNombreEmpresa() == null) {
                    formatoComparendo.setNombreEmpresa("");
                }
                if (formatoComparendo.getNombreNumeroViaPrincipal() == null) {
                    formatoComparendo.setNombreNumeroViaPrincipal("");
                }
                if (formatoComparendo.getNombreNumeroViaSecundaria() == null) {
                    formatoComparendo.setNombreNumeroViaSecundaria("");
                }
                if (formatoComparendo.getPrimerApellidoPropietario() == null) {
                    formatoComparendo.setPrimerApellidoPropietario("");
                }
                if (formatoComparendo.getSegundoApellidoPropietario() == null) {
                    formatoComparendo.setSegundoApellidoPropietario("");
                }
                if (formatoComparendo.getPrimerNombrePropietario() == null) {
                    formatoComparendo.setPrimerNombrePropietario("");
                }
                if (formatoComparendo.getSegundoNombrePropietario() == null) {
                    formatoComparendo.setSegundoNombrePropietario("");
                }
                if (formatoComparendo.getPrimerApellidoInfractor() == null) {
                    formatoComparendo.setPrimerApellidoInfractor("");
                }
                if (formatoComparendo.getSegundoApellidoInfractor() == null) {
                    formatoComparendo.setSegundoApellidoInfractor("");
                }
                if (formatoComparendo.getPrimerNombreInfractor() == null) {
                    formatoComparendo.setPrimerNombreInfractor("");
                }
                if (formatoComparendo.getSegundoNombreInfractor() == null) {
                    formatoComparendo.setSegundoNombreInfractor("");
                }
                if (formatoComparendo.getPrimerApellidoAgente() == null) {
                    formatoComparendo.setPrimerApellidoAgente("");
                }
                if (formatoComparendo.getSegundoApellidoAgente() == null) {
                    formatoComparendo.setSegundoApellidoAgente("");
                }
                if (formatoComparendo.getPrimerNombreAgente() == null) {
                    formatoComparendo.setPrimerNombreAgente("");
                }
                if (formatoComparendo.getSegundoNombreAgente() == null) {
                    formatoComparendo.setSegundoNombreAgente("");
                }

                if (formatoComparendo.getPrimerApellidoTestigo() == null) {
                    formatoComparendo.setPrimerApellidoTestigo("");
                }
                if (formatoComparendo.getSegundoApellidoTestigo() == null) {
                    formatoComparendo.setSegundoApellidoTestigo("");
                }
                if (formatoComparendo.getPrimerNombreTestigo() == null) {
                    formatoComparendo.setPrimerNombreTestigo("");
                }
                if (formatoComparendo.getSegundoNombreTestigo() == null) {
                    formatoComparendo.setSegundoNombreTestigo("");
                }
                if (formatoComparendo.getDireccionPatio() == null) {
                    formatoComparendo.setDireccionPatio("");
                }
                if (formatoComparendo.getNumeroPatio() == null) {
                    formatoComparendo.setNumeroPatio("");
                }
                if (formatoComparendo.getObservaciones() == null) {
                    formatoComparendo.setObservaciones("");
                }
                if (formatoComparendo.getPlacaGrua() == null) {
                    formatoComparendo.setPlacaGrua("");
                }
                if (formatoComparendo.getValor() == null) {
                    formatoComparendo.setValor("");
                }
                if (formatoComparendo.getEdadInfractor() == null) {
                    formatoComparendo.setEdadInfractor("");
                }

                if (formatoComparendo.getEntidadAgente() == null) {
                    formatoComparendo.setEntidadAgente("");
                }
                if (formatoComparendo.getFecha() == null) {
                    formatoComparendo.setFecha("");
                }
                if (formatoComparendo.getHora() == null) {
                    formatoComparendo.setHora("");
                }
                if (formatoComparendo.getLocalidad() == null) {
                    formatoComparendo.setLocalidad("");
                }
                if (formatoComparendo.getModeloVehiculo() == null) {
                    formatoComparendo.setModeloVehiculo("");
                }
                if (formatoComparendo.getMunicipio() == null) {
                    formatoComparendo.setMunicipio("");
                }
                if (formatoComparendo.getMunicipioDireccionInfractor() == null) {
                    formatoComparendo.setMunicipioDireccionInfractor("");
                }
                if (formatoComparendo.getNumeroIdentificacionInfractor() == null) {
                    formatoComparendo.setNumeroIdentificacionInfractor("");
                }
                if (formatoComparendo.getNumeroIdentificacionPropietario() == null) {
                    formatoComparendo.setNumeroIdentificacionPropietario("");
                }
                if (formatoComparendo.getNumeroIdentificacionTestigo() == null) {
                    formatoComparendo.setNumeroIdentificacionTestigo("");
                }
                if (formatoComparendo.getPasajerosVehiculo() == null) {
                    formatoComparendo.setPasajerosVehiculo("");
                }


                return new String[]{
                        formatoComparendo.getNumero(),
                        formatoComparendo.getCategoriaLicencia(),
                        formatoComparendo.getTipoVehiculo(),
                        formatoComparendo.getCodigoInfraccion(),
                        formatoComparendo.getDepartamento(),
                        formatoComparendo.getDireccion(),
                        formatoComparendo.getDireccionInfractor(),
                        formatoComparendo.getDireccionPatio(),
                        formatoComparendo.getEdadInfractor(),
                        formatoComparendo.getEntidadAgente(),
                        formatoComparendo.getFecha(),
                        formatoComparendo.getHora(),
                        formatoComparendo.getLocalidad(),
                        formatoComparendo.getModeloVehiculo(),
                        formatoComparendo.getMunicipio(),
                        formatoComparendo.getMunicipioDireccionInfractor(),
                        formatoComparendo.getNitEmpresa(),
                        formatoComparendo.getPrimerNombreAgente(),
                        formatoComparendo.getSegundoNombreAgente(),
                        formatoComparendo.getPrimerApellidoAgente(),
                        formatoComparendo.getSegundoApellidoAgente(),
                        formatoComparendo.getPrimerNombreInfractor(),
                        formatoComparendo.getSegundoNombreInfractor(),
                        formatoComparendo.getPrimerApellidoInfractor(),
                        formatoComparendo.getSegundoApellidoInfractor(),
                        formatoComparendo.getPrimerNombrePropietario(),
                        formatoComparendo.getSegundoNombrePropietario(),
                        formatoComparendo.getPrimerApellidoPropietario(),
                        formatoComparendo.getSegundoApellidoPropietario(),
                        formatoComparendo.getPrimerNombreTestigo(),
                        formatoComparendo.getSegundoNombreTestigo(),
                        formatoComparendo.getPrimerApellidoTestigo(),
                        formatoComparendo.getSegundoApellidoTestigo(),
                        formatoComparendo.getNumeroGrua(),
                        formatoComparendo.getNumeroIdentificacionInfractor(),
                        formatoComparendo.getNumeroIdentificacionPropietario(),
                        formatoComparendo.getNumeroIdentificacionTestigo(),
                        formatoComparendo.getNumeroInmovilizacion(),
                        formatoComparendo.getNumeroLicencia(),
                        formatoComparendo.getNumeroPatio(),
                        formatoComparendo.getObservaciones(),
                        formatoComparendo.getOrganismoTransito(),
                        formatoComparendo.getPasajerosVehiculo(),
                        formatoComparendo.getPlacaAgente(),
                        formatoComparendo.getPlacaGrua(),
                        formatoComparendo.getPlacaVehiculo(),
                        formatoComparendo.getClaseServicioVehiculo(),
                        formatoComparendo.getTarjetaOperacion(),
                        formatoComparendo.getTelefonoInfractor(),
                        formatoComparendo.getTelefonoTestigo(),
                        formatoComparendo.getTipo(),
                        formatoComparendo.getTipoIdentificacionInfractor(),
                        formatoComparendo.getTipoIdentificacionPropietario(),
                        formatoComparendo.getTipoIdentificacionTestigo(),
                        formatoComparendo.getTipoInfractor(),
                        formatoComparendo.getTipoViaPrincipal(),
                        formatoComparendo.getTipoViaSecundaria(),
                        formatoComparendo.getValor()
                };
            }
        });
        FileInputStream fileInputStream = null;
        Date date = new Date();
        String dateStr = formateador.format(date);
        File file = new File("inconsistenciaComparendos" + dateStr + ".txt");
        try {
            // Se agregan los bytes al archivo.
            FileOutputStream fileOuputStream = new FileOutputStream(file);
            fileOuputStream.write(bytes);
            fileOuputStream.close();
            generarCSV.descargarArchivo(file, response);
            if (file.delete())
                LOG.info("El fichero ha sido borrado satisfactoriamente");
            else
                LOG.info("El fichero no puede ser borrado");
        } catch (Exception e) {
            LOG.error("Se produjo un error: " + e.getMessage());
        }
        LOG.debug("Se registrara la modificación de un comparendo inconsistente. Id: " + c.size());
        return null;
    }

    private void serializarErrores(FormatoComparendo fmt, MapBindingResult errors) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(byteArrayOutputStream);

            outputStream.writeObject(errors.getAllErrors());
            fmt.setResultadoValidacion(byteArrayOutputStream.toByteArray());

        } catch (Exception e) {
            LOG.error("Ocurrió un error", e);
        }
    }


    public interface StringArrayMapper<T> {
        String[] build(T t);
    }


}
