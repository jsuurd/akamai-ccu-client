package org.suurd.akamai.ccu.client.mock;

import java.io.IOException;

import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;

public class ServerErrorResponseMockHttpTransport extends MockHttpTransport {

	public int lowLevelExecCalls;

	@Override
	public LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
		return new MockLowLevelHttpRequest() {
			@Override
			public LowLevelHttpResponse execute() throws IOException {
				lowLevelExecCalls++;
				MockLowLevelHttpResponse response = new MockLowLevelHttpResponse();
				response.setStatusCode(500);
				return response;
			}
		};
	}

}
