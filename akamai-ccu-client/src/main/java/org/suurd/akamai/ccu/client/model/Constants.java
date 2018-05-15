package org.suurd.akamai.ccu.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Constants used by the CCU client.
 * 
 * @author jsuurd
 */
public interface Constants {

	/**
	 * Media type for JSON message content.
	 */
	String CONTENT_TYPE_JSON = "application/json";

	/**
	 * HTTP status code for a successful GET request.
	 */
	int HTTP_STATUS_GET_SUCCESS = 200;

	/**
	 * HTTP status code for a successful POST request.
	 */
	int HTTP_STATUS_POST_SUCCESS = 201;

	/**
	 * Maximum size of the request content supported by CCU.
	 */
	int REQUEST_MAX_CONTENT_SIZE = 50000;

	/**
	 * Style of the output of {@link ToStringBuilder} used in ToString method of models.
	 */
	ToStringStyle TO_STRING_STYLE = ToStringStyle.NO_CLASS_NAME_STYLE;

}
