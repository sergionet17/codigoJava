package co.gov.movilidadbogota.bpm;

import co.gov.movilidadbogota.sipa.serv.api.CommandContext;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import java.io.Serializable;
import java.util.Map;

public class CommandContextUtils {

    /**
     * Construye un contexto pasando todos lo parámetros serializables del {@link DelegateExecution}
     * @param execution El contexto de ejecución del proceso
     * @return Un nuevo contexto de comando con los parámetros serializables
     */
    public static CommandContext from(DelegateExecution execution) {
        CommandContext ctx = new CommandContext();
        for(Map.Entry<String, Object> x : execution.getVariables().entrySet()) {
            if(x.getValue() instanceof Serializable) {
                ctx.put(x.getKey(), x.getValue());
            }
        }
        return ctx;
    }

    /**
     * Traspasa las variables de contexto a la ejecución
     * @param context
     * @param execution
     */
    public static void to(CommandContext context, DelegateExecution execution) {
        for(Map.Entry<String, Object> x : context.entrySet()) {
            if(x.getValue() instanceof Serializable) {
                execution.setVariable(x.getKey(), x.getValue());
            }
        }
    }
}
