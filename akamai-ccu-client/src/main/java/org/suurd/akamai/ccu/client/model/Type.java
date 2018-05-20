package org.suurd.akamai.ccu.client.model;

import com.google.api.client.util.NullValue;
import com.google.api.client.util.Value;

/**
 * Enumeration of the type of the objects, either arl (default) or cpcode.
 * 
 * @author jsuurd
 */
public enum Type {

	/**
	 * The arl object type.
	 */
	@NullValue
	@Value("arl")
	ARL,

	/**
	 * The cpcode object type.
	 */
	@Value("cpcode")
	CPCODE

}
