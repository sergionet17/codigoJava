package co.gov.movilidadbogota.sipa.bpm;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerDelegate implements JavaDelegate {

    private static final Logger logger = LoggerFactory.getLogger(LoggerDelegate.class);

    public void execute(DelegateExecution execution) throws Exception {
        logger.info("Instancia: " + execution.getProcessInstanceId());
    }
}
