package org.suurd.akamai.ccu.client.mock;

import org.suurd.akamai.ccu.client.provider.HttpTransportProvider;

import com.google.api.client.http.HttpTransport;

public class MockHttpTransportProvider implements HttpTransportProvider {

	private HttpTransport httpTransport;

	public MockHttpTransportProvider(HttpTransport httpTransport) {
		this.httpTransport = httpTransport;
	}

	public HttpTransport getHttpTransport() {
		return httpTransport;
	}

}
