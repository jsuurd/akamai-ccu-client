package org.suurd.akamai.ccu.client.model.v2;

import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.Builder;

/**
 * Builder assisting in creating <code>PurgeRequest</code> objects. This builder
 * validates if all the mandatory fields are set.
 * 
 * @author jsuurd
 */
public class PurgeRequestBuilder implements Builder<PurgeRequest> {

	private PurgeRequest purgeRequest;

	/**
	 * Constructs a purge request builder.
	 */
	public PurgeRequestBuilder() {
		purgeRequest = new PurgeRequest();
	}

	/**
	 * Sets the domain to use.
	 * 
	 * @param domain the domain to set
	 * @return this configuration builder instance
	 */
	public PurgeRequestBuilder domain(Domain domain) {
		this.purgeRequest.setDomain(domain);
		return this;
	}

	/**
	 * Sets the action to take.
	 * 
	 * @param action the action to set
	 * @return this configuration builder instance
	 */
	public PurgeRequestBuilder action(Action action) {
		this.purgeRequest.setAction(action);
		return this;
	}

	/**
	 * Sets the type of the objects.
	 * 
	 * @param type the type to set
	 * @return this configuration builder instance
	 */
	public PurgeRequestBuilder type(Type type) {
		this.purgeRequest.setType(type);
		return this;
	}

	/**
	 * Sets the list of URLs, ARLs, or CPCodes.
	 * 
	 * @param objects the list of objects to set
	 * @return this configuration builder instance
	 */
	public PurgeRequestBuilder objects(List<String> objects) {
		this.purgeRequest.setObjects(objects);
		return this;
	}

	@Override
	public PurgeRequest build() {
		Validate.notNull(this.purgeRequest.getObjects(), "objects cannot be null");
		return purgeRequest;
	}

}
