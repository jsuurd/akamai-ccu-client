package org.suurd.akamai.ccu.client.v3.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.suurd.akamai.ccu.client.model.AbstractResponse;
import org.suurd.akamai.ccu.client.model.Constants;

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
	private String purgeId;

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

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, Constants.TO_STRING_STYLE);
	}

}
