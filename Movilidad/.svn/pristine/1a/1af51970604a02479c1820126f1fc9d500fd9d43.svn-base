package co.gov.movilidadbogota.sipa.serv.comparendos;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.doc.PlantillaRepo;
import co.gov.movilidadbogota.sipa.serv.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * Clase que funciona como adaptador para el servicio {@link ComparendoService}.
 * Su función es recibir llamados a las operaciones mediante HTTP REST e invocar
 * el correspondiente método del servicio.
 */
@RestController
public class ComparendoServiceController {

    @Autowired
    ComparendoService comparendoService;
    @Autowired
    PlantillaRepo plantillaRepo;
    @Autowired
    DocumentoService documentoService;

    @PostMapping(ComparendoServiceRestNaming.PATH_IMPONER_COMPARENDO_MANUAL)
    public ResponseEntity<?> imponerComparendoManual(
            @RequestPart(ComparendoServiceRestNaming.VAR_FORMATO) FormatoComparendo formatoComparendo,
            @RequestPart(ComparendoServiceRestNaming.VAR_ORDEN) MultipartFile orden
    ) {
        try {
            return ResponseEntity.ok(comparendoService.imponerComparendoManual(
                    formatoComparendo,
                    orden.getBytes()
            ));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(ComparendoServiceRestNaming.PATH_IMPONER_COMPARENDO_ELECTRONICO)
    public ResponseEntity<?> imponerComparendoElectronico(
            @RequestPart(ComparendoServiceRestNaming.VAR_FORMATO) FormatoComparendo formatoComparendo,
            @RequestPart(ComparendoServiceRestNaming.VAR_FIRMA_AGENTE) MultipartFile firmaAgente,
            @RequestPart(ComparendoServiceRestNaming.VAR_FOTO1) MultipartFile foto1,
            @RequestPart(ComparendoServiceRestNaming.VAR_FOTO2) MultipartFile foto2
    ) {
        try {
            return ResponseEntity.ok(comparendoService.imponerComparendoElectronico(
                    formatoComparendo,
                    firmaAgente.getBytes(),
                    foto1.getBytes(),
                    foto2.getBytes()
            ));
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(ComparendoServiceRestNaming.PATH_GENERAR_RESOLUCION_AUTOMATICA_FALLO)
    public ResponseEntity generarResolucionAutomaticaFallo(
            @PathVariable(ComparendoServiceRestNaming.VAR_COMPARENDO_ID) UUID comparendoId,
            @RequestBody CommandContext commandContext
    ) {
        try {
            return ResponseEntity.ok(comparendoService.generarResolucionAutomaticaFallo(
                    comparendoId, commandContext
            ));
        } catch (ComparendoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(ComparendoServiceRestNaming.PATH_GENERAR_CONSTANCIA_EJECUTORIA_AUTOMATICA)
    public ResponseEntity generarConstanciaEjecutoriaAutomatica(
            @PathVariable(ComparendoServiceRestNaming.VAR_COMPARENDO_ID) UUID comparendoId,
            @RequestBody CommandContext commandContext
    ) {
        try {
            return ResponseEntity.ok(comparendoService.generarConstanciaEjecutoriaAutomatica(
                    comparendoId, commandContext
            ));
        } catch (ComparendoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(ComparendoServiceRestNaming.PATH_GENERAR_RESOLUCION_REINCIDENCIA)
    public ResponseEntity generarResolucionReincidencia(
            @PathVariable(ComparendoServiceRestNaming.VAR_COMPARENDO_ID) UUID comparendoId,
            @RequestBody CommandContext commandContext
    ) {
        try {
            return ResponseEntity.ok(comparendoService.generarResolucionReincidencia(
                    comparendoId, commandContext
            ));
        } catch (ComparendoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(ComparendoServiceRestNaming.PATH_IDENTIFICAR_REINCIDENCIA)
    public ResponseEntity identificarReincidencia(
            @PathVariable(ComparendoServiceRestNaming.VAR_COMPARENDO_ID) UUID comparendoId,
            @RequestBody CommandContext commandContext
    ) {
        try {
            return ResponseEntity.ok(comparendoService.identificarReincidencia(
                    comparendoId, commandContext
            ));
        } catch (ComparendoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
