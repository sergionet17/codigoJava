package co.gov.movilidadbogota.sipa.sso.conf;

import org.springframework.security.core.AuthenticationException;

/**
 * Excepcion cuando el usuario no esta activo al momento de autenticar
 *
 * @author lorenzo.pinango
 */
public class UserNotActivatedException extends AuthenticationException {

    public UserNotActivatedException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserNotActivatedException(String msg) {
        super(msg);
    }
}

