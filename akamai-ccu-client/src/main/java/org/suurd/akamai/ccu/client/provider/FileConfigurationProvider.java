package org.suurd.akamai.ccu.client.provider;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suurd.akamai.ccu.client.CcuClientException;
import org.suurd.akamai.ccu.client.model.Configuration;

public class FileConfigurationProvider implements ConfigurationProvider {

	private static final Logger LOG = LoggerFactory.getLogger(FileConfigurationProvider.class);

	private Configuration configuration;

	@Override
	public Configuration getConfiguration() {
		if (configuration == null) {
			Properties configurationProperties = getConfigurationProperties();
			
			configuration = new Configuration(
					configurationProperties.getProperty("baseAuthority"),
					configurationProperties.getProperty("accessToken"),
					configurationProperties.getProperty("clientToken"),
					configurationProperties.getProperty("clientSecret"),
					configurationProperties.getProperty("queuesEndpoint"));
			
			// Optional properties
			String connectTimeout = configurationProperties.getProperty("connectTimeout");
			if (connectTimeout != null) {
				configuration.setConnectTimeout(Integer.parseInt(connectTimeout));
			}
			String readTimeout = configurationProperties.getProperty("readTimeout");
			if (readTimeout != null) {
				configuration.setReadTimeout(Integer.parseInt(readTimeout));
			}
			String numberOfRetries = configurationProperties.getProperty("numberOfRetries");
			if (numberOfRetries != null) {
				configuration.setNumberOfRetries(Integer.parseInt(numberOfRetries));
			}
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
