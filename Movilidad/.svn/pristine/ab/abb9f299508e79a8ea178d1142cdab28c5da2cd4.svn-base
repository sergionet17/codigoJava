package co.gov.movilidadbogota.sipa.serv.bpm;

import co.gov.movilidadbogota.sipa.serv.api.CommandContext;
import co.gov.movilidadbogota.sipa.serv.api.ProcesoException;
import co.gov.movilidadbogota.sipa.serv.api.ProcesoService;
import co.gov.movilidadbogota.sipa.serv.api.ProcessVariableValue;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementación del servicio de procesos
 */
@Service
public class ProcesoServiceImpl implements ProcesoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcesoServiceImpl.class);

    private RestTemplate restTemplate;

    private String apiUrlPreffix;

    public ProcesoServiceImpl(@Value("${app.services.bpm:http://66.175.219.84/forestxp-rest}") String apiUrlPreffix) {
        restTemplate = new RestTemplate();
        this.apiUrlPreffix = apiUrlPreffix;
        LOGGER.info("Prefijo de la API del motor de procesos: " + apiUrlPreffix);
    }

    @Override
    public void deliverMessage(
            String messageName,
            Map<String, ProcessVariableValue> processVariables,
            String businessKey,
            Map<String, ProcessVariableValue> correlationKeys
    ) throws ProcesoException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HashMap<String, Object> map = new HashMap<>();
        map.put("messageName", messageName);
        if (StringUtils.isNotBlank(businessKey))
            map.put("businessKey", businessKey);
        if (correlationKeys != null)
            map.put("correlationKeys", correlationKeys);
        if (processVariables != null)
            map.put("processVariables", processVariables);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(map, headers);
        ResponseEntity<Void> response = restTemplate.postForEntity(
                String.format("%s/message", apiUrlPreffix),
                request,
                Void.class
        );
        if (response.getStatusCode().is5xxServerError() || response.getStatusCode().is4xxClientError())
            throw new ProcesoException("Error al invocar el servicio API del servidor de procesos");
    }

    @Override
    public void deliverMessage(
            String messageName,
            Map<String, ProcessVariableValue> processVariables,
            String businessKey
    ) throws ProcesoException {
        deliverMessage(messageName, processVariables, businessKey, null);
    }

    @Override
    public void deliverMessage(
            String messageName,
            Map<String, ProcessVariableValue> processVariables
    ) throws ProcesoException {
        deliverMessage(messageName, processVariables, null);
    }

    @Override
    public void deliverMessage(String messageName) throws ProcesoException {
        deliverMessage(messageName, null);
    }

    @Override
    public CommandContext start(String key, CommandContext ctx) throws ProcesoException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HashMap<String, Map<String, ProcessVariableValue>> map = new HashMap<>();
        HashMap<String, ProcessVariableValue> variables = new HashMap<>();
        map.put("variables", variables);
        ctx.entrySet().stream().forEach(x -> variables.put(x.getKey(), new ProcessVariableValue((String) x.getValue())));

        String url = String.format("%s/process-definition/key/" + key + "/start", apiUrlPreffix);
        ResponseEntity<CommandContext> response;
        try {
            response = restTemplate.postForEntity(url, map, CommandContext.class);
        } catch (Exception cause) {
            String msg = String.format("Error al invocar el servicio API del servidor de procesos para iniciar una instancia de '%s'", key);
            LOGGER.warn(msg, cause);
            throw new ProcesoException(msg, cause);
        }

        if (response.getStatusCode().is5xxServerError() || response.getStatusCode().is4xxClientError())
            throw new ProcesoException("Error al invocar el servicio API del servidor de procesos para iniciar una instancia de '" + key + "'");

        return response.getBody();
    }
}
