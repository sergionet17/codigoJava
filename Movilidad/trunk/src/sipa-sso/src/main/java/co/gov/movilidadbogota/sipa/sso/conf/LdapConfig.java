package co.gov.movilidadbogota.sipa.sso.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.core.support.SimpleDirContextAuthenticationStrategy;

/**
 * Configuracion para la conexion con LDAP
 *
 * @author lorenzo.pinango
 */
@Configuration
public class LdapConfig {
    @Value("${ldap.server:ldap://localhost:10389/}")
    String ldapServer;
    @Value("${ldap.base-dn:dc=example,dc=com}")
    String baseDn;
    @Value("${ldap.user-connection: uid=admin,ou=system}")
    String userConnection;
    @Value("${ldap.password-user-connection: secret}")
    String password;

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationServerConfiguration.class);

    @Bean
    public LdapContextSource getContextSource() throws Exception {
        LdapContextSource ldapContextSource = new LdapContextSource();
        ldapContextSource.setUrl(ldapServer);
        ldapContextSource.setBase(baseDn);
        ldapContextSource.setUserDn(userConnection);
        ldapContextSource.setPassword(password);
        ldapContextSource.setAuthenticationStrategy(new SimpleDirContextAuthenticationStrategy());
        ldapContextSource.setPooled(true);
        return ldapContextSource;
    }

    @Bean
    public LdapTemplate ldapTemplate() throws Exception {
        LdapTemplate ldapTemplate = new LdapTemplate(getContextSource());
        ldapTemplate.setIgnorePartialResultException(true);
        ldapTemplate.setContextSource(getContextSource());
        return ldapTemplate;
    }
}
