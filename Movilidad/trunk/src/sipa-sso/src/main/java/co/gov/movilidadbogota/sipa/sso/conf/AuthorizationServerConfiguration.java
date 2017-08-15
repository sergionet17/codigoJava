package co.gov.movilidadbogota.sipa.sso.conf;

import co.gov.movilidadbogota.sipa.data.aut.Permiso;
import co.gov.movilidadbogota.sipa.data.aut.Rol;
import co.gov.movilidadbogota.sipa.data.aut.Usuario;
import co.gov.movilidadbogota.sipa.data.aut.UsuarioRepo;
import co.gov.movilidadbogota.sipa.data.util.MensajeHelper;
import co.gov.movilidadbogota.sipa.sso.repo.LdapRepo;
import co.gov.movilidadbogota.sipa.trans.UsuarioActiveDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * Configuracion para el servidor de autenticacion con OAUTH2
 *
 * @author lorenzo.pinango
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfiguration extends WebSecurityConfigurerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationServerConfiguration.class);
    @Value("${ldap.server:ldap://localhost:10389/}")
    String ldapServer;
    @Value("${ldap.base-dn:dc=example,dc=com}")
    String baseDn;
    @Value("${ldap.user-dn-Patterns:uid={0},ou=users}")
    String userDn;
    @Value("${ldap.password-attribute:userPassword}")
    String passwordAttribute;
    @Value("${ldap.validar:false}")
    Boolean validarLdap;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        LOGGER.info("Configurando la seguridad por HttpSecurity");

        http.csrf().disable();

        http.formLogin().loginPage("/login")
                .failureForwardUrl("/loginError")
                .failureHandler(authenticationFailureHandler())
                .and()
                .logout();
        http.authorizeRequests()
                .antMatchers(
                        "/login",
                        "/loginError",
                        "/cambiar-password",
                        "/reset-password/**",
                        "/resetPasswd",
                        "/editar",
                        "/editarPasswd",
                        "/passwd-reset",
                        "/reset",
                        "/loginError/**",
                        "/error",
                        "/oauth/authorize",
                        "/oauth/confirm_access",
                        "/static/**",
                        "/assets/**",
                        "/api/**",
                        "/docs/**")
                .permitAll();
        http.authorizeRequests().anyRequest().fullyAuthenticated();
    }

    /**
     * Para capturar las excepciones cuando falla la autenticacion del login
     *
     * @return Custom {@link AuthenticationFailureHandler}
     */
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new LoginAuthenticationFailureHandler();
    }

    @Bean
    OAuth2RestTemplate oauth2RestTemplate(OAuth2ClientContext oauth2ClientContext, OAuth2ProtectedResourceDetails details) {
        return new OAuth2RestTemplate(details, oauth2ClientContext);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        if (validarLdap) {
            LOGGER.info("Configurando la seguridad por AuthenticationManagerBuilder para ldap");
            auth.ldapAuthentication()
                    .userDetailsContextMapper(userDetailsContextMapper())
                    .userDnPatterns(userDn)
                    .contextSource(contextSource())
                    .passwordCompare()
                    .passwordEncoder(new LdapShaPasswordEncoder())
                    .passwordAttribute(passwordAttribute);
        }

        LOGGER.info("Configurando la seguridad por AuthenticationManagerBuilder para jdbc");
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(new ShaPasswordEncoder());
    }

    /**
     * Para obtener el detalle de usuario asociado al username del LDAP
     *
     * @return Custom {@link UserDetails}
     */
    @Bean
    public UserDetailsContextMapper userDetailsContextMapper() {
        return new LdapUserDetailsMapper() {
            @Autowired
            LdapTemplate ldapTemplate;
            @Value("${ldap.user-base:ou=users}")
            String userBase;
            @Autowired
            private UsuarioRepo userRepository;

            @Override
            @Transactional
            public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
                LOGGER.info("Se obtiene detalle de usuario para LDAP");
                LOGGER.info("Username: " + username);

                Usuario userFromDatabase = userRepository.findOneByUsername(username);

                if (userFromDatabase == null) {
                    LOGGER.info("Error no se encontro el usuario de LDAP " + username + " en la BD");
                    throw new UserNotFoundException("El usuario " + username +
                            " se autentic&oacute; exitosamente con credenciales del LDAP" +
                            ", pero no se encontr&oacute; en la base de datos del sistema");
                } else if (!userFromDatabase.getActivo()) {
                    LOGGER.info("Error el usuario " + username + " no se encuentra activo");
                    throw new UserNotActivatedException("El usuario " + username +
                            " se autentic&oacute; exitosamente con credenciales del LDAP" +
                            ", pero no encuentra activo en el sistema");
                }

                //Se actualizan los datos del usuario
                LdapRepo ldapRepo = new LdapRepo();
                ldapRepo.setLdapTemplate(ldapTemplate);
                UsuarioActiveDirectory usuarioLdap = null;
                try {
                    usuarioLdap = ldapRepo.getUserDetails(username, userBase);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (usuarioLdap != null) {
                    userFromDatabase.setPassword(usuarioLdap.getUserPassword().replace("{SHA}", ""));
                    if (usuarioLdap.getPwdAccountLockedTime() != null)
                        userFromDatabase.setFechaCaducidad(usuarioLdap.getPwdAccountLockedTime());
                    userRepository.save(userFromDatabase);
                }

                //Se verifica si la cuenta esta caduca
                if (userFromDatabase.getFechaCaducidad().compareTo(new Date()) < 0) {
                    LOGGER.info("Error el usuario " + username + " esta expirado");
                    throw new UserExpiredException("El usuario " + username +
                            " no puede ingresar al sistema ya que su fecha de caducidad" +
                            " expir&oacute;");
                }

                Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                for (Rol rol : userFromDatabase.getPerfil().getRoles()) {
                    for (Permiso permiso : rol.getPermisos()) {
                        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permiso.getNombre());
                        grantedAuthorities.add(grantedAuthority);
                    }
                }
                return new org.springframework.security.core.userdetails.User(userFromDatabase.getUsername(), userFromDatabase.getPassword(), grantedAuthorities);
            }
        };
    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource() {
        return new DefaultSpringSecurityContextSource(Collections.singletonList(ldapServer), baseDn);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * Configuracion del servidor de autenticacion para OAUTH2
     */
    @Configuration
    protected static class Oauth2Configuration extends AuthorizationServerConfigurerAdapter {

        @Autowired
        private DataSource dataSource;
        @Autowired
        private AuthenticationManager authenticationManager;
        @Autowired
        private UserDetailsService userDetailsService;

        @Bean
        public JdbcClientDetailsService obtenerClientes() {
            return new JdbcClientDetailsService(dataSource);
        }

        @Bean
        public TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            endpoints
                    .approvalStore(approvalStore())
                    .authorizationCodeServices(authorizationCodeServices())
                    .tokenStore(tokenStore())
                    .userDetailsService(userDetailsService)
                    .authenticationManager(authenticationManager);
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("isAuthenticated()");
        }

        @Bean
        public ApprovalStore approvalStore() {
            return new JdbcApprovalStore(dataSource);
        }

        @Bean
        public MensajeHelper mensajeHelper() {
            return new MensajeHelper();
        }

        @Bean
        public AuthorizationCodeServices authorizationCodeServices() {
            return new JdbcAuthorizationCodeServices(dataSource);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.withClientDetails(obtenerClientes());
        }
    }

}
