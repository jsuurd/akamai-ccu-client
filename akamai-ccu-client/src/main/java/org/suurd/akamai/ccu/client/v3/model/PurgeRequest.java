package org.suurd.akamai.ccu.client.v3.model;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.suurd.akamai.ccu.client.model.Constants;

import com.google.api.client.util.Key;

/**
 * Object representation of the Add Purge Request JSON request body.
 * 
 * @author jsuurd
 */
public class PurgeRequest {

	private Action action;

	private Type type;

	private Network network;

	@Key
	private List<String> objects;

	/**
	 * Constructs a purge request.
	 */
	PurgeRequest() {
		super();
	}

	/**
	 * Returns a new purge request builder.
	 * 
	 * @return the purge request builder
	 */
	public static PurgeRequestBuilder builder() {
		return new PurgeRequestBuilder();
	}

	/**
	 * Gets the action to take.
	 * 
	 * @return the action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * Sets the action to take.
	 * 
	 * @param action the action to set
	 */
	public void setAction(Action action) {
		this.action = action;
	}

	/**
	 * Gets the type of the objects.
	 * 
	 * @return the type
	 */
	public Type getType() {
		return type;
	}

	/**
	 * Sets the type of the objects.
	 * 
	 * @param type the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * Gets the network to use.
	 * 
	 * @return the network
	 */
	public Network getNetwork() {
		return network;
	}

	/**
	 * Sets the network to use.
	 * 
	 * @param network the network to set
	 */
	public void setNetwork(Network network) {
		this.network = network;
	}

	/**
	 * Gets the list of URLs, ARLs, or CPCodes.
	 * 
	 * @return the list of objects
	 */
	public List<String> getObjects() {
		return objects;
	}

	/**
	 * Sets the list of URLs, ARLs, or CPCodes.
	 * 
	 * @param objects the list of objects to set
	 */
	public void setObjects(List<String> objects) {
		this.objects = objects;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, Constants.TO_STRING_STYLE);
	}

}
