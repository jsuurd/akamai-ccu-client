package org.suurd.akamai.ccu.client.model;

import com.google.api.client.util.Key;

/**
 * Object representation of the Add Purge Request JSON response content.
 * 
 * @author jsuurd
 */
public class PurgeResponse extends AbstractResponse {

	@Key
	private int estimatedSeconds;

	@Key
	private String progressUri;

	@Key
	private String purgeId;

	@Key
	private int pingAfterSeconds;

	/**
	 * Gets the minimum amount of time to wait before calling Purge Status.
	 * 
	 * @return the estimated seconds
	 */
	public int getEstimatedSeconds() {
		return estimatedSeconds;
	}

	/**
	 * Sets the minimum amount of time to wait before calling Purge Status.
	 * 
	 * @param estimatedSeconds the estimated seconds to set
	 */
	public void setEstimatedSeconds(int estimatedSeconds) {
		this.estimatedSeconds = estimatedSeconds;
	}

	/**
	 * Gets the progress URI to make Purge Status calls.
	 * 
	 * @return the progress URI
	 */
	public String getProgressUri() {
		return progressUri;
	}

	/**
	 * Sets the progress URI to make Purge Status calls.
	 * 
	 * @param progressUri the progress URI to set
	 */
	public void setProgressUri(String progressUri) {
		this.progressUri = progressUri;
	}

	/**
	 * Gets the unique ID for the purge request.
	 * 
	 * @return the purgeId
	 */
	public String getPurgeId() {
		return purgeId;
	}

	/**
	 * Sets the unique ID for the purge request.
	 * 
	 * @param purgeId the purgeId to set
	 */
	public void setPurgeId(String purgeId) {
		this.purgeId = purgeId;
	}

	/**
	 * Sets the minimum amount of time to wait before calling Purge Status.
	 * 
	 * @return the ping after seconds
	 */
	public int getPingAfterSeconds() {
		return pingAfterSeconds;
	}

	/**
	 * Gets the minimum amount of time to wait before calling Purge Status.
	 * 
	 * @param pingAfterSeconds the ping after seconds to set
	 */
	public void setPingAfterSeconds(int pingAfterSeconds) {
		this.pingAfterSeconds = pingAfterSeconds;
	}

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

}
