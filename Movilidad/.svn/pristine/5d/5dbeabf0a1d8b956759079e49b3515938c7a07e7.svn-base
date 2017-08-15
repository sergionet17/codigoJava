package co.gov.movilidadbogota.sipa.cli;

import co.gov.movilidadbogota.sipa.serv.api.*;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

/**
 * Adaptador del servicio de títulos ejecutivos HTTP REST
 */
public class TituloEjecutivoServiceClient extends GenericRemoteClient implements TituloEjecutivoService {

    public TituloEjecutivoServiceClient(RestTemplate restTemplate, ServiceLocationProperties serviceLocationProperties) {
        super(serviceLocationProperties, restTemplate);
    }

    @Override
    public CommandContext registrarTituloEjecutivo(UUID comparendoId, CommandContext context) throws TituloEjecutivoException {
        String url = buildUrlFor(TituloEjecutivoServiceRestNaming.PATH_REGISTRAR);
        ResponseEntity<CommandContext> response = restTemplate.postForEntity(
                url,
                createJsonEntity(context),
                CommandContext.class,
                ImmutableMap.<String, String>builder().put(TituloEjecutivoServiceRestNaming.VAR_COMPARENDO_ID, comparendoId.toString()).build()
        );
        try {
            return resolveResponse(url, response);
        } catch (ClientException e) {
            throw new TituloEjecutivoException(e);
        }
    }

    @Override
    public CommandContext iniciarProcesoCobroPersuasivo(UUID tituloEjecutivoId, CommandContext context) throws TituloEjecutivoException {
        String url = buildUrlFor(TituloEjecutivoServiceRestNaming.PATH_INICIAR_COBRO_PERSUASIVO);
        ResponseEntity<CommandContext> response = restTemplate.postForEntity(
                url,
                createJsonEntity(context),
                CommandContext.class,
                ImmutableMap.<String, String>builder().put(TituloEjecutivoServiceRestNaming.VAR_TITULO_EJECUTIVO_ID, tituloEjecutivoId.toString()).build()
        );
        try {
            return resolveResponse(url, response);
        } catch (ClientException e) {
            throw new TituloEjecutivoException(e);
        }
    }
}
