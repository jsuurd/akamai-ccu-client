package org.suurd.akamai.ccu.client.model;

import com.google.api.client.util.NullValue;
import com.google.api.client.util.Value;

/**
 * Enumeration of the action to take, either remove (default) or invalidate.
 * 
 * @author jsuurd
 */
public enum Action {

	/**
	 * The invalidate action.
	 */
	@Value("invalidate")
	INVALIDATE,

	/**
	 * The remove action.
	 */
	@NullValue
	@Value("remove")
	REMOVE

}
