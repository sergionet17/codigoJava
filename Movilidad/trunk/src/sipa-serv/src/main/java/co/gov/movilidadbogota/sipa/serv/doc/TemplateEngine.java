package co.gov.movilidadbogota.sipa.serv.doc;

import co.gov.movilidadbogota.sipa.data.doc.Plantilla;

import java.util.Map;

/**
 * Representa un motor de plantillas.
 */
public interface TemplateEngine {

    /**
     * Nombre del parámetro del para de parámetros en el que se encuentra la colección por
     * defecto que se usa para crear los detalles de la plantilla.
     */
    String DEFAULT_COLLECTION = "DEFAULT_COLLECTION";

    /**
     * Combina una plantilla con los datos que se suministran como parámetro en el contexto
     *
     *
     * @param plantilla
     * @param bytesPlantilla Bytes correspondientes a la plantilla
     * @param context        Contexto en el que se pasan los datos a la plantilla
     * @return El arreglo de bytes que corresponde a la combinación de la plantilla
     * con los datos del contexto.
     * @throws Exception cuando ocurre un error en la construcción del documento.
     */
    byte[] merge(Plantilla plantilla, byte[] bytesPlantilla, Map<String, Object> context)
            throws Exception;

}
