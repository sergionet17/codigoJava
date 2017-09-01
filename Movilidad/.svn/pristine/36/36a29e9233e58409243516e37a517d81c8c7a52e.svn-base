package co.gov.movilidadbogota.sipa.cmis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.gov.movilidadbogota.sipa.cmis.session.CmisSessionFactory;
import co.gov.movilidadbogota.sipa.cmis.type.CmisTypeFactory;
import co.gov.movilidadbogota.sipa.cmis.type.PropertiesTypeFactory;

/**
 * Configure the beans used from the cmis test application.
 * 
 * @author Hermes
 *
 */
@Configuration
public class ConfBeans {
	
	@Bean
	public CmisSessionFactory cmisSessionFactory(){
		return new CmisSessionFactory();
	}
	
	@Bean
	public CmisTypeFactory documentTypeFactory(){
		return new CmisTypeFactory();
	}	
	
	@Bean
	public PropertiesTypeFactory propertiesTypeFactory(){
		return new PropertiesTypeFactory();
	}
}
