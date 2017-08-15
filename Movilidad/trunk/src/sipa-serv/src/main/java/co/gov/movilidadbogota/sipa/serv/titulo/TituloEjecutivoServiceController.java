package co.gov.movilidadbogota.sipa.serv.titulo;

import co.gov.movilidadbogota.sipa.serv.api.CommandContext;
import co.gov.movilidadbogota.sipa.serv.api.TituloEjecutivoException;
import co.gov.movilidadbogota.sipa.serv.api.TituloEjecutivoService;
import co.gov.movilidadbogota.sipa.serv.api.TituloEjecutivoServiceRestNaming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Clase que funciona como adaptador para el servicio {@link TituloEjecutivoService}.
 * Su función es recibir llamados a las operaciones mediante HTTP REST e invocar
 * el correspondiente método del servicio.
 */
@RestController
public class TituloEjecutivoServiceController {

    @Autowired
    TituloEjecutivoService TituloEjecutivoService;


    @PostMapping(TituloEjecutivoServiceRestNaming.PATH_REGISTRAR)
    public ResponseEntity registrarTituloEjecutivo(
            @PathVariable(TituloEjecutivoServiceRestNaming.VAR_COMPARENDO_ID) UUID comparendoId,
            @RequestBody CommandContext commandContext
    ) {
        try {
            return ResponseEntity.ok(TituloEjecutivoService.registrarTituloEjecutivo(
                    comparendoId, commandContext
            ));
        } catch (TituloEjecutivoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(TituloEjecutivoServiceRestNaming.PATH_INICIAR_COBRO_PERSUASIVO)
    public ResponseEntity iniciarProcesoCobroPersuasivo(
            @PathVariable(TituloEjecutivoServiceRestNaming.VAR_TITULO_EJECUTIVO_ID) UUID tituloEjecutivoId,
            @RequestBody CommandContext commandContext
    ) {
        try {
            return ResponseEntity.ok(TituloEjecutivoService.iniciarProcesoCobroPersuasivo(
                    tituloEjecutivoId, commandContext
            ));
        } catch (TituloEjecutivoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
