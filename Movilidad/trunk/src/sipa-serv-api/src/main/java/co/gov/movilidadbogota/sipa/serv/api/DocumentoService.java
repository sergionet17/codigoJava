package co.gov.movilidadbogota.sipa.serv.api;

import co.gov.movilidadbogota.sipa.data.doc.Actuacion;
import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.doc.Expediente;

import java.util.List;
import java.util.UUID;

/**
 * Servicio especializado en el manejo del respositorio de documentos.
 *
 * @author acpreda
 */
public interface DocumentoService {

    /**
     * Es el nombre bajo el cual se deja el identificador único de documento cuando se pasa este
     * valor mediante un {@link CommandContext}
     */
    String CTX_DOCUMENTO_ID = "idDocumento";

    /**
     * Es el nombre bajo el cual se deja la bandera que indica si un documento se sustancia o no
     * cuando se pasa este valor mediante un {@link CommandContext}
     */
    String CTX_DOCUMENTO_SE_SUSTANCIA = "seSustancia";

    /**
     * Es el nombre bajo el cual se deja la bandera que indica si un documento se verifica o no
     * cuando se pasa este valor mediante un {@link CommandContext}
     */
    String CTX_DOCUMENTO_SE_VERIFICA = "seVerifica";

    /**
     * Es el nombre bajo el cual se deja la bandera que indica si un documento se firma o no
     * cuando se pasa este valor mediante un {@link CommandContext}
     */
    String CTX_DOCUMENTO_SE_FIRMA = "seFirma";

    /**
     * Es el nombre bajo el cual se deja la bandera que indica si un documento se firma de
     * manera manua o no cuando se pasa este valor mediante un {@link CommandContext}
     */
    String CTX_DOCUMENTO_SE_FIRMA_MANUAL = "seFirma";

    /**
     * Es el nombre bajo el cual se deja el nombre original de un documento cuando se pasa
     * este valor mediante un {@link CommandContext}
     */
    String CTX_DOCUMENTO_ORIGINAL_NAME = "originalName";

    /**
     * Es el nombre bajo el cual se deja el tipo documental de un documento cuando se pasa
     * este valor mediante un {@link CommandContext}
     */
    String CTX_DOCUMENTO_TIPO_DOCUMENTAL = "idTipoDocumental";

    /**
     * Es el identificador del expediente al que se debe asociar el documento
     */
    String CTX_EXPEDIENTE_ID = "idExpediente";

    /**
     * Guarda un documento.
     *
     * @param documento La entidad con la información del documento. En este objeto se deben
     *                  especificar todas las propiedades referentes al contenido ya que estas
     *                  complementan el contenido que se suministra como arreglo de bytes.
     * @param contenido Es el contenido del archivo que corresponde al documento.
     * @return El objeto documento que se almacenó efectivamente
     * @throws DocumentoException       cuando se presenta algún error al momento de guardar el documento
     *                                  o su contenido en los sistemas externos de repositorio.
     * @throws IllegalArgumentException cuando alguno de los parámetros contiene información errónea
     *                                  o incompleta.
     */
    Documento addDocumento(Documento documento, byte[] contenido)
            throws DocumentoException;

    /**
     * Guarda un documento, asociándolo a un expediente existente.
     *
     * @param documento  La entidad con la información del documento. En este objeto se deben
     *                   especificar todas las propiedades referentes al contenido ya que estas
     *                   complementan el contenido que se suministra como arreglo de bytes.
     * @param expediente Es el expediente al que debe quedar asociado el
     *                   documento.
     * @param contenido  Es el contenido del archivo que corresponde al documento.
     * @return El objeto documento que se almacenó efectivamente
     * @throws DocumentoException       cuando se presenta algún error al momento de guardar el documento
     *                                  o su contenido en los sistemas externos de repositorio.
     * @throws IllegalArgumentException cuando alguno de los parámetros contiene información errónea
     *                                  o incompleta. Además cuando el documento ya existe o el expediente
     *                                  no existe.
     */
    Documento addDocumento(Documento documento, Expediente expediente, byte[] contenido)
            throws DocumentoException;

    /**
     * Guarda un documento, asociándolo a un expediente existente.
     *
     * @param documento  La entidad con la información del documento. En este objeto se deben
     *                   especificar todas las propiedades referentes al contenido ya que estas
     *                   complementan el contenido que se suministra como arreglo de bytes.
     * @param expediente Es el expediente al que debe quedar asociado el
     *                   documento.
     * @param contenido  Es el contenido del archivo que corresponde al documento.
     * @param actuacion  Es la actuación asociada al documento
     * @return El objeto documento que se almacenó efectivamente
     * @throws DocumentoException       cuando se presenta algún error al momento de guardar el documento
     *                                  o su contenido en los sistemas externos de repositorio.
     * @throws IllegalArgumentException cuando alguno de los parámetros contiene información errónea
     *                                  o incompleta. Además cuando el documento ya existe o el expediente
     *                                  no existe.
     */
    Documento addDocumento(Documento documento, Expediente expediente, byte[] contenido, Actuacion actuacion)
            throws DocumentoException;

    /**
     * Asocia un documento al expediente
     *
     * @param documento  La entidad con la información del documento.
     * @param expediente Es el expediente al que debe quedar asociado el documento.
     * @param actuacion  Es el rol que cumple el documento en el expediente
     * @throws DocumentoException       cuando se presenta algún error al momento de guardar el documento
     *                                  o su contenido en los sistemas externos de repositorio.
     * @throws IllegalArgumentException cuando alguno de los parámetros contiene información errónea
     *                                  o incompleta. Además cuando el documento ya existe o el expediente
     *                                  no existe.
     */
    void asociarDocumento(Documento documento, Expediente expediente, Actuacion actuacion)
            throws DocumentoException;

    /**
     * Actualiza los datos y contenido de un documento preexistente
     *
     * @param documento El documento a actualizar
     * @param contenido El nuevo contenido del documento
     * @throws DocumentoException cuando el repositorio externo  produce un error
     */
    void actualizarDocumento(Documento documento, byte[] contenido) throws DocumentoException;

    /**
     * Crea un expediente
     *
     * @param expediente La entidad que contiene la información que define el expediente
     * @throws DocumentoException       cuando se presenta algún error al momento de guardar el expediente
     *                                  en los sistemas externos de repositorio.
     * @throws IllegalArgumentException cuando alguno de los parámetros contiene información errónea
     *                                  o incompleta, cuando el expediente ya existe.
     */
    Expediente addExpediente(Expediente expediente)
            throws DocumentoException;

    /**
     * Obtiene un documento almacenado en el repositorio.
     *
     * @param documentoId El identificador único del documento
     * @return La entidad que contiene los datos del documento, excepto el contenido en bytes. En
     * caso de no encontrar un documento con el identificador suministrado devuelve null.
     * @throws DocumentoException cuando se presenta algún error al momento de consultar el documento
     *                            en los sistemas externos de repositorio.
     */
    Documento getDocumento(UUID documentoId)
            throws DocumentoException;

    /**
     * Obtiene el contenido del documento en bytes.
     *
     * @param documentoId El identificador único del documento
     * @return El arreglo de bytes con el contenido del documento
     * @throws DocumentoException cuando se presenta algún error al momento de consultar el documento
     *                            en los sistemas externos de repositorio.
     */
    byte[] getContenidoDocumento(UUID documentoId)
            throws DocumentoException;

    /**
     * Obtiene un expediente almacenado en el sistema
     *
     * @param expedienteId Identificador único del expediente
     * @return La entidad que contiene los datos del expediente. No contiene los documentos asociados
     * al expediente ya que estos pueden obtenerse con otra operación del servicio usando el mismo
     * identificador de expediente.
     * @throws DocumentoException cuando se presenta algún error al momento de consultar el expediente
     *                            en los sistemas externos de repositorio.
     */
    Expediente getExpediente(UUID expedienteId)
            throws DocumentoException;

    /**
     * Obtiene un listado de documentos asociados al expediente en orden cronológico ascendente
     *
     * @param expedienteId Identificador único del expediente
     * @return Un listado con entidades que describen los documentos que se encuentran asociados
     * al expediente. Los objetos de la lista no incorporan el contenido del documento.
     * @throws DocumentoException cuando se presenta algún error al momento de consultar el expediente
     *                            en los sistemas externos de repositorio.
     */
    List<Documento> getDocumentosExpediente(UUID expedienteId)
            throws DocumentoException;

    /**
     * Obtiene los objetos de documentos solicitados. En caso de no encontrar alguno de los documentos
     * solicitados la operación falla ya que no hay manera de notificar al cliente del servicio cuáles
     * de los documentos hacen falta; esta falla se notifica mediante un excepción.
     *
     * @param documentoIds Lista con los identificadores únicos de documento que se requieren.
     * @return Lista con los objetos de documento que corresponden a los identificadores únicos
     * solicitados.
     * @throws DocumentoException cuando se presenta algún error al momento de consultar el expediente
     *                            en los sistemas externos de repositorio. También cuando uno de los
     *                            documentos de la lista no se encuentra.
     */
    List<Documento> getDocumentos(List<UUID> documentoIds)
            throws DocumentoException;

    /**
     * Construye un documento a partir de una plantilla. La plantilla debe existir previamente en
     * el sistema.
     *
     * @param nombrePlantilla Es el nombre con el que se conoce la plantilla en el sistema
     * @param context         Son los parámetros que requiere el extractor de datos asociado a la
     *                        plantilla. Por lo general estos son identificadores únicos de objetos
     *                        previamente almacenados en la base de datos.
     * @return El contexto actualizado con el identificador del documento bajo la clave CTX_DOCUMENTO_ID
     * @throws DocumentoException Cuando ocurre un error con alguno de los sistemas dependencia o
     *                            cuando la plantilla no existe en el sistema. También se lanza
     *                            esta excepción cuando la configuración de la plantilla no es
     *                            consistente y por tanto no se puede llevar a cabo la tarea.
     */
    CommandContext generarDocumento(String nombrePlantilla, CommandContext context)
            throws DocumentoException;

    /**
     * Construye la dirección de edición de Sharepoint de un documento
     * @param idDocumento
     * @return
     * @throws DocumentoException
     */
    String getSharepointUrl(UUID idDocumento) throws DocumentoException;

}
