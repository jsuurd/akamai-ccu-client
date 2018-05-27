package org.suurd.akamai.ccu.client.model.v2;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.Builder;

/**
 * Builder assisting in creating <code>PurgeStatusRequest</code> objects. This
 * builder validates if all the mandatory fields are set.
 * 
 * @author jsuurd
 */
public class PurgeStatusRequestBuilder implements Builder<PurgeStatusRequest> {

	private PurgeStatusRequest purgeStatusRequest;

	/**
	 * Constructs a purge status request builder.
	 */
	public PurgeStatusRequestBuilder() {
		purgeStatusRequest = new PurgeStatusRequest();
	}

	/**
	 * Sets the progress URI to make Purge Status call.
	 * 
	 * @param progressUri the progress URI to set
	 * @return this configuration builder instance
	 */
	public PurgeStatusRequestBuilder progressUri(String progressUri) {
		purgeStatusRequest.setProgressUri(progressUri);
		return this;
	}

	@Override
	public PurgeStatusRequest build() {
		Validate.notBlank(purgeStatusRequest.getProgressUri(), "progressUri cannot be blank");
		return purgeStatusRequest;
	}

}
