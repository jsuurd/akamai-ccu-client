package org.suurd.akamai.ccu.client.model;

import com.google.api.client.util.Key;

public class PurgeResponse extends AbstractResponse {

	@Key
	private int estimatedSeconds;

	@Key
	private String progressUri;

	@Key
	private String purgeId;

	@Key
	private int pingAfterSeconds;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[estimatedSeconds=");
		sb.append(getEstimatedSeconds());
		sb.append(", progressUri=");
		sb.append(getProgressUri());
		sb.append(", purgeId=");
		sb.append(getPurgeId());
		sb.append(", pingAfterSeconds=");
		sb.append(getPingAfterSeconds());
		sb.append(" httpStatus=");
		sb.append(getHttpStatus());
		sb.append(" detail=");
		sb.append(getDetail());
		sb.append(", supportId=");
		sb.append(getSupportId());
		sb.append("]");
		return sb.toString();
	}

	public int getEstimatedSeconds() {
		return estimatedSeconds;
	}

	public void setEstimatedSeconds(int estimatedSeconds) {
		this.estimatedSeconds = estimatedSeconds;
	}

	public String getProgressUri() {
		return progressUri;
	}

	public void setProgressUri(String progressUri) {
		this.progressUri = progressUri;
	}

	public String getPurgeId() {
		return purgeId;
	}

	public void setPurgeId(String purgeId) {
		this.purgeId = purgeId;
	}

	public int getPingAfterSeconds() {
		return pingAfterSeconds;
	}

	public void setPingAfterSeconds(int pingAfterSeconds) {
		this.pingAfterSeconds = pingAfterSeconds;
	}

}
