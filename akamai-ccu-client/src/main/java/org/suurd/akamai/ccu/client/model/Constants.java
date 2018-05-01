package org.suurd.akamai.ccu.client.model;

public interface Constants {

	int HTTP_STATUS_GET_SUCCESS = 200;

	int HTTP_STATUS_POST_SUCCESS = 201;

	String CONTENT_TYPE_JSON = "application/json";

	/**
	 * Maximum size of the request content supported by CCU is 50,000 bytes.
	 */
	int REQUEST_MAX_CONTENT_SIZE = 50000;

}
