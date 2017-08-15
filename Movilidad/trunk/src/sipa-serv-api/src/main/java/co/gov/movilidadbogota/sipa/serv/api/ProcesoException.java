package co.gov.movilidadbogota.sipa.serv.api;

public class ProcesoException extends Exception {
    public ProcesoException(String message) {
        super(message);
    }

    public ProcesoException(String msg, Exception cause) {
        super(msg, cause);
    }
}
