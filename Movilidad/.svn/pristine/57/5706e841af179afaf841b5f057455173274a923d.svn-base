package co.gov.movilidadbogota.sipa.serv.bpm;

import co.gov.movilidadbogota.sipa.serv.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Adaptador HTTP REST para el servicio {@link co.gov.movilidadbogota.sipa.serv.api.ProcesoService}
 */
@RestController
public class ProcesoServiceController {

    @Autowired
    private ProcesoService procesoService;

    @PostMapping(ProcesoServiceRestNaming.PATH_DELIVER_MESSAGE)
    public ResponseEntity deliverMessage(@RequestBody ProcMessageRequest request) {
        try {
            procesoService.deliverMessage(
                    request.getMessageName(),
                    request.getProcessVariables(),
                    request.getBusinessKey(),
                    request.getCorrelationKeys()
            );
            return ResponseEntity.ok().build();
        } catch (ProcesoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
