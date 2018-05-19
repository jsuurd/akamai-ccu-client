package org.suurd.akamai.ccu.client.provider;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suurd.akamai.ccu.client.CcuClientException;
import org.suurd.akamai.ccu.client.model.Configuration;
import org.suurd.akamai.ccu.client.model.ConfigurationBuilder;

/**
 * Implementation of configuration provider that provides the configuration
 * populated from a properties file in the classpath of the application.
 * 
 * @author jsuurd
 */
public class FileConfigurationProvider implements ConfigurationProvider {

	private static final Logger LOG = LoggerFactory.getLogger(FileConfigurationProvider.class);

	private Configuration configuration;

	@Override
	public Configuration getConfiguration() {
		if (configuration == null) {
			Properties configurationProperties = getConfigurationProperties();

			ConfigurationBuilder configurationBuilder = Configuration.builder()
					.baseAuthority(configurationProperties.getProperty("baseAuthority"))
					.accessToken(configurationProperties.getProperty("accessToken"))
					.clientToken(configurationProperties.getProperty("clientToken"))
					.clientSecret(configurationProperties.getProperty("clientSecret"))
					.queuesEndpoint(configurationProperties.getProperty("queuesEndpoint"));

			// Optional properties
			String connectTimeout = configurationProperties.getProperty("connectTimeout");
			if (connectTimeout != null) {
				configurationBuilder.connectTimeout(Integer.parseInt(connectTimeout));
			}
			String readTimeout = configurationProperties.getProperty("readTimeout");
			if (readTimeout != null) {
				configurationBuilder.readTimeout(Integer.parseInt(readTimeout));
			}
			String numberOfRetries = configurationProperties.getProperty("numberOfRetries");
			if (numberOfRetries != null) {
				configurationBuilder.numberOfRetries(Integer.parseInt(numberOfRetries));
			}

			configuration = configurationBuilder.build();
		}
		return configuration;
	}

	private Properties getConfigurationProperties() {
		Properties configurationProperties;
		try {
			configurationProperties = ConfigurationPropertiesLoader.getInstance().getConfigurationProperties();
		} catch (IOException e) {
			String message = "Error loading configuration";
			LOG.error(message, e);
			throw new CcuClientException(message, e);
		}
		return configurationProperties;
	}

}
