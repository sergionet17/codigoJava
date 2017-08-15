package co.gov.movilidadbogota.sipa.bpm;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogDelegate.class);

    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("Instancia: " + execution.getProcessInstanceId());
    }
}
