package co.gov.movilidadbogota.sipa.cli;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by acpreda on 7/6/17.
 */
@Component
@ConfigurationProperties(prefix = "sipa.service.location", ignoreUnknownFields = false)
public class ServiceLocationProperties {

    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

}
