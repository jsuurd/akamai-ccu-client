package org.suurd.akamai.ccu.client.mock;

import java.io.IOException;

import org.apache.http.NoHttpResponseException;

import com.google.api.client.http.LowLevelHttpRequest;
import com.google.api.client.http.LowLevelHttpResponse;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.testing.http.MockLowLevelHttpRequest;
import com.google.api.client.testing.http.MockLowLevelHttpResponse;

public class FailThenSuccessResponseMockHttpTransport extends MockHttpTransport {

	public int lowLevelExecCalls;

	private int callsBeforeSuccess;

	public FailThenSuccessResponseMockHttpTransport(int callsBeforeSuccess) {
		this.callsBeforeSuccess = callsBeforeSuccess;
	}

	@Override
	public LowLevelHttpRequest buildRequest(String method, String url) {
		return new MockLowLevelHttpRequest() {
			@Override
			public LowLevelHttpResponse execute() throws IOException {
				lowLevelExecCalls++;
				if (lowLevelExecCalls <= callsBeforeSuccess) {
					throw new NoHttpResponseException("Mock NoHttpResponseException");
				}
				// Return success when count is more than callsBeforeSuccess
				MockLowLevelHttpResponse response = new MockLowLevelHttpResponse();
				response.setStatusCode(200);
				response.setContent("{ \"httpStatus\": 201, \"detail\": \"Request accepted.\" }");
				return response;
			}
		};
	}

}
