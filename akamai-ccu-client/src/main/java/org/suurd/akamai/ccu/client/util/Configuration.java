package org.suurd.akamai.ccu.client.util;

import java.io.IOException;
import java.util.Properties;

public class Configuration {

	private static final String CONFIG_FILENAME = "/akamai-ccu-client.properties";

	// Bill Pugh Singleton implementation
	private static class SingletonHelper {
		private static final Configuration INSTANCE = new Configuration();
	}

	public static Configuration getInstance() {
		return SingletonHelper.INSTANCE;
	}

	private Properties properties;

	private Configuration() {
		try {
			properties = new Properties();
			properties.load(getClass().getResourceAsStream(CONFIG_FILENAME));
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public String getBaseAuthority() {
		return properties.getProperty("baseAuthority");
	}

	public String getAccessToken() {
		return properties.getProperty("accessToken");
	}

	public String getClientToken() {
		return properties.getProperty("clientToken");
	}

	public String getClientSecret() {
		return properties.getProperty("clientSecret");
	}

	public Integer getConnectTimeout() {
		String connectTimeout = properties.getProperty("connectTimeout");
		return connectTimeout != null ? Integer.parseInt(properties.getProperty("connectTimeout")) : null;
	}

	public Integer getNumberOfRetries() {
		String numberOfRetries = properties.getProperty("numberOfRetries");
		return numberOfRetries != null ? Integer.parseInt(properties.getProperty("numberOfRetries")) : null;
	}

	public String getQueuesEndpoint() {
		return properties.getProperty("queuesEndpoint");
	}

	public Integer getReadTimeout() {
		String readTimeout = properties.getProperty("readTimeout");
		return readTimeout != null ? Integer.parseInt(properties.getProperty("readTimeout")) : null;
	}

}
