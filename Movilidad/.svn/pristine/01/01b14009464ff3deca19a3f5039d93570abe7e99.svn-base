package co.gov.movilidadbogota.sipa.serv.financiero;

import co.gov.movilidadbogota.sipa.data.fin.FinancieroException;
import co.gov.movilidadbogota.sipa.serv.api.EventoFinanciero;
import co.gov.movilidadbogota.sipa.serv.api.FinancieroService;
import co.gov.movilidadbogota.sipa.serv.api.FinancieroServiceRestNaming;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador HTTP REST para exponer el servicio financiero
 */
@RestController
public class FinancieroServiceController {

    @Autowired
    private FinancieroService financieroService;

    @PostMapping(FinancieroServiceRestNaming.PATH_REGISTRAR)
    public ResponseEntity registrar(@RequestBody EventoFinanciero eventoFinanciero) {
        try {
            return ResponseEntity.ok(financieroService.registrar(eventoFinanciero));
        } catch (FinancieroException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
