package co.gov.movilidadbogota.sipa.serv.api;

/**
 * Cuando se implementa es responsable de la ejecución de una tarea
 * específica dependiendo de los datos que se pasan en el contexto
 * {@link CommandContext}. En el contexto es donde también se dejan
 * los resultados.
 */
public interface Command {

    /**
     * Realiza la acción para la cual se implementa la interfaz
     *
     * @param ctx Es el objeto que contiene tanto los parámetros de
     *            entrada como los de salida.
     * @throws Exception cuando la implementación presenta alguna
     *                   falla interna que no puede controlar.
     */
    void execute(CommandContext ctx) throws Exception;

}
