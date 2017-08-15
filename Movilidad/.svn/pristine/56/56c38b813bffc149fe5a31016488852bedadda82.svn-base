package co.gov.movilidadbogota.bpm;

import co.gov.movilidadbogota.sipa.cli.SipaServicesClient;
import co.gov.movilidadbogota.sipa.serv.api.CommandContext;
import co.gov.movilidadbogota.sipa.serv.api.DocumentoService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenerarDocumentoDesdePlantilla implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(GenerarDocumentoDesdePlantilla.class);

    @Autowired
    private SipaServicesClient services;

    public void execute(DelegateExecution execution) throws Exception {

        String nombrePlantilla = (String) execution.getVariable("nombrePlantilla");

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("nombrePlantilla=%s", nombrePlantilla));
        }

        CommandContext ctx = CommandContextUtils.from(execution);
        CommandContext ctxResultado = services.getDocumentos().generarDocumento(nombrePlantilla, ctx);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(String.format("nombrePlantilla=%s, ctx=%s, ctxResultado=%s",
                    nombrePlantilla, ctx, ctxResultado
            ));
        }

        CommandContextUtils.to(ctxResultado, execution);
    }
}
