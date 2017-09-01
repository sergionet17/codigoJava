package co.gov.movilidadbogota.sipa.sso.conf;

import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.aut.UsuarioRepo;
import co.gov.movilidadbogota.sipa.sso.repo.LdapRepo;
import co.gov.movilidadbogota.sipa.trans.UsuarioActiveDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpMethod;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.text.ParseException;

/**
 * Configuracion del resource para obtener la informacion del usuario autenticado
 *
 * @author lorenzo.pinango
 */
@EnableResourceServer
@RestController
@EntityScan(basePackages = {"co.gov.movilidadbogota.sipa.data"})
@EnableJpaRepositories(basePackages = {"co.gov.movilidadbogota.sipa.data"})
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceServerConfiguration.class);
    @Autowired
    private UsuarioRepo usuarioRepo;

    @Autowired
    LdapTemplate ldapTemplate;

    @Value("${ldap.user-base:ou=users}")
    String userBase;

    @RequestMapping(path = "api/user/{username}", method = RequestMethod.GET)
    @Transactional
    Usuario getUsuario(@PathVariable("username") String username) {
        LOGGER.info("Se obtiene el usuario asociado al username");
        return usuarioRepo.findOneByUsername(username);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").access("#oauth2.hasScope('read')")
                .antMatchers(HttpMethod.GET, "/api/**").access("#oauth2.hasScope('write')");
    }

    @RequestMapping(path = "api/user/ldap/{username}", method = RequestMethod.GET)
    UsuarioActiveDirectory getUsuarioLdap(@PathVariable("username") String username) throws ParseException {
        LOGGER.info("Se obtiene el usuario asociado al username del ldap");
        LdapRepo ldapRepo = new LdapRepo();
        ldapRepo.setLdapTemplate(ldapTemplate);
        return ldapRepo.getUserDetails(username, userBase);
    }

}
