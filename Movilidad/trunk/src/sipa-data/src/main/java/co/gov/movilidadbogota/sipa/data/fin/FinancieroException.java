package co.gov.movilidadbogota.sipa.data.fin;

/**
 * Excepción general para notificar errores en los registros financieros
 */
public class FinancieroException extends RuntimeException {
    public FinancieroException(String msg) {
        super(msg);
    }

    public FinancieroException(Exception cause) {
        super(cause);
    }
}
