package org.suurd.akamai.ccu.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class containing configuration properties used by the CCU client.
 *  
 * @author jsuurd
 */
public class Configuration {

	private String baseAuthority;

	private String accessToken;

	private String clientToken;

	private String clientSecret;

	private String queuesEndpoint;

	private Integer connectTimeout;

	private Integer readTimeout;

	private Integer numberOfRetries;

	/**
	 * Constructs a new configuration.
	 */
	Configuration() {
		super();
	}

	/**
	 * Returns a new configuration builder.
	 * 
	 * @return the configuration builder
	 */
	public static ConfigurationBuilder builder() {
		return new ConfigurationBuilder();
	}

	/**
	 * Gets the Akamai client credential base authority.
	 * 
	 * @return the base authority
	 */
	public String getBaseAuthority() {
		return baseAuthority;
	}

	/**
	 * Sets the Akamai client credential base authority.
	 * 
	 * @param baseAuthority the base authority to set
	 */
	public void setBaseAuthority(String baseAuthority) {
		this.baseAuthority = baseAuthority;
	}

	/**
	 * Gets the Akamai client credential access token.
	 * 
	 * @return the access token
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Sets the Akamai client credential access token.
	 * 
	 * @param accessToken the access token to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * Gets the Akamai client credential client token.
	 * 
	 * @return the client token
	 */
	public String getClientToken() {
		return clientToken;
	}

	/**
	 * Sets the Akamai client credential client token.
	 * 
	 * @param clientToken the client token to set
	 */
	public void setClientToken(String clientToken) {
		this.clientToken = clientToken;
	}

	/**
	 * Gets the Akamai client credential client secret token.
	 * 
	 * @return the client secret
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * Sets the Akamai client credential client secret token.
	 * 
	 * @param clientSecret the client secret to set
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	/**
	 * Gets the Akamai CCU API queues endpoint.
	 * 
	 * @return the queues endpoint
	 */
	public String getQueuesEndpoint() {
		return queuesEndpoint;
	}

	/**
	 * Sets the Akamai CCU API queues endpoint.
	 * 
	 * @param queuesEndpoint the queues endpoint to set
	 */
	public void setQueuesEndpoint(String queuesEndpoint) {
		this.queuesEndpoint = queuesEndpoint;
	}

	/**
	 * Gets the timeout in milliseconds to establish a HTTP connection.
	 * 
	 * @return the connect timeout
	 */
	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * Sets the timeout in milliseconds to establish a HTTP connection.
	 * 
	 * @param connectTimeout the connect timeout to set
	 */
	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	/**
	 * Gets the timeout in milliseconds to read data from an established connection.
	 * 
	 * @return the read timeout
	 */
	public Integer getReadTimeout() {
		return readTimeout;
	}

	/**
	 * Sets the timeout in milliseconds to read data from an established connection.
	 * 
	 * @param readTimeout the read timeout to set
	 */
	public void setReadTimeout(Integer readTimeout) {
		this.readTimeout = readTimeout;
	}

	/**
	 * Gets the number of retries before a HTTP request will be terminated.
	 * 
	 * @return the number of retries
	 */
	public Integer getNumberOfRetries() {
		return numberOfRetries;
	}

	/**
	 * Sets the number of retries before a HTTP request will be terminated.
	 * 
	 * @param numberOfRetries the number of retries to set
	 */
	public void setNumberOfRetries(Integer numberOfRetries) {
		this.numberOfRetries = numberOfRetries;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this, Constants.TO_STRING_STYLE)
				.append("baseAuthority", getBaseAuthority())
				.append("accessToken", getAccessToken())
				.append("clientToken", getClientToken())
				.append("clientSecret", getClientSecret() != null ? "*****" : null)
				.append("queuesEndpoint", getQueuesEndpoint())
				.append("connectTimeout", getConnectTimeout())
				.append("readTimeout", getReadTimeout())
				.append("numberOfRetries", getNumberOfRetries())
				.build();
	}

}
