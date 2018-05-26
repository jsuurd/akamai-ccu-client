package org.suurd.akamai.ccu.client.model.v2;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.suurd.akamai.ccu.client.model.Constants;

import com.google.api.client.util.Key;

/**
 * Object representation of the Get Purge Status JSON request body.
 * 
 * @author jsuurd
 */
public class PurgeStatusRequest {

	@Key
	private String progressUri;

	/**
	 * Constructs a purge status.
	 */
	PurgeStatusRequest() {
		super();
	}

	/**
	 * Returns a new purge status request builder.
	 * 
	 * @return the purge status request builder
	 */
	public static PurgeStatusRequestBuilder builder() {
		return new PurgeStatusRequestBuilder();
	}

	/**
	 * Gets the progress URI to make Purge Status call.
	 * 
	 * @return the progress URI
	 */
	public String getProgressUri() {
		return progressUri;
	}

	/**
	 * Sets the progress URI to make Purge Status call.
	 * 
	 * @param progressUri the progress URI to set
	 */
	public void setProgressUri(String progressUri) {
		this.progressUri = progressUri;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, Constants.TO_STRING_STYLE);
	}

}
