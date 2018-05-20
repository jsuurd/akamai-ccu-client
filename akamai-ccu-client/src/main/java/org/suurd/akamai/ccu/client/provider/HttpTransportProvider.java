package org.suurd.akamai.ccu.client.provider;

import com.google.api.client.http.HttpTransport;

/**
 * Interface providing an implementation of {@link HttpTransport}. For
 * maximum efficiency, applications should use a single globally-shared instance
 * of the HTTP transport.
 * 
 * @author jsuurd
 */
public interface HttpTransportProvider {

	/**
	 * Gets the HTTP transport.
	 * 
	 * @return the HTTP transport
	 */
	HttpTransport getHttpTransport();

}
