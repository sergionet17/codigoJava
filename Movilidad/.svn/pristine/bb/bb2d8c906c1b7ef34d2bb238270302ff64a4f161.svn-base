package co.gov.movilidadbogota.sipa.cli;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;
import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.serv.api.*;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * Adaptador del servicio de comparendos para transporte HTTP REST
 */
public class ComparendoServiceClient extends GenericRemoteClient implements ComparendoService {

    public ComparendoServiceClient(RestTemplate restTemplate, ServiceLocationProperties serviceLocationProperties) {
        super(serviceLocationProperties, restTemplate);
    }

    public UUID imponerComparendoManual(FormatoComparendo formatoComparendo, byte[] orden) throws Exception {

        HttpEntity<MultiValueMap<String, Object>> request = super.createMultipartEntity(ImmutableMap.<String, HttpEntity<?>>builder()
                .put(ComparendoServiceRestNaming.VAR_FORMATO, createJsonEntity(formatoComparendo))
                .put(ComparendoServiceRestNaming.VAR_ORDEN, createFileEntity(Constants.MIME_TYPE_TIFF, ComparendoServiceRestNaming.VAR_ORDEN, "orden-comparendo.tiff", orden))
                .build()
        );

        String url = buildUrlFor(ComparendoServiceRestNaming.PATH_IMPONER_COMPARENDO_MANUAL);
        ResponseEntity<UUID> response = restTemplate.postForEntity(url, request, UUID.class);
        return resolveResponse(url, response);
    }

    public UUID imponerComparendoElectronico(FormatoComparendo formatoComparendo, byte[] firmaAgente, byte[] foto1, byte[] foto2) throws Exception {
        // TODO: hacer la implementación para electrónicos
        return null;
    }

    public CommandContext generarResolucionAutomaticaFallo(
            UUID comparendoId, CommandContext commandContext) throws ComparendoException {
        String url = buildUrlFor(ComparendoServiceRestNaming.PATH_GENERAR_RESOLUCION_AUTOMATICA_FALLO);
        try {
            ResponseEntity<CommandContext> response = restTemplate.postForEntity(
                    url,
                    createJsonEntity(commandContext),
                    CommandContext.class,
                    ImmutableMap.<String, String>builder().put(ComparendoServiceRestNaming.VAR_COMPARENDO_ID, comparendoId.toString()).build()
            );
            return resolveResponse(url, response);
        } catch (ClientException e) {
            throw new ComparendoException(e);
        }
    }

    public CommandContext generarConstanciaEjecutoriaAutomatica(UUID comparendoId, CommandContext commandContext) throws ComparendoException {
        String url = buildUrlFor(ComparendoServiceRestNaming.PATH_GENERAR_CONSTANCIA_EJECUTORIA_AUTOMATICA);
        ResponseEntity<CommandContext> response = restTemplate.postForEntity(
                url,
                createJsonEntity(commandContext),
                CommandContext.class,
                ImmutableMap.<String, String>builder().put(ComparendoServiceRestNaming.VAR_COMPARENDO_ID, comparendoId.toString()).build()
        );
        try {
            return resolveResponse(url, response);
        } catch (ClientException e) {
            throw new ComparendoException(e);
        }
    }

    public CommandContext generarResolucionReincidencia(UUID comparendoId, CommandContext context) throws ComparendoException {
        String url = buildUrlFor(ComparendoServiceRestNaming.PATH_GENERAR_RESOLUCION_REINCIDENCIA);
        ResponseEntity<CommandContext> response = restTemplate.postForEntity(
                url,
                createJsonEntity(context),
                CommandContext.class,
                ImmutableMap.<String, String>builder().put(ComparendoServiceRestNaming.VAR_COMPARENDO_ID, comparendoId.toString()).build()
        );
        try {
            return resolveResponse(url, response);
        } catch (ClientException e) {
            throw new ComparendoException(e);
        }
    }

    public CommandContext identificarReincidencia(UUID comparendoId, CommandContext context) throws ComparendoException {
        String url = buildUrlFor(ComparendoServiceRestNaming.PATH_IDENTIFICAR_REINCIDENCIA);
        ResponseEntity<CommandContext> response = restTemplate.postForEntity(
                url,
                createJsonEntity(context),
                CommandContext.class,
                ImmutableMap.<String, String>builder().put(ComparendoServiceRestNaming.VAR_COMPARENDO_ID, comparendoId.toString()).build()
        );
        try {
            return resolveResponse(url, response);
        } catch (ClientException e) {
            throw new ComparendoException(e);
        }
    }
}
