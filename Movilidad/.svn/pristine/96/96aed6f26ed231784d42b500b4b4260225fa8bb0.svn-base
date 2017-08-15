package co.gov.movilidadbogota.sipa.cli;

import co.gov.movilidadbogota.sipa.data.doc.Actuacion;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.doc.Expediente;
import co.gov.movilidadbogota.sipa.serv.api.*;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

public class DocumentosServiceClient extends GenericRemoteClient implements DocumentoService {

    public static final Logger LOGGER = LoggerFactory.getLogger(DocumentosServiceClient.class);

    public DocumentosServiceClient(RestTemplate restTemplate, ServiceLocationProperties serviceLocationProperties) {
        super(serviceLocationProperties, restTemplate);
    }

    @Override
    public Documento addDocumento(Documento documento, byte[] contenido) throws DocumentoException {
        return addDocumento(documento, new Expediente(), contenido);
    }

    @Override
    public Documento addDocumento(Documento documento, Expediente expediente, byte[] contenido) throws DocumentoException {
        return addDocumento(documento, expediente, contenido, Actuacion.ASOCIACION);
    }

    @Override
    public Documento addDocumento(Documento documento, Expediente expediente, byte[] contenido, Actuacion actuacion) throws DocumentoException {

        HttpEntity<MultiValueMap<String, Object>> request = super.createMultipartEntity(ImmutableMap.<String, HttpEntity<?>>builder()
                .put(DocumentoServiceRestNaming.VAR_DOCUMENTO, createJsonEntity(documento))
                .put(DocumentoServiceRestNaming.VAR_EXPEDIENTE, expediente != null ? createJsonEntity(expediente) : null)
                .put(DocumentoServiceRestNaming.VAR_ACTUACION, actuacion != null ? createJsonEntity(actuacion) : null)
                .put(DocumentoServiceRestNaming.VAR_ARCHIVO, createFileEntity(documento.getContentType(), DocumentoServiceRestNaming.VAR_ARCHIVO, documento.getOriginalName(), contenido))
                .build()
        );

        String url = buildUrlFor(DocumentoServiceRestNaming.PATH_ADD_DOCUMENTO);
        try {
            return resolveResponse(url, restTemplate.postForEntity(url, request, Documento.class));
        } catch (ClientException cause) {
            String msg = "Agregando documento: " + documento.getId();
            LOGGER.error(msg, cause);
            throw new DocumentoException(msg, cause);
        }
    }

    @Override
    public void asociarDocumento(Documento documento, Expediente expediente, Actuacion actuacion) throws DocumentoException {

        HttpEntity<MultiValueMap<String, Object>> request = super.createMultipartEntity(ImmutableMap.<String, HttpEntity<?>>builder()
                .put(DocumentoServiceRestNaming.VAR_DOCUMENTO, createJsonEntity(documento))
                .put(DocumentoServiceRestNaming.VAR_EXPEDIENTE, createJsonEntity(expediente))
                .put(DocumentoServiceRestNaming.VAR_ACTUACION, createJsonEntity(actuacion))
                .build()
        );

        String url = buildUrlFor(DocumentoServiceRestNaming.PATH_ASOCIAR_DOCUMENTO);
        ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class,
                ImmutableMap.builder().put("id", documento.getId())
        );
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            String msg =String.format("Error realizando la asociación del documento: " + documento.getId() + " " + response.getStatusCode());
            LOGGER.error(msg);
            throw new DocumentoException(msg);
        }
    }

    @Override
    public void actualizarDocumento(Documento documento, byte[] contenido) throws DocumentoException {

        HttpEntity<MultiValueMap<String, Object>> request = super.createMultipartEntity(ImmutableMap.<String, HttpEntity<?>>builder()
                .put(DocumentoServiceRestNaming.VAR_DOCUMENTO, createJsonEntity(documento))
                .put(DocumentoServiceRestNaming.VAR_ARCHIVO, createFileEntity(documento.getContentType(), DocumentoServiceRestNaming.VAR_ARCHIVO, documento.getOriginalName(), contenido))
                .build()
        );

        String url = buildUrlFor(DocumentoServiceRestNaming.PATH_ACTUALIZAR_DOCUMENTO);
        ResponseEntity<Void> response = restTemplate.postForEntity(url, request, Void.class,
                ImmutableMap.builder().put("id", documento.getId())
        );
        if (!response.getStatusCode().equals(HttpStatus.OK)) {
            throw new DocumentoException("Error realizando la actualización del documento: " + documento.getId() + " " + response.getStatusCode());
        }
    }

    @Override
    public Expediente addExpediente(Expediente expediente) throws DocumentoException {

        String url = buildUrlFor(DocumentoServiceRestNaming.PATH_ADD_EXPEDIENTE);
        ResponseEntity<Expediente> response = restTemplate.postForEntity(url, createJsonEntity(expediente), Expediente.class);
        try {
            return resolveResponse(url, response);
        } catch (ClientException e) {
            throw new DocumentoException(e);
        }
    }

    @Override
    public Documento getDocumento(UUID documentoId) throws DocumentoException {
        String url = buildUrlFor(DocumentoServiceRestNaming.PATH_GET_DOCUMENTO)
                .replace("{id}", documentoId.toString());
        ResponseEntity<Documento> response = restTemplate.getForEntity(url, Documento.class);
        try {
            return resolveResponse(url, response);
        } catch (ClientException e) {
            throw new DocumentoException(e);
        }
    }

    @Override
    public byte[] getContenidoDocumento(UUID documentoId) throws DocumentoException {
        String url = buildUrlFor(DocumentoServiceRestNaming.PATH_GET_CONTENIDO_DOCUMENTO)
                .replace("{id}", documentoId.toString());
        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);
        try {
            return resolveResponse(url, response);
        } catch (ClientException e) {
            throw new DocumentoException(e);
        }
    }

    @Override
    public Expediente getExpediente(UUID expedienteId) throws DocumentoException {
        String url = buildUrlFor(DocumentoServiceRestNaming.PATH_GET_EXPEDIENTE)
                .replace("{id}", expedienteId.toString());
        ResponseEntity<Expediente> response = restTemplate.getForEntity(url, Expediente.class);
        try {
            return resolveResponse(url, response);
        } catch (ClientException e) {
            throw new DocumentoException(e);
        }
    }

    @Override
    public List<Documento> getDocumentosExpediente(UUID expedienteId) throws DocumentoException {
        String url = buildUrlFor(DocumentoServiceRestNaming.PATH_GET_DOCUMENTOS_EXPEDIENTE)
                .replace("{id}", expedienteId.toString());
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);
        //noinspection unchecked
        try {
            return resolveResponse(url, response);
        } catch (ClientException e) {
            throw new DocumentoException(e);
        }
    }

    @Override
    public List<Documento> getDocumentos(List<UUID> documentoIds) throws DocumentoException {
        String url = buildUrlFor(DocumentoServiceRestNaming.PATH_GET_DOCUMENTOS);
            ResponseEntity<List> response = restTemplate.postForEntity(url, createJsonEntity(documentoIds), List.class);
            //noinspection unchecked
            try {
                return resolveResponse(url, response);
            } catch (ClientException e) {
                throw new DocumentoException(e);
        }
    }

    @Override
    public CommandContext generarDocumento(String nombrePlantilla, CommandContext context) throws DocumentoException {
        String url = buildUrlFor(DocumentoServiceRestNaming.PATH_PLANTILLA);
        ResponseEntity<CommandContext> response = restTemplate.postForEntity(
                url,
                createJsonEntity(context),
                CommandContext.class,
                ImmutableMap.<String, String>builder().put("nombrePlantilla", nombrePlantilla).build()
        );
        try {
            return resolveResponse(url, response);
        } catch (ClientException e) {
            throw new DocumentoException(e);
        }
    }

    @Override
    public String getSharepointUrl(UUID idDocumento) throws DocumentoException {
        String url = buildUrlFor(DocumentoServiceRestNaming.PATH_GET_SHAREPOINT_URL)
                .replace("{id}", idDocumento.toString());
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        try {
            return resolveResponse(url, response);
        } catch (ClientException e) {
            throw new DocumentoException(e);
        }
    }
}
