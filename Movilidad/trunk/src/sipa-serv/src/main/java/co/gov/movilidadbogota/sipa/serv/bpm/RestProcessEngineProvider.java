package co.gov.movilidadbogota.sipa.serv.bpm;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.rest.spi.ProcessEngineProvider;

import java.util.Set;

/**
 * Proveedor de motor de servicios. Este se invoca por parte de la implementación
 * REST de JAX-RS Jersey para obtener el motor de procesos.
 */
public class RestProcessEngineProvider implements ProcessEngineProvider {

    /**
     * Obtiene el motor por defecto
     *
     * @return
     */
    public ProcessEngine getDefaultProcessEngine() {
        return ProcessEngines.getDefaultProcessEngine();
    }

    /**
     * Obtiene el motor de procesos con el nombre específico
     *
     * @param name Nombre del motor de procesos
     * @return La instancia del motor de procesos
     */
    public ProcessEngine getProcessEngine(String name) {
        return ProcessEngines.getProcessEngine(name);
    }

    /**
     * Obtiene el nombre de todos los motores de procesos que se encuentran registrados
     *
     * @return El conjunto de nombres de los motores registrados
     */
    public Set<String> getProcessEngineNames() {
        return ProcessEngines.getProcessEngines().keySet();
    }

}