package co.gov.movilidadbogota.sipa.serv.comparendos;

import co.gov.movilidadbogota.sipa.commons.log.LogUtils;
import co.gov.movilidadbogota.sipa.conf.DatabaseInitialLoader;
import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.aut.UsuarioRepo;
import co.gov.movilidadbogota.sipa.data.biz.comp.*;
import co.gov.movilidadbogota.sipa.data.biz.num.EstadoNumeroComparendo;
import co.gov.movilidadbogota.sipa.data.biz.num.NumeroComparendoRepository;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.doc.Expediente;
import co.gov.movilidadbogota.sipa.data.doc.ExpedienteRepo;
import co.gov.movilidadbogota.sipa.data.doc.TipoDocumental;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.data.gen.Parametro;
import co.gov.movilidadbogota.sipa.data.gen.ValorParametro;
import co.gov.movilidadbogota.sipa.data.gen.ValorParametroRepo;
import co.gov.movilidadbogota.sipa.data.util.MensajeHelper;
import co.gov.movilidadbogota.sipa.serv.api.*;
import co.gov.movilidadbogota.sipa.serv.financiero.FinancieroDataLoader;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ComparendoServiceImpl implements ComparendoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComparendoServiceImpl.class);
    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    ReincidenciaRepo reincidenciaRepo;
    @Autowired
    private FormatoComparendoChainValidationFactory chainValidationFactory;
    @Autowired
    private NumeroComparendoRepository numeroComparendoRepository;
    @Autowired
    private ComparendoRepository comparendoRepository;
    @Autowired
    private ProcesoService procesoService;
    @Autowired
    private FormatoComparendoRepo formatoComparendoRepo;
    @Autowired
    private InfraccionRepo infraccionRepo;
    @Autowired
    private MensajeHelper mensajeHelper;
    @Autowired
    private ConstanciaEjecutoriaAutomaticaRepo constanciaEjecutoriaAutomaticaRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private ResolucionAutomaticaDeFalloRepo resolucionAutomaticaDeFalloRepo;
    @Autowired
    private DocumentoService documentoService;
    @Autowired
    private FinancieroService financieroService;
    @Autowired
    private ExpedienteRepo expedienteRepo;
    private Map<String, List<ValorParametro>> parametros;

    @Autowired
    private DatabaseInitialLoader databaseInitialLoader;

    @Autowired
    private FinancieroDataLoader financieroDataLoader;

    /**
     * Cuando se inyecta el repositorio entonces se leen todos los parámetros para agilizar la búsqueda
     * TODO: Reemplazar este mecanismo con un servicio que use apropiadamente un caché
     *
     * @param valorParametroRepo
     */
    @Autowired
    public void setValorParametroRepo(ValorParametroRepo valorParametroRepo) {
        parametros = new ConcurrentHashMap<>();
        valorParametroRepo.findAll().parallelStream()
                .forEach(x -> {
                    List<ValorParametro> l = parametros.get(x.getParametro().getClave());
                    if (l == null) {
                        l = new ArrayList<>();
                        parametros.put(x.getParametro().getClave(), l);
                    }
                    l.add(x);
                });
    }

    @Override
    public UUID imponerComparendoManual(FormatoComparendo formato, byte[] orden) throws Exception {
        return imponerComparendo(
                formato,
                TipoComparendo.MANUAL,
                new ImmutablePair<>(
                        new Documento(
                                Constants.MIME_TYPE_TIFF,
                                "imagen-orden-comparendo.tiff",
                                TipoDocumental.COMPARENDO),
                        orden
                )
        );
    }

    @Override
    public UUID imponerComparendoElectronico(FormatoComparendo formato, byte[] firmaAgente, byte[] foto1, byte[] foto2) throws Exception {
        return imponerComparendo(
                formato,
                TipoComparendo.MANUAL,
                new ImmutablePair<>(
                        new Documento(
                                Constants.MIME_TYPE_TIFF,
                                "firma-agente.png",
                                TipoDocumental.COMPARENDO),
                        firmaAgente
                )
        );
    }

    private UUID imponerComparendo(
            FormatoComparendo formato,
            TipoComparendo tipo,
            Pair<Documento, byte[]>... archivos) throws Exception {

        /*
        Se inicializa un comparendo con un identificador único para que vaya siendo
        completado a medida que se valida el formato.
         */
        Comparendo comparendo = new Comparendo(UUID.randomUUID());
        comparendo.setFechaRegistro(new Date());
        comparendo.setTipoComparendo(tipo);
        comparendo.setEstado(EstadoComparendo.IMPUESTO);
        comparendo.setFormato(formato);

        /*
        Verifica si el formato contiene errores y los almacena en el formato. También
        se asigna el estado ESTADO_INCONSISTENTE al formato
         */
        FormatoComparendoValidatorErrors errors = null;
        try {
            errors = validarFormato(formato, comparendo, tipo);
        } catch (Exception cause) {
            LogUtils.logAndRethrow(LOGGER, cause);
        }
        if (errors.hasErrors()) {
            try {
                formato.setObservaciones(objectMapper.writeValueAsString(errors.toMap()));
                formato.setEstado(FormatoComparendo.ESTADO_INCONSISTENTE);
            } catch (JsonProcessingException e) {
                throw new ComparendoException("Los errores no se pueden almacenar: " + errors.toMap());
            }
        }

        /*
        Sea como sea almacena el formato en la base de datos y las imágenes en un expediente
        cuyo identificador es el mismo que el del futuro comparendo y así evitar renombrar el
        expediente cuando se corrija el formato, por lo tanto es muy importante asignar este
        identificador ahora, así como el identificador del formato en caso que no lo tenga.
          */
        if (formato.getId() == null)
            formato.setId(UUID.randomUUID());
        formato.setComparendoId(UUID.randomUUID());
        try {
            // TODO: Usar compensación transaccional para revertir en caso de error
            formatoComparendoRepo.save(formato);
            Expediente expediente = documentoService.addExpediente(
                    new Expediente(formato.getComparendoId(), TipoDocumental.EXPEDIENTE_FORMATO_COMPARENDO));
            for (Pair<Documento, byte[]> pair : archivos) {
                documentoService.addDocumento(pair.getKey(), expediente, pair.getValue(), null);
            }
        } catch (DocumentoException e) {
            throw new ComparendoException("No se pueden almacenar el formato o los documentos asociados al formato", e);
        }

        /*
        En caso que el formato contenga errores es el momento de detener el
        procesamiento del comparendo devolviendo el identificador de comparendo.
        Con esto se acepta el formato y permanece en estado estado inconsistente.
         */
        if (errors.hasErrors()) {
            return formato.getComparendoId();
        }

        /*
        Habiendo validado la información el comparendo ya debe contener la información
        obligatoria según las reglas de validación y por tanto se procede a guardar el
        comparendo y sus dependencias en caso que sea necesario.
         */

        // Cálculo del valor de la multa
        // TODO: Manejar el caso en el que no haya salario mínimo definido para la fecha de imposición
        ValorParametro parametroSalarioMinimo = findParametro(Parametro.SMMLV, comparendo.getFechaImposicion());
        if (parametroSalarioMinimo != null) {
            BigDecimal salarioMinimo = new BigDecimal(parametroSalarioMinimo.getValor());
            comparendo.setValorNominal(salarioMinimo.multiply(comparendo.getInfraccion().getValor()));
        }

        comparendo.getNumero().setEstado(EstadoNumeroComparendo.IMPUESTO);
        numeroComparendoRepository.save(comparendo.getNumero());
        comparendo.setId(formato.getComparendoId());
        comparendoRepository.save(comparendo);

        /*
        Realiza el registro financiero de la imposición
         */
        financieroService.registrar(new EventoFinanciero(
                FinancieroService.COD_IMPOSICION_COMPARENDO,
                String.format("Imposición del comparendo %s", comparendo.getNumero().getValor()),
                comparendo.getFechaImposicion(),
                FinancieroService.REF_COMPARENDO,
                comparendo.getId(),
                comparendo.getValorNominal()
        ));

        /*
        Inicia el proceso contravencional correspondiente a este comparendo
         */
        procesoService.start("contravencional", new CommandContext()
                .put("idComparendo", comparendo.getId().toString()));

        return comparendo.getId();
    }

    /**
     * Encuentra el valor vigente a la fecha de un parámetro
     *
     * @param clave
     * @param fecha
     * @return
     */
    private ValorParametro findParametro(String clave, Date fecha) {
        List<ValorParametro> l = parametros.get(clave);
        if (l == null)
            return null;
        return l.parallelStream()
                .filter(x -> x.getInicioVigencia().before(fecha) && (x.getFinVigencia() == null || x.getFinVigencia().after(fecha)))
                .findAny().orElse(null);
    }


    private FormatoComparendoValidatorErrors validarFormato(
            FormatoComparendo formato,
            Comparendo comparendo,
            TipoComparendo tipo
    ) throws Exception {
        /*
        Valida unos datos mínimos que permitan realizar una revisión más completa
        del formato. Los datos mínimos correctos deben ser la fecha de la imposición,
        el código de la infracción y el tipo de comparendo. Sin estos datos no es
        posible construir una cadena de validación y por tanto el comparendo se
        rechaza con una excepción.
         */
        if (tipo == null) {
            throw new ComparendoException("El tipo de comparendo es obligatorio");
        }
        comparendo.setTipoComparendo(tipo);

        Date fechaImposicion;
        try {
            fechaImposicion = Constants.DATE_FORMAT.parse(formato.getFecha());
        } catch (ParseException e) {
            throw new ComparendoException(
                    "La fecha de imposición del comparendo no es válida: " + formato.getFecha(), e);
        }
        comparendo.setFechaImposicion(fechaImposicion);

        String codigoInfraccion = formato.getCodigoInfraccion();
        if (StringUtils.isBlank(codigoInfraccion)) {
            throw new ComparendoException("El código de la infracción es obligatorio");
        }
        /*
        Obtiene la configuración de la infracción, vigente a la fecha de la imposición. De
        esta configuración se usará el campo "validador" para armar la cadena de validación
        obteniendo del contexto de aplicación los beans de validación.
        TODO: Usar el tipo de comparendo para obtener la configuración de validación
        */
        Infraccion infraccion = infraccionRepo.findByCodigoAndInicioVigenciaBeforeAndFinVigenciaAfter(
                codigoInfraccion, fechaImposicion, fechaImposicion
        );
        if (infraccion == null) {
            /*
            Si la infracción no existe para la fecha correspondiente entonces crea un mensaje con destinatario
            Usuario.ROOT y lanza una excepción para que no se reciba el formato por el momento ya que no hay
            manera de determinar si se trata de una inconsistencia o simplemente es información "basura"
            */
            String msg = String.format(
                    "No hay datos vigentes a fecha de %s para la infracción %s ",
                    fechaImposicion, codigoInfraccion
            );
            mensajeHelper.enviarMensaje(Usuario.SISTEMA, msg, TipoNotificacion.PANTALLA, TipoNotificacion.EMAIL);
            throw new Exception(msg);
        }

        if (StringUtils.isBlank(infraccion.getValidador())) {
            /*
            Si la infracción no tiene ninguna validación entonces crea un mensaje con destinatario
            Usuario.ROOT y lanza una excepción para que no se reciba el formato por el momento ya que no hay
            manera de determinar si se trata de una inconsistencia o simplemente es información "basura"
            */
            String msg = String.format(
                    "No hay validaciones para la infracción: %s - %s ",
                    codigoInfraccion, infraccion.getId()
            );
            mensajeHelper.enviarMensaje(Usuario.SISTEMA, msg, TipoNotificacion.PANTALLA, TipoNotificacion.EMAIL);
            throw new Exception(msg);
        }
        //TODO aqui va el calculo de la i
        comparendo.setInfraccion(infraccion);

        /*
        Obtiene la cadena de validación que corresponde al tipo de comparendo,
        la infracción y la fecha de imposición. En caso que no se encuentre una
        cadena de validación entonces se rechaza el formato. Posteriormente la
        cadena se recorre la cadena aplicando cada elemento al formato, acumulando
        los errores en un mapa.
         */
        List<FormatoComparendoValidator> chain;
        try {
            chain = chainValidationFactory.get(infraccion);
        } catch (Exception e) {
            throw new ComparendoException("No se puede determinar la cadena de validaciones", e);
        }
        FormatoComparendoValidatorErrors errorsAccumulator = new FormatoComparendoValidatorErrors();
        chain.stream()
                .map(x -> x.validate(formato, comparendo, errorsAccumulator))
                .reduce((x, y) -> x && y);
        return errorsAccumulator;
    }

    @Override
    public CommandContext generarResolucionAutomaticaFallo(UUID comparendoId, CommandContext commandContext) throws ComparendoException {
        Comparendo comparendo = comparendoRepository.findOne(comparendoId);
        //Se obtiene el comparendo
        if (comparendo == null) {
            String msg = "No se encontró el comparendo " + comparendoId.toString();
            LOGGER.error(msg);
            throw new ComparendoException(msg);
        }
        //Se verifica si ya existe un resolución automática para el comparendo
        ResolucionAutomaticaDeFallo oldRaf = resolucionAutomaticaDeFalloRepo.findByComparendoId(comparendoId);
        if (oldRaf != null) {
            String msg = "Ya se encuentra generada una resolución automática de fallo para el comparendo " + comparendoId.toString();
            LOGGER.error(msg);
            throw new ComparendoException(msg);
        }
        //Se obtiene el documento
        String idDocumento = commandContext.get(CTX_ID_DOCUMENTO_RESOLUCION_AUTOMATICA_FALLO).toString();
        Documento resolucion = null;
        try {
            resolucion = documentoService.getDocumento(UUID.fromString(idDocumento));
        } catch (DocumentoException e) {
            String msg = String.format("Error obteniendo el documento '%s'", idDocumento);
            LOGGER.warn(msg, e);
            throw new ComparendoException(msg, e);
        }
        if (resolucion == null) {
            String msg = "No se encontró el documento resolución para el comparendo " + comparendoId.toString();
            LOGGER.error(msg);
            throw new ComparendoException(msg);
        }

        //para guardar la información
        //String numeroResolucion = commandContext.get(CTX_NUMERO_RESOLUCION_AUTOMATICA_FALLO).toString();
        //TODO numero resolucion
        String numeroResolucion = "123456789";
        ResolucionAutomaticaDeFallo raf = new ResolucionAutomaticaDeFallo();
        try {
            raf.setId(UUID.randomUUID());
            raf.setComparendo(comparendo);
            raf.setNumero(numeroResolucion);
            raf.setFechaExpedicion(resolucion.getFechaCreacion());
            //TODO cambiar por la autoridad
            raf.setAutoridad(usuarioRepo.findOneByUsername("sistema"));
            raf.setDocumento(resolucion);
            resolucionAutomaticaDeFalloRepo.save(raf);
            comparendo.setEstado(EstadoComparendo.CON_FALLO);
            comparendoRepository.save(comparendo);

        } catch (Exception cause) {
            String msg = String.format(
                    "Ocurrió un error guardando la resolución automática de fallo para el comparendo: '%s'",
                    comparendoId.toString());
            LOGGER.error(msg);
            throw new ComparendoException(msg, cause);
        }

        commandContext.put(CTX_RESOLUCION_AUTOMATICA_FALLO, raf.getId().toString());
        return commandContext;
    }

    @Override
    public CommandContext generarConstanciaEjecutoriaAutomatica(UUID comparendoId, CommandContext commandContext) throws ComparendoException {
        //Se obtiene el comparendo
        Comparendo comparendo = comparendoRepository.findOne(comparendoId);
        if (comparendo == null) {
            String msg = "No se encontró el comparendo " + comparendoId.toString();
            LOGGER.error(msg);
            throw new ComparendoException(msg);
        }

        //Se verifica si ya existe una constancia para ese comparende
        ConstanciaEjecutoriaAutomatica oldCea = constanciaEjecutoriaAutomaticaRepo.findByComparendoId(comparendoId);
        if (oldCea != null) {
            String msg = "Ya se encuentra generada una constancia ejecutoria automática para el comparendo " + comparendoId.toString();
            LOGGER.error(msg);
            throw new ComparendoException(msg);
        }

        //Se obtiene el documento
        String idDocumento = commandContext.get(CTX_ID_DOCUMENTO_CONSTANCIA_EJECUTORIA_AUTOMATICA).toString();
        Documento constanciaEjecutoria = null;
        try {
            constanciaEjecutoria = documentoService.getDocumento(UUID.fromString(idDocumento));
        } catch (DocumentoException e) {
            String msg = String.format("Error obteniendo el documento '%s'", idDocumento);
            LOGGER.warn(msg, e);
            throw new ComparendoException(msg, e);
        }
        if (constanciaEjecutoria == null) {
            String msg = "No se encontró el documento constancia ejecutoria automática para el comparendo " + comparendoId.toString();
            LOGGER.error(msg);
            throw new ComparendoException(msg);
        }

        //para guardar la información
        ConstanciaEjecutoriaAutomatica cea = new ConstanciaEjecutoriaAutomatica();
        try {
            cea.setId(UUID.randomUUID());
            cea.setComparendo(comparendo);
            //TODO definir numero de resolucion
            cea.setNumero("26549872");
            cea.setFechaExpedicion(constanciaEjecutoria.getFechaCreacion());
            //TODO cambiar por la autoridad
            cea.setAutoridad(usuarioRepo.findOneByUsername("sistema"));
            cea.setDocumento(constanciaEjecutoria);
            constanciaEjecutoriaAutomaticaRepo.save(cea);
            comparendo.setEstado(EstadoComparendo.EN_FIRME);
            comparendoRepository.save(comparendo);
            //Se realiza el cambio de cuenta a cuenta de orden
            financieroService.registrar(new EventoFinanciero(
                    FinancieroService.COD_CONSTANCIA_EJECUTORIA_COMPARENDO,
                    "Constancia ejecutoria del comparendo: " + comparendoId,
                    constanciaEjecutoria.getFechaCreacion(),
                    FinancieroService.REF_COMPARENDO,
                    comparendoId,
                    comparendo.getValorNominal()
            ));
        } catch (Exception cause) {
            String msg = String.format(
                    "Ocurrió un error guardando la constancia ejecutoria autom¿atica para el comparendo: '%s'",
                    comparendoId.toString());
            LOGGER.error(msg);
            throw new ComparendoException(msg, cause);
        }

        commandContext.put(CTX_CONSTANCIA_EJECUTORIA_AUTOMATICA, cea.getId().toString());
        return commandContext;
    }

    @Override
    public CommandContext generarResolucionReincidencia(UUID comparendoId, CommandContext commandContext) throws ComparendoException {
        //Se obtiene el comparendo
        Comparendo comparendo = comparendoRepository.findOne(comparendoId);
        if (comparendo == null)
            throw new ComparendoException("No se encontró el comparendo");

        //Se obtiene el documento
        String idDocumento = commandContext.get(CTX_ID_DOCUMENTO_RESOLUCION_REINCIDENCIA).toString();
        //Se obtiene la constancia
        String idDocumentoConstancia = commandContext.get(CTX_ID_DOCUMENTO_CONSTANCIA_EJECUTORIA_AUTOMATICA).toString();
        Documento resolucionReincidencia = null;
        Documento constanciaEjecutoria = null;
        try {
            resolucionReincidencia = documentoService.getDocumento(UUID.fromString(idDocumento));
            constanciaEjecutoria = documentoService.getDocumento(UUID.fromString(idDocumentoConstancia));
        } catch (DocumentoException e) {
            String msg = String.format("Error obteniendo el documento '%s'", idDocumento);
            LOGGER.warn(msg, e);
            throw new ComparendoException(msg, e);
        }
        if (resolucionReincidencia == null)
            throw new ComparendoException("No se encontró el documento resolución");

        //para guardar la información
        Reincidencia re = new Reincidencia();
        re.setId(UUID.randomUUID());
        re.setReincidente(comparendo.getInfractor());
        re.setNotificado(true);
        re.setFecha(resolucionReincidencia.getFechaCreacion());
        re.setFallo(constanciaEjecutoria);
        re.setExpediente(expedienteRepo.findOne(comparendoId));
        reincidenciaRepo.save(re);

        return commandContext;
    }

    @Override
    public CommandContext identificarReincidencia(UUID comparendoId, CommandContext context) throws ComparendoException {
        Comparendo c = comparendoRepository.findOne(comparendoId);

        Calendar today = Calendar.getInstance();
        Calendar sixMonthsBeforeToday = Calendar.getInstance();
        sixMonthsBeforeToday.add(Calendar.MONTH, -6);
        Integer numResoluciones = constanciaEjecutoriaAutomaticaRepo.findByInfractorIdAndFechaImposicionBetween(c.getInfractor().getId(), sixMonthsBeforeToday.getTime(), today.getTime());

        Boolean reincidente = false;
        if (numResoluciones > 1)
            reincidente = true;

        context.put(CTX_REINCIDENTE, reincidente);

        return context;
    }
}
