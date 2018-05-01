package org.suurd.akamai.ccu.client.mock;

import java.io.IOException;

import org.apache.http.NoHttpResponseException;

import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;

public class NoHttpResponseMockHttpTransport extends MockHttpTransport {

	public int lowLevelExecCalls;

	@Override
	public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
		return new MockLowLevelHttpRequest() {
			@Override
			public LowLevelHttpResponse execute() throws IOException {
				lowLevelExecCalls++;
				throw new NoHttpResponseException("Mock NoHttpResponseException");
			}
		};
	}
	
}
