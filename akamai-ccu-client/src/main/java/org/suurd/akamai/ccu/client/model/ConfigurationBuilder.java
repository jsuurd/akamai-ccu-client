package org.suurd.akamai.ccu.client.model;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.Builder;

/**
 * Builder assisting in creating <code>Configuration</code> objects. This builder
 * validates if all the mandatory fields are set.
 * 
 * @author jsuurd
 */
public class ConfigurationBuilder implements Builder<Configuration> {

	private Configuration configuration;

	/**
	 * Constructs a new configuration builder.
	 */
	public ConfigurationBuilder() {
		configuration = new Configuration();
	}

	/**
	 * Sets the Akamai client credential base authority.
	 * 
	 * @param baseAuthority the base authority to set
	 * @return this configuration builder instance
	 */
	public ConfigurationBuilder baseAuthority(String baseAuthority) {
		configuration.setBaseAuthority(baseAuthority);
		return this;
	}

	/**
	 * Sets the Akamai client credential access token.
	 * 
	 * @param accessToken the access token to set
	 * @return this configuration builder instance
	 */
	public ConfigurationBuilder accessToken(String accessToken) {
		configuration.setAccessToken(accessToken);
		return this;
	}

	/**
	 * Sets the Akamai client credential client token.
	 * 
	 * @param clientToken the client token to set
	 * @return this configuration builder instance
	 */
	public ConfigurationBuilder clientToken(String clientToken) {
		configuration.setClientToken(clientToken);
		return this;
	}

	/**
	 * Sets the Akamai client credential client secret token.
	 * 
	 * @param clientSecret the client secret to set
	 * @return this configuration builder instance
	 */
	public ConfigurationBuilder clientSecret(String clientSecret) {
		configuration.setClientSecret(clientSecret);
		return this;
	}

	/**
	 * Sets the Akamai CCU API queues endpoint.
	 * 
	 * @param queuesEndpoint the queues endpoint to set
	 * @return this configuration builder instance
	 */
	public ConfigurationBuilder queuesEndpoint(String queuesEndpoint) {
		configuration.setQueuesEndpoint(queuesEndpoint);
		return this;
	}

	/**
	 * Sets the timeout in milliseconds to establish a HTTP connection.
	 * 
	 * @param connectTimeout the connect timeout to set
	 * @return this configuration builder instance
	 */
	public ConfigurationBuilder connectTimeout(Integer connectTimeout) {
		configuration.setConnectTimeout(connectTimeout);
		return this;
	}

	/**
	 * Sets the timeout in milliseconds to read data from an established connection.
	 * 
	 * @param readTimeout the read timeout to set
	 * @return this configuration builder instance
	 */
	public ConfigurationBuilder readTimeout(Integer readTimeout) {
		configuration.setReadTimeout(readTimeout);
		return this;
	}

	/**
	 * Sets the number of retries before a HTTP request will be terminated.
	 * 
	 * @param numberOfRetries the number of retries to set
	 * @return this configuration builder instance
	 */
	public ConfigurationBuilder numberOfRetries(Integer numberOfRetries) {
		configuration.setNumberOfRetries(numberOfRetries);
		return this;
	}

	@Override
	public Configuration build() {
		Validate.notBlank(configuration.getBaseAuthority(), "baseAuthority cannot be blank");
		Validate.notBlank(configuration.getAccessToken(), "accessToken cannot be blank");
		Validate.notBlank(configuration.getClientToken(), "clientToken cannot be blank");
		Validate.notBlank(configuration.getClientSecret(), "clientSecret cannot be blank");
		Validate.notBlank(configuration.getQueuesEndpoint(), "queuesEndpoint cannot be blank");
		return configuration;
	}

}
