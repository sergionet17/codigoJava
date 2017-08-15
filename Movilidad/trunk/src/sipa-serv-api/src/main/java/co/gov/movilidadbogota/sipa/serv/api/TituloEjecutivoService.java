package co.gov.movilidadbogota.sipa.serv.api;

import java.util.UUID;

/**
 * Cuando se implementa provee los servicios de título ejecutivo
 */
public interface TituloEjecutivoService {

    /**
     * Es el nombre bajo el cual se deja el identificador único de documento cuando se pasa este
     * valor mediante un {@link CommandContext}
     */
    String CTX_TITULO_EJECUTIVO_ID = "idTituloEjecutivo";


    /**
     * Generar una resolución automática de fallo asociada a un comparendo
     *
     * @param comparendoId Es el  identificador del comparendo
     * @param context      Son los parámetros se requieren para registrar el título ejecutivo
     * @return El contexto actualizado
     * @throws TituloEjecutivoException Cuando ocurre un error que impide almacenar
     *                                  la información en los repositorios o por inconsistencias
     *                                  graves
     */
    CommandContext registrarTituloEjecutivo(
            UUID comparendoId,
            CommandContext context
    ) throws TituloEjecutivoException;

    /**
     * Inicia el proceso del cobro persuasivo
     *
     * @param tituloEjecutivoId Es el  identificador del título ejecutivo
     * @param context           Son los parámetros se requieren para iniciar el cobro persuasivo
     * @throws TituloEjecutivoException Cuando ocurre un error que impide almacenar
     *                                  la información en los repositorios o por inconsistencias
     *                                  graves
     */
    CommandContext iniciarProcesoCobroPersuasivo(
            UUID tituloEjecutivoId,
            CommandContext context
    ) throws TituloEjecutivoException;

}
