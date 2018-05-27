package org.suurd.akamai.ccu.client.provider;

import java.io.IOException;
import java.util.Properties;

import org.suurd.akamai.ccu.client.model.Configuration;
import org.suurd.akamai.ccu.client.model.ConfigurationBuilder;

/**
 * Class that loads the configuration from a properties file in the classpath of
 * the application. The configuration is loaded using lazy loading and is stored
 * as instance variable.
 * 
 * @author jsuurd
 */
public class FileConfigurationLoader {

	private static final String CONFIG_FILENAME = "/akamai-ccu-client.properties";

	private Configuration configuration;

	/**
	 * Returns the configuration loaded from a properties file.
	 * 
	 * @return the configuration
	 * @throws IOException if an error occurs loading the properties file 
	 */
	public Configuration getConfiguration() throws IOException {
		if (configuration == null) {
			Properties properties = new Properties();
			properties.load(this.getClass().getResourceAsStream(CONFIG_FILENAME));
			
			ConfigurationBuilder configurationBuilder = Configuration.builder()
					.baseAuthority(properties.getProperty("baseAuthority"))
					.accessToken(properties.getProperty("accessToken"))
					.clientToken(properties.getProperty("clientToken"))
					.clientSecret(properties.getProperty("clientSecret"))
					.queuesEndpoint(properties.getProperty("queuesEndpoint"));
			
			// Optional properties
			String connectTimeout = properties.getProperty("connectTimeout");
			if (connectTimeout != null) {
				configurationBuilder.connectTimeout(Integer.parseInt(connectTimeout));
			}
			String readTimeout = properties.getProperty("readTimeout");
			if (readTimeout != null) {
				configurationBuilder.readTimeout(Integer.parseInt(readTimeout));
			}
			String numberOfRetries = properties.getProperty("numberOfRetries");
			if (numberOfRetries != null) {
				configurationBuilder.numberOfRetries(Integer.parseInt(numberOfRetries));
			}
			
			configuration = configurationBuilder.build();
		}
		return configuration;
	}

}
