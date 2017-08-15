package co.gov.movilidadbogota.sipa.cmis.session;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.bindings.CmisBindingFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.gov.movilidadbogota.sipa.common.ConfParameters;
import co.gov.movilidadbogota.sipa.common.Constants;

/**
 * CMIS Session Factory class, used to create session based on the binding type:
 * (WEBSERVICES, ATOMPUB, BROWSER).
 * 
 * @author Hermes
 *
 */
@Component("cmisSessionFactory")
@Scope("singleton")
public class CmisSessionFactory {
	
	/**
	 * Cmis Session factory method
	 * 
	 * @param user
	 * @param password
	 * @param type
	 * @return
	 */
	public Session createCmisSession(String user, String password, String bindingType) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put(SessionParameter.USER, user);
		parameters.put(SessionParameter.PASSWORD, password);
		
		String repository = ConfParameters.getParm(Constants.CONF_CMIS_REPOSITORY);
		if(!repository.equals("default"))
			parameters.put(SessionParameter.REPOSITORY_ID, ConfParameters.getParm(Constants.CONF_CMIS_REPOSITORY));

		CmisBindingFactory factory = CmisBindingFactory.newInstance();

		if (bindingType.equals(BindingType.WEBSERVICES.value())) {	
			parameters.put(SessionParameter.WEBSERVICES_ACL_SERVICE, ConfParameters.getParm(Constants.CONF_CMIS_URL)+"/ACLService?wsdl");
	        parameters.put(SessionParameter.WEBSERVICES_DISCOVERY_SERVICE, ConfParameters.getParm(Constants.CONF_CMIS_URL)+"/DiscoveryService?wsdl");
	        parameters.put(SessionParameter.WEBSERVICES_MULTIFILING_SERVICE, ConfParameters.getParm(Constants.CONF_CMIS_URL)+"/MultiFilingService?wsdl");
	        parameters.put(SessionParameter.WEBSERVICES_NAVIGATION_SERVICE, ConfParameters.getParm(Constants.CONF_CMIS_URL)+"/NavigationService?wsdl");
	        parameters.put(SessionParameter.WEBSERVICES_OBJECT_SERVICE, ConfParameters.getParm(Constants.CONF_CMIS_URL)+"/ObjectService?wsdl");
	        parameters.put(SessionParameter.WEBSERVICES_POLICY_SERVICE, ConfParameters.getParm(Constants.CONF_CMIS_URL)+"/PolicyService?wsdl");
	        parameters.put(SessionParameter.WEBSERVICES_RELATIONSHIP_SERVICE, ConfParameters.getParm(Constants.CONF_CMIS_URL)+"/RelationshipService?wsdl");
	        parameters.put(SessionParameter.WEBSERVICES_REPOSITORY_SERVICE, ConfParameters.getParm(Constants.CONF_CMIS_URL)+"/RepositoryService?wsdl");
	        parameters.put(SessionParameter.WEBSERVICES_VERSIONING_SERVICE, ConfParameters.getParm(Constants.CONF_CMIS_URL)+"/VersioningService?wsdl");
			factory.createCmisWebServicesBinding(parameters);
		} else if (bindingType.equals(BindingType.ATOMPUB.value())) {
			parameters.put(SessionParameter.ATOMPUB_URL, ConfParameters.getParm(Constants.CONF_CMIS_URL));
			factory.createCmisAtomPubBinding(parameters);
		} else if (bindingType.equals(BindingType.BROWSER.value())) {
			parameters.put(SessionParameter.BROWSER_URL, ConfParameters.getParm(Constants.CONF_CMIS_URL));
			factory.createCmisBrowserBinding(parameters);
		}

		SessionFactory factorysession = SessionFactoryImpl.newInstance();
		Session session = null;
		if(!repository.equals("default"))
			session = factorysession.createSession(parameters);
		else{
			session = factorysession.getRepositories(parameters).get(0).createSession();
		}
		// It´s very important does'nt use cache...!!!!!!!!!!!
		session.getDefaultContext().setCacheEnabled(false);
		return session;
	}
}
