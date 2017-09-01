package co.gov.movilidadbogota.sipa.sso.conf;

import org.springframework.security.core.AuthenticationException;

/**
 * Excepcion cuando el usuario tiene fecha de caducidad expirada
 *
 * @author lorenzo.pinango
 */
public class UserExpiredException extends AuthenticationException {

    public UserExpiredException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserExpiredException(String msg) {
        super(msg);
    }
}

