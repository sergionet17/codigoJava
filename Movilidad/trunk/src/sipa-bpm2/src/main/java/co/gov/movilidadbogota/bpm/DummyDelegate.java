package co.gov.movilidadbogota.bpm;

import co.gov.movilidadbogota.sipa.serv.api.CommandContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DummyDelegate implements JavaDelegate {

    private static final Logger LOGGER = LoggerFactory.getLogger(DummyDelegate.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        CommandContext ctx = CommandContextUtils.from(execution);
        LOGGER.info(String.format(
                "Hey! Dummy '%s' on the road with execution variables: %s",
                execution.getCurrentActivityName(),
                ctx
        ));
    }
}
