package org.suurd.akamai.ccu.client.provider;

import java.io.IOException;
import java.util.Properties;

/**
 * Singleton implementation that loads the properties from a properties file in
 * the classpath of the application
 * 
 * @author jsuurd
 */
public class ConfigurationPropertiesLoader {

	private static final String CONFIG_FILENAME = "/akamai-ccu-client.properties";

	// Bill Pugh Singleton implementation
	private static class SingletonHelper {
		private static final ConfigurationPropertiesLoader INSTANCE = new ConfigurationPropertiesLoader();
	}

	/**
	 * Gets the one and only instance of the configuration properties loader.
	 * 
	 * @return the configuration properties loader
	 */
	public static ConfigurationPropertiesLoader getInstance() {
		return SingletonHelper.INSTANCE;
	}

	private Properties properties;

	private ConfigurationPropertiesLoader() { }

	/**
	 * Gets the configuration properties.
	 * 
	 * @return the configuration properties
	 * @throws IOException
	 */
	public Properties getConfigurationProperties() throws IOException {
		if (properties == null) {
			properties = new Properties();
			properties.load(this.getClass().getResourceAsStream(CONFIG_FILENAME));
		}
		return properties;
	}

}
