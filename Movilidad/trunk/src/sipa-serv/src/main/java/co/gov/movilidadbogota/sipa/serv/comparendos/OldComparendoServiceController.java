package co.gov.movilidadbogota.sipa.serv.comparendos;

import co.gov.movilidadbogota.sipa.data.biz.comp.*;
import co.gov.movilidadbogota.sipa.data.biz.gen.*;
import co.gov.movilidadbogota.sipa.data.biz.num.EstadoNumeroComparendo;
import co.gov.movilidadbogota.sipa.data.biz.num.NumeroComparendo;
import co.gov.movilidadbogota.sipa.data.biz.num.NumeroComparendoRepository;
import co.gov.movilidadbogota.sipa.data.doc.*;
import co.gov.movilidadbogota.sipa.data.gen.*;
import co.gov.movilidadbogota.sipa.data.id.TipoDocumento;
import co.gov.movilidadbogota.sipa.data.loc.*;
import co.gov.movilidadbogota.sipa.data.persona.Persona;
import co.gov.movilidadbogota.sipa.data.persona.PersonaRepo;
import co.gov.movilidadbogota.sipa.data.persona.TipoPersona;
import co.gov.movilidadbogota.sipa.serv.doc.ReportBuilder;
import co.gov.movilidadbogota.sipa.serv.api.EventoFinanciero;
import co.gov.movilidadbogota.sipa.serv.comparendos.validator.OldFormatoComparendoValidator;
import co.gov.movilidadbogota.sipa.serv.api.FinancieroService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DirectFieldBindingResult;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * Clase de servicios relacionados con el registro y verificación de comparendos
 */
@RestController
@Api(value = "comparendos", description = "Recursos para la interacción con comparendos", produces = "application/json")
public class OldComparendoServiceController {

    private static final Logger LOG = LoggerFactory.getLogger(OldComparendoServiceController.class);

    @Autowired
    ValorParametroRepo valorParametroRepo;
    @Autowired
    private FinancieroService financieroService;
    @Autowired
    private OldFormatoComparendoValidator oldFormatoComparendoValidator;
    @Autowired
    private FormatoComparendoRepo formatoComparendoRepo;
    @Autowired
    private ComparendoRepository comparendoRepo;
    @Autowired
    private CarpetaRepo carpetaRepo;
    @Autowired
    private PersonaRepo personaRepo;
    @Autowired
    private EmailRepo emailRepo;
    @Autowired
    private TelefonoRepo telefonoRepo;
    @Autowired
    private CategoriaLicenciaRepo categoriaLicenciaRepo;
    @Autowired
    private LicenciaRepo licenciaRepo;
    @Autowired
    private VehiculoRepo vehiculoRepo;
    @Autowired
    private NumeroComparendoRepository numeroComparendoRepo;
    @Autowired
    private OrganismoTransitoRepo organismoTransitoRepo;
    @Autowired
    private DireccionRepo direccionRepo;
    @Autowired
    private TestigoRepo testigoRepo;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @ApiOperation(value = "Registra un comparendo", notes = "", response = AcuseRecibo.class)
    public AcuseRecibo registro(@RequestBody FormatoComparendo fmt) {

        if (LOG.isDebugEnabled()) {
            LOG.debug(String.format("Formato de comparendo recibido: %s", fmt));
        } else {
            LOG.info(String.format("Formato de comparendo recibido: %s", fmt.getNumero()));
        }

        MapBindingResult errors = new MapBindingResult(new HashMap<>(), "fmt");
        ComparendoDto comparendoDto = new ComparendoDto();
        oldFormatoComparendoValidator.validar(fmt, errors, comparendoDto);
        boolean formatoComparendoTieneErrores = false;


        // Se Inserta datos en la tabla FORMATO_COMPARENDO
        if (errors.hasErrors()) {
            LOG.warn("errors: " + errors);
            fmt.setEstado(FormatoComparendo.ESTADO_INCONSISTENTE);
            serializarErrores(fmt, errors);
            //fmt.setResultadoValidacion(errors.toString());
            LOG.warn("******* Observaciones: " + errors.toString());
            formatoComparendoTieneErrores = true;
        } else {
            fmt.setEstado(FormatoComparendo.ESTADO_IMPUESTO);
        }
        // Almacena la informacion del formatoComparendo en la base de datos.
        //formatoComparendoRepo.save(fmt);
        // Almacena las imágenes del formato en un directorio
/*
        Carpeta carpeta;
        try {

            carpeta = new Carpeta(fmt.getNumero(), "Expediente comparendo " + fmt.getNumero());
            ResponseEntity responseCarpeta = documentoService.save(carpeta);
            if (responseCarpeta.getStatusCode().equals(HttpStatus.OK)) {
                Documento documento = new Documento(
                        "Imagen comparendo",
                        "image/tiff",
                        "imagen-comparendo.tiff",
                        TipoDocumental.COMPARENDO
                );
                MultipartFile file = new MockMultipartFile(
                        "archivo",
                        "comparendo-" + fmt.getNumero() + ".tiff",
                        "image/tiff",
                        fmt.getImagenComparendo1()
                );
                ResponseEntity responseDocumento = documentoService.save(carpeta.getId(), documento, file);
                if (responseDocumento.getStatusCode().equals(HttpStatus.OK) == false) {
                    throw new Exception("No se pudo guardar el archivo: " + responseDocumento.getBody());
                } else {
                    fmt.setImagenComparendo1Documento(documento);
                }
            } else {
                throw new Exception("No se pudo guardar el archivo");
            }

            if (!formatoComparendoTieneErrores && fmt.isCorregido()) {
                formatoComparendoRepo.save(fmt);
            } else if (!fmt.isCorregido()) {
                fmt.setId(UUID.randomUUID());
                formatoComparendoRepo.save(fmt);
            }
        } catch (Exception e) {
            LOG.error("No se puede almacenar las imágenes del comparendo", e);
            throw new RuntimeException(e);
        }
*/
        if (!formatoComparendoTieneErrores && fmt.isCorregido()) {
            formatoComparendoRepo.save(fmt);
        } else if (!fmt.isCorregido()) {
            fmt.setId(UUID.randomUUID());
            formatoComparendoRepo.save(fmt);
        }

        AcuseRecibo acuseRecibo = new AcuseRecibo();
        if (errors.hasErrors()) {
            acuseRecibo.setErrores(errors.toString());
            return acuseRecibo;
        }

        UUID carpetaId = UUID.randomUUID();
        Carpeta carpeta = new Carpeta(carpetaId.toString(), carpetaId.toString());
        carpetaRepo.save(carpeta);
        Comparendo comparendo = guardarComparendo(fmt,
                comparendoDto.getInfraccion(),
                comparendoDto.getTipoDocumentoInfractor(),
                comparendoDto.getTipoDocumentoPropietario(),
                comparendoDto.getMunicipioInfraccion(),
                comparendoDto.getLocalidadInfraccion(),
                comparendoDto.getMunicipioInfractor(),
                comparendoDto.getNumeroComparendo(),
                comparendoDto.getOrganismoTransito(),
                comparendoDto.getCategoriaLicencia(),
                comparendoDto.getTipoDocumentoTestigo(),
                comparendoDto.getTipoInfractor(),
                carpeta
        );
        if (TipoComparendo.ELECTRONICO.getId().toString().equals(fmt.getTipo())) {
            //Se realiza el llamdo al metodo de crear PDF
            // TODO: La dirección de la imagen en el reporte está quemada y no funciona en otros computadores
            //byte[] bytes = generarOrdenComparendo(fmt);
//            byte[] bytes = "Sin datos".getBytes();
//            Documento
//            Documento doc = documentoService.guardar(new DocumentoRequest(
//                    "comparendo-" + fmt.getNumero(),
//                    "Orden de comparendo",
//                    Constants.MIME_TYPE_PDF,
//                    bytes
//            ));
//            comparendo.setImagen(doc);
        }

        EventoFinanciero eventoFinanciero = new EventoFinanciero(
                "MultaComparendoPresuntivo",
                "Imposición del comparendo #" + comparendo.getNumero().getValor(),
                comparendo.getFechaImposicion(),
                "comparendo",
                comparendo.getId(),
                comparendo.getValorNominal()
        );
        BindingResult registroErrors = new DirectFieldBindingResult(eventoFinanciero, "eventoFinanciero");
//        try {
//            financieroService.registrar(eventoFinanciero, registroErrors);
//        } catch (Exception e) {
//            LOG.error(String.format("No se pudo realizar el registro del evento financiero: %s", eventoFinanciero), e);
//            throw new RuntimeException("Guardando el registro financiero: ", e);
//        }

        return acuseRecibo;
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

    /**
     * Metodo que almacena en la tabla comparendo
     */
    private Comparendo guardarComparendo(
            FormatoComparendo formatoComparendo,
            Infraccion infraccion,
            TipoDocumento tipoDocumentoInfractor,
            TipoDocumento tipoDocumentoPropietario,
            Municipio municipioInfraccion,
            Localidad localidadInfraccion,
            Municipio municipioInfractor,
            NumeroComparendo numeroComparendo,
            OrganismoTransito organismoTransito,
            CategoriaLicencia categoriaLicencia,
            TipoDocumento tipoDocumentoTestigo,
            TipoInfractor tipoInfractor,
            Carpeta carpeta
    ) {

        //Instanciamos la clase Comparendo insertando su contenido.
        Comparendo comparendo = new Comparendo();

        //Clase Vehiculo
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setPlacaVehiculo(formatoComparendo.getPlacaVehiculo());
        TipoVehiculo tipoVehiculo = new TipoVehiculo();
        //tipoVehiculo.setNombre(formatoComparendo.getClaseVehiculo());
        tipoVehiculo.setId(Integer.valueOf(formatoComparendo.getTipoVehiculo()));
        vehiculo.setTipo(tipoVehiculo);
        vehiculo.setModelo(formatoComparendo.getModeloVehiculo());

        // Datos Propietario
        Persona personaPropietario = new Persona();
        personaPropietario.setTipoDocumentoIdentidad(tipoDocumentoPropietario);
        personaPropietario.setNumeroDocumentoIdentidad(formatoComparendo.getNumeroIdentificacionPropietario());
        vehiculo.setPropietario(personaPropietario);

        // Datos Tipo comparendo
        comparendo.setTipoComparendo(new TipoComparendo(new Integer(formatoComparendo.getTipo())));

        // Datos Tipo infractor
        comparendo.setTipoInfractor(tipoInfractor);

        // Datos Servicio Vehiculo
        ClaseServicioVehiculo claseServicioVehiculo = new ClaseServicioVehiculo();
        claseServicioVehiculo.setId(Integer.valueOf(formatoComparendo.getClaseServicioVehiculo()));
        vehiculo.setClase(claseServicioVehiculo);
        vehiculo.setTarjetaOperacion(formatoComparendo.getTarjetaOperacion());
        vehiculo.setNumeroPasajeros(formatoComparendo.getPasajerosVehiculo());
        vehiculo.setId(UUID.randomUUID());

        // Datos Infractor
        Persona personaInfractor = personaRepo.findByTipoDocumentoIdentidadAndNumeroDocumentoIdentidad(
                tipoDocumentoInfractor,
                formatoComparendo.getNumeroIdentificacionInfractor()
        );
        if (personaInfractor == null) {
            personaInfractor = new Persona();
            personaInfractor.setTipoDocumentoIdentidad(tipoDocumentoInfractor);
            personaInfractor.setNumeroDocumentoIdentidad(formatoComparendo.getNumeroIdentificacionInfractor());
        }

        personaInfractor.setPrimerApellido(formatoComparendo.getPrimerApellidoInfractor());
        personaInfractor.setPrimerNombre(formatoComparendo.getPrimerNombreInfractor());
        personaInfractor.setSegundoApellido(formatoComparendo.getSegundoApellidoInfractor());
        personaInfractor.setSegundoNombre(formatoComparendo.getSegundoNombreInfractor());
        if (personaInfractor.getId() == null)
            personaInfractor.setId(UUID.randomUUID());
        personaInfractor.setTipoPersona(TipoPersona.NATURAL);
        personaRepo.save(personaInfractor);

        telefonoRepo.save(new Telefono(personaInfractor, formatoComparendo.getTelefonoInfractor()));

        emailRepo.save(new Email(personaInfractor, formatoComparendo.getEmailInfractor()));

//        Direccion direccion = new Direccion(personaInfractor, formatoComparendo.getDireccionInfractor());
//        direccion.setLocalidad(localidadInfraccion);
//        direccion.setSistemaOrigen(SistemaOrigen.DUUPS);
//        direccion.setMunicipio(municipioInfraccion);
        //direccionRepo.save(direccion);

        //comparendo.setDireccion(direccion);

        Licencia licencia = null;
        if (formatoComparendo.getNumeroLicencia() != null) {
            licencia = licenciaRepo.findOneByNumero(formatoComparendo.getNumeroLicencia());
            if (licencia == null) {
                licencia = new Licencia();
                licencia.setCategoriaLicencia(categoriaLicencia);
                licencia.setOrganismoTransito(organismoTransito);
                licencia.setNumero(formatoComparendo.getNumeroLicencia());
                licencia.setId(UUID.randomUUID());
                licencia.setPersona(personaInfractor);
                licenciaRepo.save(licencia);
            }
        }

        Patio patio = new Patio();
        patio.setNumero(formatoComparendo.getNumeroPatio());

        // Datos del comparendo
        comparendo.setId(formatoComparendo.getId());

        comparendo.setLicencia(licencia);
        comparendo.setPropietario(personaInfractor);
        try {
            comparendo.setFechaImposicion(Constants.DATE_FORMAT.parse(formatoComparendo.getFecha()));
        } catch (ParseException e) {
            // No se hace nada porque la validación de la fecha se hizo con antelación
        }
        comparendo.setFechaNotificacion(new Date());
        comparendo.setFechaRegistro(new Date());

        // Datos Testigo
        Testigo testigo = new Testigo();
        Persona personaTestigo = personaRepo.findByTipoDocumentoIdentidadAndNumeroDocumentoIdentidad(
                tipoDocumentoTestigo,
                formatoComparendo.getNumeroIdentificacionTestigo()
        );
        if (personaTestigo == null) {
            personaTestigo = new Persona();
            personaTestigo.setNumeroDocumentoIdentidad(formatoComparendo.getNumeroIdentificacionTestigo());
            personaTestigo.setTipoDocumentoIdentidad(tipoDocumentoTestigo);
        }

        personaTestigo.setPrimerApellido(formatoComparendo.getPrimerApellidoTestigo());
        personaTestigo.setPrimerNombre(formatoComparendo.getPrimerNombreTestigo());
        personaTestigo.setSegundoApellido(formatoComparendo.getSegundoApellidoTestigo());
        personaTestigo.setSegundoNombre(formatoComparendo.getSegundoNombreTestigo());
        if (personaTestigo.getId() == null)
            personaTestigo.setId(UUID.randomUUID());
        personaTestigo.setTipoPersona(TipoPersona.NATURAL);
        //personaRepo.save(personaTestigo);
        //testigo.setPersona(personaTestigo);
        //testigoRepo.save(testigo);

        //telefonoRepo.save(new Telefono(personaTestigo, formatoComparendo.getTelefonoTestigo()));

        //Direccion direccionTestigo = new Direccion(personaTestigo, formatoComparendo.getDireccionTestigo());
        //direccionRepo.save(direccionTestigo);
        //List<Direccion> listDireccionesTestigo = new ArrayList<>();
        //listDireccionesTestigo.add(direccionTestigo);

        //personaTestigo.setDirecciones(listDireccionesTestigo);
        //comparendo.setTestigo(testigo);
        vehiculo.setPropietario(personaInfractor);
        vehiculoRepo.save(vehiculo);
        comparendo.setVehiculo(vehiculo);

        // TODO: Cambiar al esquema de Expediente
        //comparendo.setCarpeta(carpeta);
        comparendo.setEstado(EstadoComparendo.PENDIENTE_FALLO);
        comparendo.setFormato(formatoComparendo);
        comparendo.setInfraccion(infraccion);
        comparendo.setInfractor(personaInfractor);

        // Calcula el valor del comparendo a partir de la infracción
        ValorParametro smmlvParametro = valorParametroRepo.findValorVigenteByClave(Parametro.SMMLV, comparendo.getFechaImposicion());
        BigDecimal smmlv = new BigDecimal(smmlvParametro.getValor());
        comparendo.setValorNominal(smmlv.multiply(infraccion.getValor()));

        comparendo.setNumero(numeroComparendo);
        comparendo.setId(UUID.randomUUID());
        comparendoRepo.save(comparendo);
        LOG.debug("Comparendo almacenado exitosamente: " + comparendo.getNumero().getValor());
        numeroComparendo.setEstado(EstadoNumeroComparendo.IMPUESTO);
        numeroComparendoRepo.save(numeroComparendo);
        LOG.debug("Estado de numero de Comparendo almacenado exitosamente a impuesto: " + numeroComparendo.getValor());
        return comparendo;
    }


    /**
     * Genera la orden de comparendo en PDF
     *
     * @param formatoComparendo Objeto con la data del {@link FormatoComparendo}
     */

    private byte[] generarOrdenComparendo(FormatoComparendo formatoComparendo) {
        List<FormatoComparendo> listaFormatoComparendos = new ArrayList<>();
        listaFormatoComparendos.add(formatoComparendo);
        ReportBuilder reportBuilder = new ReportBuilder();
        reportBuilder.templatePath = "classpath:reports";
        Map<String, Object> parametros = new HashMap<>();
        //Genera el PDF de acuerdo a la data y parametros enviados
        byte[] bytes = reportBuilder.pdf(
                "orden-comparendo",
                listaFormatoComparendos,
                parametros
        );
        return bytes;
    }


}