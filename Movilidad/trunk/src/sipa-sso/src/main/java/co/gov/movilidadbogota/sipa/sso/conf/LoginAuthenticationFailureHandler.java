package co.gov.movilidadbogota.sipa.sso.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lorenzo on 8/06/17.
 */
@Component
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final Logger log = LoggerFactory.getLogger(LoginAuthenticationFailureHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException ex) throws IOException, ServletException {
        if (log.isDebugEnabled()) {
            log.debug("Fallo la autenticacion");
            log.debug("Tipo de exception: " + ex.getClass().getSimpleName());
        }
        String mensaje;
        if (ex instanceof BadCredentialsException)
            mensaje = "Nombre de usuario o contrasena incorrecto";
        else
            mensaje = ex.getMessage();

        if (log.isDebugEnabled())
            log.debug("Mensaje: " + mensaje);
        redirectStrategy.sendRedirect(request, response, "/loginError/" + mensaje);

        //Si es una instancia de la excepcion personalizada redireccion a cambiar contraseña
        if(ex instanceof PasswdExpiredException){
            redirectStrategy.sendRedirect(request, response, "/cambiar-password");
        }
    }
}