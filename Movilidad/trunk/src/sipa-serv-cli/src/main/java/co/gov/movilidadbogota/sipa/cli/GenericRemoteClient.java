package co.gov.movilidadbogota.sipa.cli;

import co.gov.movilidadbogota.sipa.serv.api.ClientException;
import co.gov.movilidadbogota.sipa.serv.api.DocumentoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by acpreda on 7/6/17.
 */
public class GenericRemoteClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenericRemoteClient.class);

    private final ServiceLocationProperties serviceLocationProperties;

    protected RestTemplate restTemplate;

    public GenericRemoteClient(ServiceLocationProperties serviceLocationProperties, RestTemplate restTemplate) {
        this.serviceLocationProperties = serviceLocationProperties;
        this.restTemplate = restTemplate;
    }

    /**
     * Construye la URL de la operación
     *
     * @param path La ruta relativa al servicio
     * @return La URL completa de la operación
     */
    protected String buildUrlFor(String path) {
        String preffix = serviceLocationProperties.getBaseUrl();
        if (preffix.endsWith("/")) {
            preffix = preffix.substring(0, preffix.length() - 1);
        }
        return String.format(
                "%s%s",
                preffix,
                path
        );
    }

    protected <T> HttpEntity<T> createJsonEntity(T body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<T> entity = new HttpEntity<T>(body, headers);
        return entity;
    }


    protected <RESPONSE_TYPE, BODY_TYPE> RESPONSE_TYPE postForObject(String url, BODY_TYPE body, Class<RESPONSE_TYPE> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT, MimeTypeUtils.APPLICATION_JSON_VALUE);
        relayAuthorizationHeader(headers);
        HttpEntity<BODY_TYPE> request = new HttpEntity<BODY_TYPE>(body, headers);
        RESPONSE_TYPE response = restTemplate.postForObject(url, request, clazz);
        return response;
    }

    protected <RESPONSE_TYPE> RESPONSE_TYPE getForObject(String url, Class<RESPONSE_TYPE> clazz) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.ACCEPT, MimeTypeUtils.APPLICATION_JSON_VALUE);
        relayAuthorizationHeader(headers);
        RESPONSE_TYPE response = restTemplate.getForObject(url, clazz);
        return response;
    }

    private void relayAuthorizationHeader(HttpHeaders headers) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            LOGGER.warn("Authentication object is null");
        } else {
            if (!(authentication instanceof OAuth2Authentication)) {
                LOGGER.warn("Authentication object {} is not an instance of class {}",
                        authentication,
                        OAuth2Authentication.class);
            } else {
                OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) authentication;
                Object details = oAuth2Authentication.getDetails();
                if (details == null) {
                    LOGGER.warn("Authentication details is null");
                } else {
                    if (!(details instanceof OAuth2AuthenticationDetails)) {
                        LOGGER.warn("Authentication details object {} is not an instance of class {}",
                                details,
                                OAuth2AuthenticationDetails.class);
                    } else {
                        OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) details;
                        String token = String.format("%s %s",
                                oAuth2AuthenticationDetails.getTokenType(),
                                oAuth2AuthenticationDetails.getTokenValue());
                        headers.add(HttpHeaders.AUTHORIZATION, token);
                    }
                }
            }
        }
    }

    /**
     * Resuelve la respuesta devuelta por el recurso remoto
     *
     * @param url      la dirección del recurso solicitado con el que se contruyen los
     *                 mensajes de error de las excepciones en caso que se presenten.
     * @param response El objeto respuesta que devolvió el recurso remoto
     * @param <T>      El tipo de respuesta esperado
     * @return El valor de la respuesta en caso que el estado de la respuesta
     * haya sido 2xx
     * @throws DocumentoException       cuando el estado de la respuesta es 5xx
     * @throws IllegalArgumentException cuando el estado de la respuesta es 4xx
     * @throws RuntimeException         cuando el estado de la respuesta no se espera (!2xx !4xx !5xx)
     */
    protected <T> T resolveResponse(String url, ResponseEntity<T> response)
            throws ClientException {
        if (response.getStatusCode().value() >= 200 && response.getStatusCode().value() < 300) {
            return response.getBody();
        } else if (response.getStatusCode().value() >= 500 && response.getStatusCode().value() < 600) {
            throw new ClientException(String.format(
                    "Error en el servidor para el recurso %s; %s - %s",
                    url,
                    response.getStatusCode().value(),
                    response.hasBody() ? response.getBody().toString() : response.getStatusCode().getReasonPhrase()
            ));
        } else if (response.getStatusCode().value() >= 400 && response.getStatusCode().value() < 500) {
            throw new IllegalArgumentException(String.format(
                    "Parámetros no válidos para el recurso %s; %s - %s",
                    url,
                    response.getStatusCode().value(),
                    response.hasBody() ? response.getBody().toString() : response.getStatusCode().getReasonPhrase()
            ));
        } else {
            throw new RuntimeException(String.format(
                    "Respuesta no esperada %s; %s - %s",
                    url,
                    response.getStatusCode().value(),
                    response.hasBody() ? response.getBody().toString() : response.getStatusCode().getReasonPhrase()
            ));
        }
    }


    protected HttpEntity<ByteArrayResource> createFileEntity(String contentType, String name, String filename, byte[] content) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(contentType));
        headers.add(
                "Content-Disposition",
                "form-data; name=\"" + name + "\"; filename=\"" + filename + "\"");
        HttpEntity<ByteArrayResource> entity = new HttpEntity<ByteArrayResource>(
                new ByteArrayResource(content),
                headers
        );
        return entity;
    }

    public HttpEntity<MultiValueMap<String, Object>> createMultipartEntity(Map<String, HttpEntity<?>> entities) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        entities.entrySet().stream()
                .filter(x -> x != null)
                .forEach(x -> map.add(x.getKey(), x.getValue()));
        return new HttpEntity<>(map, headers);
    }
}
