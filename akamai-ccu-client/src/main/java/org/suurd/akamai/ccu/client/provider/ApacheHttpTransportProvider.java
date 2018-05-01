package org.suurd.akamai.ccu.client.provider;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.apache.ApacheHttpTransport;

public class ApacheHttpTransportProvider implements HttpTransportProvider {

	private static final HttpTransport HTTP_TRANSPORT = new ApacheHttpTransport();

	@Override
	public HttpTransport getHttpTransport() {
		return HTTP_TRANSPORT;
	}

}
