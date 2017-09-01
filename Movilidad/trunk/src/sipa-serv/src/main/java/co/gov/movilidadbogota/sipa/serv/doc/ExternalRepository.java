package co.gov.movilidadbogota.sipa.serv.doc;

import co.gov.movilidadbogota.sipa.data.doc.Documento;
import co.gov.movilidadbogota.sipa.data.doc.Expediente;

public interface ExternalRepository {

    String createFolder(Expediente expediente);

    String createDocument(Documento documento, Expediente expediente, byte[] contenido) throws Exception;

    String createDocument(Documento documento, byte[] contenido) throws Exception;

    String buildName(Documento documento);

    void updateDocument(Documento documento, byte[] contenido) throws Exception;

    void addDocumentToFolder(Documento documento, Expediente expediente) throws Exception;

    byte[] getContenido(String externalId);
}
