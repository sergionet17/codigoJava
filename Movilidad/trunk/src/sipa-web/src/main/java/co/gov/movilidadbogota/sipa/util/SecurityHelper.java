package co.gov.movilidadbogota.sipa.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * Created by acpreda on 7/9/17.
 */
@Service
public class SecurityHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityHelper.class);

    @Autowired
    OAuth2RestTemplate oAuth2RestTemplate;

    @Value("${app.oauth2-server:http://localhost:8082/sipa-cas}/api")
    String oauth2ResourceUrl;

    public <T> T getUsuario(Class<T> clazz) {
        Principal principal = getPrincipal();
        if (principal == null)
            return null;
        return getUsuario(principal.getName(), clazz);
    }

    public <T> T getUsuario(String username, Class<T> clazz) {
        String url = String.format("%s/user/%s", oauth2ResourceUrl, username);
        T usuario = oAuth2RestTemplate.getForObject(url, clazz);
        if (LOGGER.isDebugEnabled())
            LOGGER.debug("Información del usuario obtenida del CAS: {}", usuario);
        if (usuario != null)
            return usuario;
        else
            return null;
    }

    public Authentication getPrincipal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null)
            return null;
        return auth;
    }

}
