package co.gov.movilidadbogota.sipa.sso.repo;

import co.gov.movilidadbogota.sipa.trans.UsuarioActiveDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

/**
 * Repositorio para consultas con LDAP
 */
public class LdapRepo {

    private static final Logger LOGGER = LoggerFactory.getLogger(LdapRepo.class);

    private LdapTemplate ldapTemplate;

    public void setLdapTemplate(LdapTemplate ldapTemplate) {
        this.ldapTemplate = ldapTemplate;
    }

    public UsuarioActiveDirectory getUserDetails(String userName, String userBase) throws ParseException {
        LOGGER.info("Ejecutando {getUserDetails}");
        List<UsuarioActiveDirectory> list = ldapTemplate.search(query().base(userBase).where("uid").is(userName), new UserAttributesMapper());
        if (list != null && !list.isEmpty()) {
            UsuarioActiveDirectory user = list.get(0);
            final String ATTR = "pwdAccountLockedTime";
            String dn = "uid=" + user.getUid() + "," + userBase;
            String pwdAccountLockedTime = (String) ldapTemplate.lookup(dn,
                    new String[]{ATTR},
                    new AbstractContextMapper() {
                        protected Object doMapFromContext(DirContextOperations ctx) {
                            return ctx.getStringAttribute(ATTR);
                        }
                    });
            if (pwdAccountLockedTime != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                user.setPwdAccountLockedTime(sdf.parse(pwdAccountLockedTime));
            }
            return user;
        }
        return null;
    }

    /**
     * Clase encargado de preraraer el objecto {@link UsuarioActiveDirectory} despues de
     * realizar la busqueda en LDAP
     *
     * @author lorenzo.pinango
     */
    private class UserAttributesMapper implements AttributesMapper<UsuarioActiveDirectory> {

        @Override
        public UsuarioActiveDirectory mapFromAttributes(Attributes attributes) throws NamingException {
            UsuarioActiveDirectory user;
            if (attributes == null) {
                return null;
            }
            user = new UsuarioActiveDirectory();
            user.setCn(attributes.get("cn").get().toString());

            if (attributes.get("userPassword") != null) {
                String userPassword = null;
                try {
                    userPassword = new String((byte[]) attributes.get("userPassword").get(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    LOGGER.error("Ocurrio un error leyendo el password del usuario ldap", e);
                }
                user.setUserPassword(userPassword);
            }
            if (attributes.get("uid") != null) {
                user.setUid(attributes.get("uid").get().toString());
            }
            if (attributes.get("sn") != null) {
                user.setSn(attributes.get("sn").get().toString());
            }
            if (attributes.get("postalAddress") != null) {
                user.setPostalAddress(attributes.get("postalAddress").get().toString());
            }
            if (attributes.get("telephoneNumber") != null) {
                user.setTelephoneNumber(attributes.get("telephoneNumber").get().toString());
            }
            return user;
        }
    }
}
