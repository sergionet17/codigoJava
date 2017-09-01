package co.gov.movilidadbogota.sipa.serv.api;

public class ComparendoException extends Exception {

    public ComparendoException(String message, Exception e) {
        super(message, e);
    }

    public ComparendoException(String message) {
        super(message);
    }

    public ComparendoException(Exception e) {
        super(e);
    }

}
