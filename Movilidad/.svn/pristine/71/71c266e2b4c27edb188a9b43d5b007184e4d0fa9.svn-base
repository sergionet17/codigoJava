package co.gov.movilidadbogota.sipa.serv.doc;

import co.gov.movilidadbogota.sipa.data.doc.Actuacion;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.doc.Expediente;
import co.gov.movilidadbogota.sipa.serv.api.CommandContext;
import co.gov.movilidadbogota.sipa.serv.api.DocumentoException;
import co.gov.movilidadbogota.sipa.serv.api.DocumentoService;
import co.gov.movilidadbogota.sipa.serv.api.DocumentoServiceRestNaming;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

/**
 * Adaptador REST para la interfaz {@link DocumentoService}
 */
@RestController
public class DocumentoServiceController {

    private DocumentoService documentoService;

    public DocumentoServiceController(DocumentoService documentoService) {
        this.documentoService = documentoService;
    }

    @PostMapping(DocumentoServiceRestNaming.PATH_ADD_EXPEDIENTE)
    public ResponseEntity addExpediente(
            @RequestBody Expediente expediente) {
        try {
            return ResponseEntity.ok(documentoService.addExpediente(expediente));
        } catch (DocumentoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(DocumentoServiceRestNaming.PATH_ADD_DOCUMENTO)
    public ResponseEntity addDocumento(
            @RequestPart(DocumentoServiceRestNaming.VAR_DOCUMENTO) Documento documento,
            @RequestPart(name = DocumentoServiceRestNaming.VAR_EXPEDIENTE, required = false) Expediente expediente,
            @RequestPart(name = DocumentoServiceRestNaming.VAR_ARCHIVO, required = false) MultipartFile archivo,
            @RequestPart(name = DocumentoServiceRestNaming.VAR_ACTUACION, required = false) Actuacion actuacion
    ) {
        // Verifica los parámetros
        String msg = null;
        if (documento == null || documento.getId() == null)
            msg = "El documento o el identificador del documento no puede ser nulo";
        if (StringUtils.isNotBlank(msg))
            return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);

        try {
            if (expediente != null && actuacion != null
                    && archivo != null && !archivo.isEmpty()) {
                return ResponseEntity.ok(documentoService.addDocumento(documento, expediente, archivo.getBytes(), actuacion));
            } else if (expediente != null && actuacion == null
                    && archivo != null && !archivo.isEmpty()) {
                return ResponseEntity.ok(documentoService.addDocumento(documento, expediente, archivo.getBytes()));
            } else if (expediente == null) {
                if (archivo != null && !archivo.isEmpty())
                    return ResponseEntity.ok(documentoService.addDocumento(documento, archivo.getBytes()));
                else {
                    msg = "El archivo no puede ser nulo o vacío";
                    return new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
                }
            } else {
                throw new RuntimeException("Revisar los parámetros enviados para guardar el documento");
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(DocumentoServiceRestNaming.PATH_GET_DOCUMENTO)
    public ResponseEntity getDocumento(@PathVariable("id") UUID id) {
        try {
            return ResponseEntity.ok(documentoService.getDocumento(id));
        } catch (DocumentoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(DocumentoServiceRestNaming.PATH_GET_CONTENIDO_DOCUMENTO)
    public ResponseEntity getContenidoDocumento(@PathVariable("id") UUID id) {
        try {
            return ResponseEntity.ok(documentoService.getContenidoDocumento(id));
        } catch (DocumentoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(DocumentoServiceRestNaming.PATH_GET_EXPEDIENTE)
    public ResponseEntity getExpediente(@PathVariable("id") UUID id) {
        try {
            return ResponseEntity.ok(documentoService.getExpediente(id));
        } catch (DocumentoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(DocumentoServiceRestNaming.PATH_GET_DOCUMENTOS_EXPEDIENTE)
    public ResponseEntity getDocumentosExpediente(@PathVariable("id") UUID id) {
        try {
            return ResponseEntity.ok(documentoService.getDocumentosExpediente(id));
        } catch (DocumentoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(DocumentoServiceRestNaming.PATH_GET_DOCUMENTOS)
    public ResponseEntity getDocumentos(@RequestBody List<UUID> ids) {
        try {
            return ResponseEntity.ok(documentoService.getDocumentos(ids));
        } catch (DocumentoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(DocumentoServiceRestNaming.PATH_PLANTILLA)
    public ResponseEntity generarDocumento(
            @PathVariable(DocumentoServiceRestNaming.VAR_NOMBRE_PLANTILLA) String nombre,
            @RequestBody CommandContext commandContext
    ) {
        try {
            System.out.println("Inicia proceso de generar documento ");
            return ResponseEntity.ok(documentoService.generarDocumento(nombre, commandContext));
        } catch (DocumentoException e) {
            System.out.println("Error documento: " + e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(DocumentoServiceRestNaming.PATH_ACTUALIZAR_DOCUMENTO)
    public ResponseEntity actualizarDocumento(
            @RequestPart(DocumentoServiceRestNaming.VAR_DOCUMENTO) Documento documento,
            @RequestPart(name = DocumentoServiceRestNaming.VAR_ARCHIVO, required = false) MultipartFile archivo) {
        try {
            documentoService.actualizarDocumento(documento, archivo.getBytes());
            return ResponseEntity.ok().body("");
        } catch (DocumentoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(DocumentoServiceRestNaming.PATH_ASOCIAR_DOCUMENTO)
    public ResponseEntity actualizarDocumento(
            @RequestPart(DocumentoServiceRestNaming.VAR_DOCUMENTO) Documento documento,
            @RequestPart(DocumentoServiceRestNaming.VAR_EXPEDIENTE) Expediente expediente,
            @RequestPart(DocumentoServiceRestNaming.VAR_ACTUACION) Actuacion actuacion) {
        try {
            documentoService.asociarDocumento(documento, expediente, actuacion);
            return ResponseEntity.ok().body("");
        } catch (DocumentoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(DocumentoServiceRestNaming.PATH_GET_SHAREPOINT_URL)
    public ResponseEntity getSharepointPath(@PathVariable("id") UUID id) {
        try {
            return ResponseEntity.ok(documentoService.getSharepointUrl(id));
        } catch (DocumentoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
