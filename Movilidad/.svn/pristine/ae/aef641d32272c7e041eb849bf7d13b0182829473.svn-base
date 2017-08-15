package co.gov.movilidadbogota.sipa.serv.bpm;

import co.gov.movilidadbogota.sipa.data.biz.comp.AudienciaComparendo;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.serv.api.AudienciaException;
import co.gov.movilidadbogota.sipa.serv.api.AudienciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Adaptador HTTP REST del servicio {@link co.gov.movilidadbogota.sipa.serv.api.AudienciaService}
 */
@RestController
public class AudienciaServiceController {

    public static final String PATH = "/v" + Constants.API_VERSION + "/audiencia";

    @Autowired
    private AudienciaService audienciaService;

    public ResponseEntity aperturaAudiencia(
            AudienciaComparendo audienciaComparendo
    ) {
        try {
            audienciaService.aperturaAudiencia(audienciaComparendo);
            return ResponseEntity.ok(audienciaComparendo);
        } catch (AudienciaException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
