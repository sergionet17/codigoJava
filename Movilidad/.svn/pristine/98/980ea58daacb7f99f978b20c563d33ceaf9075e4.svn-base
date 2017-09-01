package co.gov.movilidadbogota.sipa.serv.doc;

import co.gov.movilidadbogota.sipa.conf.ApplicationProperties;
import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by acpreda on 6/27/17.
 */
public class CmisSessionFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(CmisSessionFactory.class);

    private final ApplicationProperties.Cmis cmisProperties;

    private final SessionFactory factory;

    private String repositoryId;

    private Session session;

    public CmisSessionFactory(ApplicationProperties.Cmis cmisProperties) {
        factory = SessionFactoryImpl.newInstance();
        this.cmisProperties = cmisProperties;
        if(org.apache.commons.lang3.StringUtils.isBlank(cmisProperties.getRepositoryId())) {
            repositoryId = getDefaultRepository();
            LOGGER.info("Repositorio por defecto encontrado: " + repositoryId);
        } else {
            repositoryId = cmisProperties.getRepositoryId();
            LOGGER.info("Repositorio especificado por configuración: " + repositoryId);
        }
        session = factory.createSession(getParameters());
    }

    public Session createSession() {
        return session;
    }

    private Map<String, String> getParameters() {
        Map<String, String> parameter = new HashMap<String, String>();

        // user credentials
        parameter.put(SessionParameter.USER, cmisProperties.getUsername());
        parameter.put(SessionParameter.PASSWORD, cmisProperties.getPassword());

        // connection settings
        parameter.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());
        parameter.put(SessionParameter.BROWSER_URL, cmisProperties.getServiceRoot());
        parameter.put(SessionParameter.REPOSITORY_ID, repositoryId);

        return parameter;
    }

    public List<Repository> repositories() {
        SessionFactory factory = SessionFactoryImpl.newInstance();
        return factory.getRepositories(getParameters());
    }

    public String getDefaultRepository() {
        List<Repository> repos = factory.getRepositories(getParameters());
        if(repos != null && repos.size() > 0) {
            return repos.get(0).getId();
        } else {
            return null;
        }
    }
}
