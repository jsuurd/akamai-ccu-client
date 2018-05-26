package org.suurd.akamai.ccu.client.facade;

/**
 * Facade to the Akamai OPEN EdgeGrid Client library.
 * 
 * @author jsuurd
 */
public interface EdgeGridFacade {

	/**
	 * Submit a Get request. Returns a response object parsed from a JSON
	 * response.
	 * 
	 * @param <T> the type of response object returned
	 * @param path the request path
	 * @param responseClass the response class
	 * @return the response object
	 */
	<T> T submitGetRequest(String path, Class<T> responseClass);

	/**
	 * Submit a Post request with the data object serialized to JSON content.
	 * Returns a response object parsed from a JSON response
	 * 
	 * @param <T> the type of response object returned
	 * @param path the request path
	 * @param data the request data
	 * @param responseClass the response class
	 * @return the response object
	 */
	<T> T submitPostRequest(String path, Object data, Class<T> responseClass);

}