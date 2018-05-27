package org.suurd.akamai.ccu.client.model.v2;

import com.google.api.client.util.NullValue;
import com.google.api.client.util.Value;

/**
 * Enumeration of the domain, either production (default) or staging.
 * 
 * @author jsuurd
 */
public enum Domain {

	/**
	 * The staging domain.
	 */
	@Value("staging")
	STAGING,

	/**
	 * The production domain.
	 */
	@NullValue
	@Value("production")
	PRODUCTION

}
