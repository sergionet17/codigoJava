package co.gov.movilidadbogota.sipa.serv.doc;

import co.gov.movilidadbogota.sipa.conf.ApplicationProperties;
import co.gov.movilidadbogota.sipa.data.doc.*;
import co.gov.movilidadbogota.sipa.serv.api.Command;
import co.gov.movilidadbogota.sipa.serv.api.CommandContext;
import co.gov.movilidadbogota.sipa.serv.api.DocumentoException;
import co.gov.movilidadbogota.sipa.serv.api.DocumentoService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Primary
@Component
public class DocumentoServiceImpl implements DocumentoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DocumentoServiceImpl.class);

    @Autowired
    private DocumentoRepo documentoRepo;

    @Autowired
    private ExpedienteRepo expedienteRepo;

    @Autowired
    private ExpedienteDocumentoRepo expedienteDocumentoRepo;

    @Autowired
    private ExternalRepository externalRepository;

    @Autowired
    private PlantillaRepo plantillaRepo;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Override
    public Documento addDocumento(Documento documento,
                                  byte[] contenido) throws DocumentoException {
        return addDocumento(documento, new Expediente(), contenido);
    }

    @Override
    public Documento addDocumento(Documento documento,
                                  Expediente expedienteParam,
                                  byte[] contenido) throws DocumentoException {
        return addDocumento(documento, expedienteParam, contenido, Actuacion.ASOCIACION);
    }

    @Override
    public Documento addDocumento(Documento documento,
                                  Expediente expedienteParam,
                                  byte[] contenido,
                                  Actuacion actuacion) throws DocumentoException {
        /*
        En caso que se especifique el expediente entonces se verifica que exista previamente en el sistema
         */
        if (expedienteParam.getIdOrigen() != null) {
            if (getExpediente(expedienteParam.getIdOrigen()) == null) {
                String msg = "Expediente no existe: " + expedienteParam.getIdOrigen();
                LOGGER.info(msg);
                throw new IllegalArgumentException(msg);
            }
        }

        /*
        Verifica si el documento ya existe
         */
        if (getDocumento(documento.getId()) != null) {
            String msg = "El documento ya existe: " + documento.getId();
            LOGGER.info(msg);
            throw new IllegalArgumentException(msg);
        }

        /*
        Completa la información faltante o sobreescribe información sensible que se debe asignar en el servicio
         */
        documento.setFechaCreacion(new Date());
        documento.setHashContenido(DigestUtils.md5Hex(contenido));
        try {
            String externalId;
            if (expedienteParam.getIdOrigen() != null) {
                Expediente expediente = getExpediente(expedienteParam.getIdOrigen());
                externalId = externalRepository.createDocument(documento, expediente, contenido);
            } else {
                externalId = externalRepository.createDocument(documento, contenido);
            }
            documento.setExternalId(externalId);
            documentoRepo.save(documento);
        } catch (Exception cause) {
            String msg = "Error al guardar en respositorio externo el documento: " + documento.getId();
            LOGGER.info(msg, cause);
            throw new DocumentoException(msg, cause);
        }

        /*
        Asociación del documento
         */
        if (expedienteParam.getIdOrigen() != null) {
            expedienteDocumentoRepo.save(new ExpedienteDocumento(expedienteParam, documento, actuacion));
        }

        return documento;
    }

    @Override
    public void asociarDocumento(Documento documento,
                                 Expediente expedienteParam,
                                 Actuacion actuacion) throws DocumentoException {
        /*
        Verifica que tanto el expediente como el documento existan. También que la actuación sea válida
         */
        if (getDocumento(documento.getId()) == null) {
            String msg = "El documento no existe: " + documento.getId();
            LOGGER.info(msg);
            throw new IllegalArgumentException(msg);
        }
        if (getExpediente(expedienteParam.getIdOrigen()) == null) {
            String msg = "El expediente no existe: " + expedienteParam.getIdOrigen();
            LOGGER.info(msg);
            throw new IllegalArgumentException(msg);
        }
        if (Actuacion.FULL_SET.parallelStream()
                .filter(x -> x.getId().equals(actuacion.getId()))
                .findAny().orElse(null) == null) {
            String msg = "Actuación no válida: " + actuacion.getId();
            LOGGER.info(msg);
            throw new IllegalArgumentException(msg);
        }

        /*
        Si la asociación no existe entonces la crea.
         */
        if (expedienteDocumentoRepo.findByDocumentoAndExpediente(documento, expedienteParam) == null) {
            expedienteDocumentoRepo.save(new ExpedienteDocumento(expedienteParam, documento, actuacion));
            try {
                externalRepository.addDocumentToFolder(documento, getExpediente(expedienteParam.getIdOrigen()));
            } catch (Exception cause) {
                String msg = "Error asociando el documento al expediente en el repositorio externo: " + documento.getId();
                LOGGER.info(msg, cause);
                throw new IllegalArgumentException(msg, cause);
            }
        } else {
            String msg = String.format("El documento '%s' ya se encuentra asociado al expediente '%s'",
                    documento.getId(), expedienteParam.getIdOrigen());
            LOGGER.info(msg);
            throw new IllegalArgumentException(msg);
        }

    }

    @Override
    public void actualizarDocumento(Documento documento, byte[] contenido) throws DocumentoException {
        /*
        Verifica que el archivo ya exista
         */
        Documento oldDocumento = getDocumento(documento.getId());
        if (oldDocumento == null) {
            String msg = "El documento no existe: " + documento.getId();
            LOGGER.info(msg);
            throw new IllegalArgumentException(msg);
        }
        if (contenido == null || contenido.length == 0) {
            String msg = "El contenido es vacío o nulo: " + documento.getId();
            LOGGER.info(msg);
            throw new IllegalArgumentException(msg);
        }

        /*
        Si el contenido no es el mismo entonces lo actualiza en el repositorio externo
         */
        String hash = DigestUtils.md5Hex(contenido);
        if (hash.equals(documento.getHashContenido()) == false) {
            documento.setHashContenido(hash);
            try {
                externalRepository.updateDocument(documento, contenido);
            } catch (Exception cause) {
                String msg = "El contenido del documento no se puede actualizar: " + documento.getId();
                LOGGER.info(msg);
                throw new DocumentoException(msg, cause);
            }
        }

        /*
        Actualiza la información asociada al documento
         */
        oldDocumento.setTipoDocumental(documento.getTipoDocumental());
        oldDocumento.setContentType(documento.getContentType());
        documentoRepo.save(oldDocumento);

    }

    @Override
    public Expediente addExpediente(Expediente expediente) throws DocumentoException {
        try {
            String externalId = externalRepository.createFolder(expediente);
            expediente.setExternalId(externalId);
            expediente.setFechaCreacion(new Date());
            expedienteRepo.save(expediente);
            return expediente;
        } catch (Exception e) {
            throw new DocumentoException(e);
        }
    }

    @Override
    public Documento getDocumento(UUID documentoId) throws DocumentoException {
        return documentoRepo.findOne(documentoId);
    }

    @Override
    public byte[] getContenidoDocumento(UUID documentoId) throws DocumentoException {
        Documento documento = documentoRepo.findOne(documentoId);
        return documento == null ? null : externalRepository.getContenido(documento.getExternalId());
    }

    @Override
    public Expediente getExpediente(UUID expedienteId) throws DocumentoException {
        return expedienteRepo.findOne(expedienteId);
    }

    @Override
    public List<Documento> getDocumentosExpediente(UUID expedienteId) throws DocumentoException {
        return expedienteDocumentoRepo.findByExpediente(new Expediente(expedienteId));
    }

    @Override
    public List<Documento> getDocumentos(List<UUID> documentoIds) throws DocumentoException {
        return expedienteDocumentoRepo.findByDocumentoIn(
                documentoIds.stream().map(x -> new Documento(x)).collect(Collectors.toList())
        );
    }

    @Override
    public CommandContext generarDocumento(String nombrePlantilla, CommandContext context) throws DocumentoException {

        CommandContext workingContext = (CommandContext) context.clone();

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format(
                    "Invocación del método generarDocumento(nombrePlantilla=%s, context=%s)",
                    nombrePlantilla,
                    workingContext
            ));
        }

        if (StringUtils.isBlank(nombrePlantilla)) {
            LOGGER.warn("Invocación de la operación 'generarDocumento' sin el nombre de plantilla");
            throw new RuntimeException("El nombre de la plantilla es obligatorio");
        }

        /*
        Obtiene la definición de la plantilla en la cual se encuentra el documento correspondiente
        y la configuración que indica si el documento resultante debe ser sustanciado, verificado,
        firmado, etc.. Esta información debe quedar en las variables del contexto para ser devueltas
        al cliente.
         */
        Plantilla plantilla;
        try {
            plantilla = plantillaRepo.findByNombre(nombrePlantilla);
        } catch (Exception e) {
            String msg = String.format(
                    "Error al consultar el objeto plantilla a la base de datos: %s",
                    nombrePlantilla
            );
            LOGGER.error(msg, e);
            throw new DocumentoException(msg, e);
        }
        if (plantilla == null) {
            String msg = String.format("La plantilla no existe: %s", nombrePlantilla);
            LOGGER.error(msg);
            throw new DocumentoException(msg);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Plantilla encontrada para el nombre '%s': %s", nombrePlantilla, plantilla));
        }

        /*
        Obtener la implementación de la extracción de los datos con los cuales se va a realizar la
        combinación con la plantilla. A esta implementación se le suministra el contexto recibido
        desde el cliente. Es responsabilidad de la implementación validar la completitud del
        contexto, es decir que cuente con todos los parámetros que necesita. Cuando la
        implementación falla entonces se empaca la excepción y se devuelve al cliente.
         */
        Command extractor;
        try {
            extractor = applicationContext.getBean(plantilla.getBeanExtraccion() + "Extractor", Command.class);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("Extractor encontrado para la plantilla '%s' con el nombre '%s': %s",
                        nombrePlantilla, plantilla.getBeanExtraccion(), extractor));
            }
        } catch (BeansException cause) {
            String msg = String.format(
                    "El bean de extracción '%s' no se puede obtener",
                    plantilla.getBeanExtraccion()
            );
            LOGGER.error(msg, cause);
            throw new DocumentoException(msg, cause);
        }
        try {
            extractor.execute(workingContext);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(String.format("Contexto actualizado con las variables del extractor '%s': %s", nombrePlantilla, workingContext));
            }
        } catch (Exception cause) {
            String msg = String.format(
                    "El bean de extracción '%s' falló",
                    plantilla.getBeanExtraccion()
            );
            LOGGER.error(msg, cause);
            throw new DocumentoException(msg, cause);
        }

        /*
        Obtiene el motor de plantillas correspondiente al tipo de plantilla según configuración.
         */
        TemplateEngine templateEngine = null;
        try {
            templateEngine = TemplateEngineFactory.getEngineFor(plantilla.getTipoPlantilla());
        } catch (Exception cause) {
            String msg = String.format(
                    "No se puede obtener la implementación para el motor de plantillas '%s'",
                    plantilla.getTipoPlantilla() == null ? null : plantilla.getTipoPlantilla().getNombre()
            );
            LOGGER.error(msg, cause);
            throw new DocumentoException(msg, cause);
        }

        /*
        Realiza la combinación entre la plantilla y los datos del contexto
         */
        byte[] bytes;
        try {
            /*
            Obtiene la plantilla desde el respositorio con el identificador del documento
            asociado a la plantilla
             */
            if (plantilla.getDocumento() == null) {
                throw new Exception("La plantilla no tiene documento asociado");
            }
            byte[] contenidoPlantilla = getContenidoDocumento(plantilla.getDocumento().getId());
            bytes = templateEngine.merge(plantilla, contenidoPlantilla, workingContext);
        } catch (Exception cause) {
            String msg = String.format(
                    "No se puede realizar la combinación de la plantilla '%s'",
                    plantilla.getNombre()
            );
            LOGGER.error(msg, cause);
            throw new DocumentoException(msg, cause);
        }

        /*
        Intenta guardar el documento generado en el repositorio
         */
        Documento documento;
        try {
            documento = new Documento(
                    plantilla.getContentTypeProducido(),
                    plantilla.getNombreArchivo(),
                    plantilla.getTipoDocumental()
            );
            addDocumento(documento, bytes);
            String idExpediente = (String) context.get(DocumentoService.CTX_EXPEDIENTE_ID);
            if (StringUtils.isNoneBlank(idExpediente)) {
                asociarDocumento(documento, new Expediente(UUID.fromString(idExpediente)), Actuacion.ASOCIACION);
            }
        } catch (Exception cause) {
            String msg = String.format(
                    "Guardando en el repositorio el documento generado con la plantilla '%s'", plantilla.getNombre());
            LOGGER.info(msg, cause);
            throw new DocumentoException(msg, cause);
        }

        context
                .put(CTX_DOCUMENTO_SE_SUSTANCIA, plantilla.getSeSustancia())
                .put(CTX_DOCUMENTO_SE_VERIFICA, plantilla.getSeVerifica())
                .put(CTX_DOCUMENTO_SE_FIRMA, plantilla.getSeFirma())
                .put(CTX_DOCUMENTO_SE_FIRMA_MANUAL, plantilla.getSeFirmaManual());
        context.put(CTX_DOCUMENTO_ID, documento.getId().toString());
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("Contexto actualizado con variables de plantilla '%s': %s", nombrePlantilla, context));
        }
        return context;
    }

    @Override
     public String getSharepointUrl(UUID idDocumento) {
        ExpedienteDocumento expedienteDocumento = expedienteDocumentoRepo.findByDocumento(new Documento(idDocumento));
        if(expedienteDocumento == null) {
            return String.format("%s/%s",
                    applicationProperties.getSharepointPrefix(),
                    externalRepository.buildName(expedienteDocumento.getDocumento())
            );
        } else {
            return String.format("%s/%s/%s",
                    applicationProperties.getSharepointPrefix(),
                    expedienteDocumento.getExpediente().getIdOrigen(),
                    externalRepository.buildName(expedienteDocumento.getDocumento())
            );
        }
    }
}
