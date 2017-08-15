package co.gov.movilidadbogota.sipa.extract.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.gov.movilidadbogota.sipa.extract.service.TextExtractor;

/**
 * Configure the beans used from the cmis test application.
 * 
 * @author Hermes
 *
 */
@Configuration
public class ConfBeans {
	
	@Bean
	public TextExtractor textExtractor(){
		return new TextExtractor();
	}
}
