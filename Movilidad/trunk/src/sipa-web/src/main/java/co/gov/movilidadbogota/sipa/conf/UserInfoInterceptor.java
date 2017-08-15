package co.gov.movilidadbogota.sipa.conf;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.biz.comp.TipoNotificacion;
import co.gov.movilidadbogota.sipa.data.gen.MensajeRepo;
import co.gov.movilidadbogota.sipa.menu.Menu;
import co.gov.movilidadbogota.sipa.menu.MenuBuilder;
import co.gov.movilidadbogota.sipa.util.SecurityHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Agrega al modelo el usuario que realizó la solicitud.
 *
 * @author arturo.cruz
 */
@Component
public class UserInfoInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInfoInterceptor.class);

    @Autowired
    SecurityHelper securityHelper;

    @Autowired
    MenuBuilder menuBuilder;

    @Autowired
    MensajeRepo mensajeRepo;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if (modelAndView == null)
            return;

        Locale l = LocaleContextHolder.getLocale();
        modelAndView.addObject("locale", l);

        Authentication principal = securityHelper.getPrincipal();
        if (principal != null) {
            modelAndView.addObject("principal", principal);
            Usuario usuario = securityHelper.getUsuario(principal.getName(), Usuario.class);
            if (usuario != null) {
                if (LOGGER.isDebugEnabled())
                    LOGGER.debug("Información del usuario obtenida del CAS: {}", usuario);
                modelAndView.addObject("usuario", usuario);
                modelAndView.addObject("mensajesSistema", mensajeRepo.findAllByUsuarioDestinoAndTipoNotificacionOrderByFechaCreacionDesc(
                        usuario, TipoNotificacion.PANTALLA
                ));
            }
        }

        Menu menu = menuBuilder.getMenu().filterBy(principal);
        modelAndView.addObject("menu", menu);

    }


}

