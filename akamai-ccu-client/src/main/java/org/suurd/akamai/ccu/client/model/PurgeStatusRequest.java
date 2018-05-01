package org.suurd.akamai.ccu.client.model;

import com.google.api.client.util.Key;

public class PurgeStatusRequest {

	@Key
	private String progressUri;

	public PurgeStatusRequest(String progressUri) {
		setProgressUri(progressUri);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[progressUri=");
		sb.append(getProgressUri());
		sb.append("]");
		return sb.toString();
	}

	public String getProgressUri() {
		return progressUri;
	}

	public void setProgressUri(String progressUri) {
		this.progressUri = progressUri;
	}	

}
