package co.gov.movilidadbogota.sipa.ctrl;

import co.gov.movilidadbogota.sipa.data.gen.Constants;
import co.gov.movilidadbogota.sipa.util.SecurityHelper;
import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collection;

/**
 * Controlador de base para obtener información y funciones comunes a todos los controladores
 *
 * @author arturo.cruz
 */
@Controller
public abstract class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private SecurityHelper securityHelper;

    /**
     * Registra el objeto que se encarga de serializar y deserializar fechas según el formato
     * global del sistema, ver {@link co.gov.movilidadbogota.sipa.data.gen.Constants}
     *
     * @param binder
     */
    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addCustomFormatter(Constants.DATE_FORMATTER);
    }

    /**
     * Inyecta en el modelo una envoltura que permite el acceso a los atributos estáticos de la clase {@link Constants}
     *
     * @return
     */
    @ModelAttribute("constants")
    public TemplateHashModel constants() {
        BeansWrapperBuilder builder = new BeansWrapperBuilder(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        BeansWrapper wrapper = builder.build();
        TemplateHashModel staticModels = wrapper.getStaticModels();
        try {
            return (TemplateHashModel) staticModels.get(Constants.class.getName());
        } catch (TemplateModelException e) {
            LOGGER.error("Mientras se obtenían las constantes para la vista", e);
        }
        return null;
    }

    /**
     * Inyecta en el modelo una envoltura que permite el acceso a los atributos estáticos de la clase controladora
     */
    @ModelAttribute("controllerStatics")
    public TemplateHashModel controllerStatics() {
        BeansWrapperBuilder builder = new BeansWrapperBuilder(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        BeansWrapper wrapper = builder.build();
        TemplateHashModel staticModels = wrapper.getStaticModels();
        try {
            return (TemplateHashModel) staticModels.get(this.getClass().getName());
        } catch (TemplateModelException e) {
            LOGGER.error("Mientras se obtenían el modelo estático del controlador", e);
        }
        return null;
    }

    /**
     * Inyecta en el modelo el controllador bajo en nombre controller
     *
     * @return
     */
    @ModelAttribute("controller")
    public BaseController controller() {
        return this;
    }

    /**
     * Verifica si un rol esta asignado al usuario
     *
     * @param role
     * @return boolean
     */
    @SuppressWarnings("unchecked")
    public boolean hasRole(String role) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            if (LOGGER.isDebugEnabled())
                LOGGER.debug("El usuario no está autenticado");
            return false;
        }
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) auth.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals(role)) {
                if (LOGGER.isDebugEnabled())
                    LOGGER.debug("El usuario {} tiene el permiso {}", auth.getName(), role);
                return true;
            }
        }
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("El usuario {} no tiene el permiso {}", auth.getName(), role);
        return false;
    }

}
