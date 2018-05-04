package org.suurd.akamai.ccu.client.model;

public class Configuration {

	private String baseAuthority;

	private String accessToken;

	private String clientToken;

	private String clientSecret;

	private String queuesEndpoint;

	private Integer connectTimeout;

	private Integer readTimeout;

	private Integer numberOfRetries;

	public Configuration(String baseAuthority, String accessToken, String clientToken, String clientSecret, String queuesEndpoint) {
		setBaseAuthority(baseAuthority);
		setAccessToken(accessToken);
		setClientToken(clientToken);
		setClientSecret(clientSecret);
		setQueuesEndpoint(queuesEndpoint);
	}

	@Override
	public String toString() {
		StringBuilder string = new StringBuilder();
		string.append("[baseAuthority=");
		string.append(getBaseAuthority());
		string.append(", accessToken=");
		string.append(getAccessToken());
		string.append(", clientToken=");
		string.append(getClientToken());
		string.append(", clientSecret=");
		string.append(getClientSecret() == null ? "" : "*****");
		string.append(", queuesEndpoint=");
		string.append(getQueuesEndpoint());
		string.append(", connectTimeout=");
		string.append(getConnectTimeout());
		string.append(", readTimeout=");
		string.append(getReadTimeout());
		string.append(", numberOfRetries=");
		string.append(getNumberOfRetries());
		string.append("]");
		return string.toString();
	}

	public String getBaseAuthority() {
		return baseAuthority;
	}

	public void setBaseAuthority(String baseAuthority) {
		this.baseAuthority = baseAuthority;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getClientToken() {
		return clientToken;
	}

	public void setClientToken(String clientToken) {
		this.clientToken = clientToken;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Integer getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(Integer connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public Integer getNumberOfRetries() {
		return numberOfRetries;
	}

	public void setNumberOfRetries(Integer numberOfRetries) {
		this.numberOfRetries = numberOfRetries;
	}

	public String getQueuesEndpoint() {
		return queuesEndpoint;
	}

	public void setQueuesEndpoint(String queuesEndpoint) {
		this.queuesEndpoint = queuesEndpoint;
	}

	public Integer getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(Integer readTimeout) {
		this.readTimeout = readTimeout;
	}

}
