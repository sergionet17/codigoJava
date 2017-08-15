package co.gov.movilidadbogota.sipa.cli;

import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.serv.api.ClientException;
import co.gov.movilidadbogota.sipa.serv.api.DocumentoException;
import co.gov.movilidadbogota.sipa.serv.api.VolantePagoService;
import co.gov.movilidadbogota.sipa.serv.api.VolantePagoServiceRestNaming;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Implementación del servicio financiero para invocar las operaciones de manera
 * remota por HTTP REST
 */
public class VolantePagoServiceClient extends GenericRemoteClient implements VolantePagoService {

    public VolantePagoServiceClient(RestTemplate restTemplate, ServiceLocationProperties serviceLocationProperties) {
        super(serviceLocationProperties, restTemplate);
    }

    public Documento generarVolantePago(UUID comparendoId) throws Exception {
        // Construye el request compuesto por todas las partes

        try {

        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(map, headers);

        String url = buildUrlFor(VolantePagoServiceRestNaming.PATH_GENERAR_VOLANTE_PAGO);
        Map<String, UUID> parametros = new HashMap<String, UUID>();
        parametros.put(VolantePagoServiceRestNaming.VAR_COMPARENDO_ID, comparendoId);

        ResponseEntity<Documento> response = restTemplate.postForEntity(
                url, request, Documento.class, parametros);
        return resolveResponse(url, response);

        //String url = buildUrlFor(VolantePagoServiceRestNaming.PATH_GENERAR_VOLANTE_PAGO);
       /* HttpEntity<MultiValueMap<String, Object>> request = super.createMultipartEntity(ImmutableMap.<String, HttpEntity<?>>builder()
                .put(VolantePagoServiceRestNaming.VAR_COMPARENDO_ID, createJsonEntity(comparendoId.toString()))
                                .build()
        );*/

        //ResponseEntity<Documento> response = restTemplate.postForEntity(url, comparendoId.toString(), Documento.class);


            //return resolveResponse(url, response);
        } catch (ClientException e) {
            throw new DocumentoException(e);
        }
    }
}
