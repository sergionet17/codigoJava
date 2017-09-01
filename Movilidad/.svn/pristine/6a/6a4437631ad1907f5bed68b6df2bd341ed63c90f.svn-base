package co.gov.movilidadbogota.bpm;

import co.gov.movilidadbogota.sipa.cli.SipaServicesClient;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by maria on 8/10/17.
 */
@Component
public class GenericSendMessageDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyDelegate.class);
    @Autowired
    SipaServicesClient sipaServicesClient;
    @Autowired
    private RuntimeService runtimeService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        String nombreMensajeDestino = (String) execution.getVariable("nombreMensajeDestino");

        List<MessageCorrelationResult> correlation = runtimeService
                .createMessageCorrelation(nombreMensajeDestino)
                .setVariables(CommandContextUtils.from(execution))
                .correlateAllWithResult();

    }
}
