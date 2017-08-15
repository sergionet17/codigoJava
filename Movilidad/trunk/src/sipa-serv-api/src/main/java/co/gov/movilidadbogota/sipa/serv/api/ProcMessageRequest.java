package co.gov.movilidadbogota.sipa.serv.api;

import java.util.Map;

/**
 * Clase que se usa para las solicitudes al adaptador REST del servicio
 * {@link ProcesoService} el método deliverMessage
 */
public class ProcMessageRequest {
    private String messageName;
    private Map<String, ProcessVariableValue> processVariables;
    private String businessKey;
    private Map<String, ProcessVariableValue> correlationKeys;


    public String getMessageName() {
        return messageName;
    }

    public void setMessageName(String messageName) {
        this.messageName = messageName;
    }

    public Map<String, ProcessVariableValue> getProcessVariables() {
        return processVariables;
    }

    public void setProcessVariables(Map<String, ProcessVariableValue> processVariables) {
        this.processVariables = processVariables;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Map<String, ProcessVariableValue> getCorrelationKeys() {
        return correlationKeys;
    }

    public void setCorrelationKeys(Map<String, ProcessVariableValue> correlationKeys) {
        this.correlationKeys = correlationKeys;
    }
}
