package co.gov.movilidadbogota.sipa.mongo.config;

import java.util.Arrays;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

import co.gov.movilidadbogota.sipa.common.ConfParameters;
import co.gov.movilidadbogota.sipa.common.Constants;

/**
 * Spring Mongodb configuration. Enable the mongodb repositories and scan the
 * model(Mongo documents) beans.
 * 
 * @author Hermes
 *
 */
@Configuration
@EnableMongoRepositories("co.gov.movilidadbogota.sipa.mongo.repository")
@ComponentScan(basePackages = { "co.gov.movilidadbogota.sipa.mongo.repository",
		"co.gov.movilidadbogota.sipa.mongo.model", "co.gov.movilidadbogota.sipa.mongo.service" })
public class MongoConfiguration extends AbstractMongoConfiguration {

	@Override
	protected String getDatabaseName() {
		return ConfParameters.getParm(Constants.CONF_MONGODB_DB);
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient(Arrays.asList(new ServerAddress(ConfParameters.getParm(Constants.CONF_MONGODB_HOST),
				Integer.parseInt(ConfParameters.getParm(Constants.CONF_MONGODB_PORT)))));
	}

}
