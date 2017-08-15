package co.gov.movilidadbogota.sipa.sso.conf;

import org.springframework.security.core.AuthenticationException;

/**
 * Excepcion cuando el usuario no esta activo al momento de autenticar
 *
 * @author lorenzo.pinango
 */
public class UserNotFoundException extends AuthenticationException {

    public UserNotFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }
}

