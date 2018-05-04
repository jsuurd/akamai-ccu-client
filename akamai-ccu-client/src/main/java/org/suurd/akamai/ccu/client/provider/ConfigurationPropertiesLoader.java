package org.suurd.akamai.ccu.client.provider;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationPropertiesLoader {

	private static final String CONFIG_FILENAME = "/akamai-ccu-client.properties";

	// Bill Pugh Singleton implementation
	private static class SingletonHelper {
		private static final ConfigurationPropertiesLoader INSTANCE = new ConfigurationPropertiesLoader();
	}

	public static ConfigurationPropertiesLoader getInstance() {
		return SingletonHelper.INSTANCE;
	}

	private Properties properties;

	private ConfigurationPropertiesLoader() { }

	public Properties getConfigurationProperties() throws IOException {
		if (properties == null) {
			properties = new Properties();
			properties.load(this.getClass().getResourceAsStream(CONFIG_FILENAME));
		}
		return properties;
	}

}
