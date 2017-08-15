package co.gov.movilidadbogota.sipa.serv.api;

/**
 * Excepción genérica de operaciones con documentos
 *
 * @author acpreda
 */
public class DocumentoException extends Exception {
    public DocumentoException(String message, Exception e) {
        super(message, e);
    }

    public DocumentoException(String message) {
        super(message);
    }

    public DocumentoException(Exception e) {
        super(e);
    }
}
