package org.suurd.akamai.ccu.client.provider;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;

/**
 * Implementation of HTTP transport provider that returns a single
 * globally-shared instance of {@link HttpTransport}}.
 * 
 * @author jsuurd
 */
public class ApacheHttpTransportProvider implements HttpTransportProvider {

	private static final HttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();

	@Override
	public HttpTransport getHttpTransport() {
		return HTTP_TRANSPORT;
	}

}
