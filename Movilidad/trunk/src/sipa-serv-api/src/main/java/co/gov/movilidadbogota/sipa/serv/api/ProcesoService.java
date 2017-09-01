package co.gov.movilidadbogota.sipa.serv.api;

import java.util.Map;

/**
 * Cuando se implementa realiza la adaptación de las operaciones al motor de procesos
 * correspondiente a la implementación.
 */
public interface ProcesoService {

    /**
     * Envía un mensaje a las instancias de proceso que correspondan a la correlación
     * que se realiza con el businessKey y correlationKeys.
     *
     * @param messageName      El nombre del mensaje
     * @param processVariables Son los valores de las variables que serán inyectadas en el
     *                         contexto de las instancias de proceso destino.
     * @param businessKey      La clave de negocio que hará correlación con las instancias
     *                         de proceso que tengan la misma clave de negocio.
     * @param correlationKeys  Las claves que harán correlación con las variables de proceso
     *                         de las instancias.
     */
    void deliverMessage(
            String messageName,
            Map<String, ProcessVariableValue> processVariables,
            String businessKey,
            Map<String, ProcessVariableValue> correlationKeys
    ) throws ProcesoException;

    /**
     * Envía un mensaje a las instancias de proceso que correspondan a la correlación
     * que se realiza con el businessKey.
     *
     * @param messageName      El nombre del mensaje
     * @param processVariables Son los valores de las variables que serán inyectadas en el
     *                         contexto de las instancias de proceso destino.
     * @param businessKey      La clave de negocio que hará correlación con las instancias
     *                         de proceso que tengan la misma clave de negocio.
     * @throws ProcesoException
     */
    void deliverMessage(
            String messageName,
            Map<String, ProcessVariableValue> processVariables,
            String businessKey
    ) throws ProcesoException;

    /**
     * Envía un mensaje a las instancias de proceso.
     *
     * @param messageName      El nombre del mensaje
     * @param processVariables Son los valores de las variables que serán inyectadas en el
     *                         contexto de las instancias de proceso destino.
     * @throws ProcesoException
     */
    void deliverMessage(
            String messageName,
            Map<String, ProcessVariableValue> processVariables
    ) throws ProcesoException;

    /**
     * Envía un mensaje a las instancias de proceso.
     *
     * @param messageName El nombre del mensaje
     * @throws ProcesoException
     */
    void deliverMessage(
            String messageName
    ) throws ProcesoException;

    /**
     * Inicia una instancia de proceso
     * @param key Clave del proceso, es decir el nombre
     * @param ctx Variables de inicio
     */
    CommandContext start(String key, CommandContext ctx) throws ProcesoException;

}
