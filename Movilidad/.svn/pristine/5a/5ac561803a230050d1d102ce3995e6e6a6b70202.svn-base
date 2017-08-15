package co.gov.movilidadbogota.sipa.serv.comparendos;


import co.gov.movilidadbogota.sipa.serv.api.ComparendoServiceRestNaming;
import co.gov.movilidadbogota.sipa.serv.api.VolantePagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class volantePagoServiceController {

    @Autowired
    VolantePagoService volantePagoService;

    //@PostMapping(VolantePagoServiceRestNaming.PATH_GENERAR_VOLANTE_PAGO)
    public ResponseEntity generarResolucionAutomaticaFallo(
            @PathVariable(ComparendoServiceRestNaming.VAR_COMPARENDO_ID) UUID comparendoId
    ) {
        try {
            return ResponseEntity.ok(volantePagoService.generarVolantePago(
                    comparendoId
            ));
        } catch (Exception e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
