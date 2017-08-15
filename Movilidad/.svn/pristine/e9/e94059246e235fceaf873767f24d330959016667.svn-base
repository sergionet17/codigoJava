package co.gov.movilidadbogota.sipa.bpm;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class BpmClusterTimerTest {

    @Test
    public void cienProcesosCienRespuestas() {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 100; i++) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map<String, String>> entity = new HttpEntity<>(new HashMap<>(), headers);
            restTemplate.postForEntity(
                    "http://66.175.219.84/RestForest-1.0.0/process-definition/key/timer-log/start",
                    entity,
                    LinkedHashMap.class
            );
        }
    }

}
