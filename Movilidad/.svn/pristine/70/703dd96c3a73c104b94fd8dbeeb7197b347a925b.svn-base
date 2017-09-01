package co.gov.movilidadbogota.sipa.cli;

import co.gov.movilidadbogota.sipa.data.fin.FinancieroException;
import co.gov.movilidadbogota.sipa.serv.api.ClientException;
import co.gov.movilidadbogota.sipa.serv.api.EventoFinanciero;
import co.gov.movilidadbogota.sipa.serv.api.FinancieroService;
import co.gov.movilidadbogota.sipa.serv.api.FinancieroServiceRestNaming;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * Implementación del servicio financiero para invocar las operaciones de manera
 * remota por HTTP REST
 */
public class FinancieroServiceClient extends GenericRemoteClient implements FinancieroService {

    public FinancieroServiceClient(RestTemplate restTemplate, ServiceLocationProperties serviceLocationProperties) {
        super(serviceLocationProperties, restTemplate);
    }

    public UUID registrar(EventoFinanciero evento) {
        String url = buildUrlFor(FinancieroServiceRestNaming.PATH_REGISTRAR);
        ResponseEntity<UUID> response = restTemplate.postForEntity(url, evento, UUID.class);
        try {
            return resolveResponse(url, response);
        } catch (ClientException cause) {
            throw new FinancieroException(cause);
        }
    }

}
