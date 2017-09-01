package co.gov.movilidadbogota.sipa.serv.api;

import co.gov.movilidadbogota.sipa.data.biz.comp.FormatoComparendo;

import java.util.UUID;

/**
 * Cuando se implementa provee los servicios de imposición de comparendos
 */
public interface ComparendoService {

    /**
     * Es el nombre bajo el cual se deja el identificador único de resolución automática de fallo
     * valor mediante un {@link CommandContext}
     */
    String CTX_RESOLUCION_AUTOMATICA_FALLO = "idResolucionAutomaticaFallo";
    /**
     * Es el nombre bajo el cual se deja el identificador único de la constancia ejecutoria
     * valor mediante un {@link CommandContext}
     */
    String CTX_CONSTANCIA_EJECUTORIA_AUTOMATICA = "idConstanciaEjecutoria";
    /**
     * Es el nombre bajo el cual se deja el número de resolución automática de fallo
     * valor mediante un {@link CommandContext}
     */
    String CTX_NUMERO_RESOLUCION_AUTOMATICA_FALLO = "numeroResolucionAutomaticaFallo";
    /**
     * Es el nombre bajo el cual se deja el identificador del documento de resolución automática de fallo
     * valor mediante un {@link CommandContext}
     */
    String CTX_ID_DOCUMENTO_RESOLUCION_AUTOMATICA_FALLO = "idDocumentoResolucion";

    /**
     * Es el nombre bajo el cual se deja el identificador del documento de la constancia ejecutoria automática
     * valor mediante un {@link CommandContext}
     */
    String CTX_ID_DOCUMENTO_CONSTANCIA_EJECUTORIA_AUTOMATICA = "idDocumentoConstanciaEjecutoria";
    /**
     * Es el nombre bajo el cual se deja el identificador del documento de la resolucion de reincidencia
     * valor mediante un {@link CommandContext}
     */
    String CTX_ID_DOCUMENTO_RESOLUCION_REINCIDENCIA = "idDocumentoResolucionReincidencia";
    /**
     * Es el nombre bajo el cual se deja el identificador del documento de la resolucion de reincidencia
     * valor mediante un {@link CommandContext}
     */
    String CTX_REINCIDENTE = "reincidente";


    /**
     * Impone un comparendo manual
     *
     * @param formatoComparendo El formato con los datos del comparendo
     * @param orden             La imagen en formato TIFF con la orden de comparendo
     * @return El identificador único de comparendo
     * @throws ComparendoException Cuando ocurre un error que impide almacenar
     *                             la información en los repositorios o por inconsistencias
     *                             graves que impiden validar la información del formato
     */
    UUID imponerComparendoManual(
            FormatoComparendo formatoComparendo,
            byte[] orden
    ) throws Exception;

    /**
     * Impone un comparendo electrónico
     *
     * @param formatoComparendo El formato con los datos del comparendo
     * @param firmaAgente       La imagen con la firma del agente que impone el comparendo
     *                          en formato PNG
     * @param foto1             La imagen con la primera foto de la infracción en formato JPG
     * @param foto2             La imagen con la segunda foto de la infracción en formato JPG
     * @return El identificador único de comparendo
     * @throws ComparendoException Cuando ocurre un error que impide almacenar
     *                             la información en los repositorios o por inconsistencias
     *                             graves que impiden validar la información del formato
     */
    UUID imponerComparendoElectronico(
            FormatoComparendo formatoComparendo,
            byte[] firmaAgente,
            byte[] foto1,
            byte[] foto2
    ) throws Exception;


    /**
     * Generar una resolución automática de fallo asociada a un comparendo
     *
     * @param comparendoId Es el  identificador del comparendo
     * @param context      Son los parámetros se requieren para generar la resolución automática de fallo
     * @return El contexto actualizado
     * @throws ComparendoException Cuando ocurre un error que impide almacenar
     *                             la información en los repositorios o por inconsistencias
     *                             graves que impiden validar la información del formato
     */
    CommandContext generarResolucionAutomaticaFallo(
            UUID comparendoId,
            CommandContext context
    ) throws ComparendoException;

    /**
     * Generar una constancia ejecutoria automática asociada a un comparendo
     *
     * @param comparendoId Es el  identificador del comparendo
     * @param context      Son los parámetros se requieren para generar la resolución automática de fallo
     * @return El contexto actualizado
     * @throws ComparendoException Cuando ocurre un error que impide almacenar
     *                             la información en los repositorios o por inconsistencias
     *                             graves que impiden validar la información del formato
     */
    CommandContext generarConstanciaEjecutoriaAutomatica(
            UUID comparendoId,
            CommandContext context
    ) throws ComparendoException;

    /**
     * Generar una resolución por reincidencia
     *
     * @param comparendoId Es el  identificador del comparendo
     * @param context      Son los parámetros se requieren para generar la resolución por reincidencia
     * @return El contexto actualizado
     * @throws ComparendoException Cuando ocurre un error que impide almacenar
     *                             la información en los repositorios o por inconsistencias
     *                             graves que impiden validar la información del formato
     */
    CommandContext generarResolucionReincidencia(
            UUID comparendoId,
            CommandContext context
    ) throws ComparendoException;

    /**
     * Identifica una reincidencia
     *
     * @param comparendoId Es el  identificador del comparendo
     * @param context      Son los parámetros se requieren para identificar una reincidencia
     * @return El contexto actualizado
     * @throws ComparendoException Cuando ocurre un error que impide almacenar
     *                             la información en los repositorios o por inconsistencias
     *                             graves que impiden validar la información del formato
     */
    CommandContext identificarReincidencia(
            UUID comparendoId,
            CommandContext context
    ) throws ComparendoException;



}
