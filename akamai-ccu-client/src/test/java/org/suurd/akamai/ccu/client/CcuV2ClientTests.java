package org.suurd.akamai.ccu.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NoHttpResponseException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.suurd.akamai.ccu.client.mock.FailThenSuccessResponseMockHttpTransport;
import org.suurd.akamai.ccu.client.mock.MockHttpTransportProvider;
import org.suurd.akamai.ccu.client.mock.NoHttpResponseMockHttpTransport;
import org.suurd.akamai.ccu.client.mock.ServerErrorResponseMockHttpTransport;
import org.suurd.akamai.ccu.client.mock.UnathorizedResponseMockHttpTransport;
import org.suurd.akamai.ccu.client.model.Action;
import org.suurd.akamai.ccu.client.model.Constants;
import org.suurd.akamai.ccu.client.model.Domain;
import org.suurd.akamai.ccu.client.model.PurgeRequest;
import org.suurd.akamai.ccu.client.model.PurgeResponse;
import org.suurd.akamai.ccu.client.model.PurgeStatus;
import org.suurd.akamai.ccu.client.model.PurgeStatusRequest;
import org.suurd.akamai.ccu.client.model.PurgeStatusResponse;
import org.suurd.akamai.ccu.client.model.QueueLengthResponse;
import org.suurd.akamai.ccu.client.model.Type;
import org.suurd.akamai.ccu.client.provider.ApacheHttpTransportProvider;

import com.google.api.client.http.HttpResponseException;
import com.google.api.client.testing.http.HttpTesting;

public class CcuV2ClientTests {
	
	private static final String TEST_PURGE_URL = "http://google.com";
	
	private static final String TEST_PURGE_CPCODE = "123456";
	
	private CcuV2Client ccuClient;
	
	@Before
	public void setUp() throws Exception {
		ccuClient = new CcuV2Client(new ApacheHttpTransportProvider());
	}

	@Test
	@Ignore("Integration Test")
	public void addPurgeRequest_WithInvalidateArls_ShouldReturnHttpStatusSuccess() {
		List<String> arls = new ArrayList<String>();
		arls.add(TEST_PURGE_URL);
		
		PurgeRequest purgeRequest = new PurgeRequest(arls);
		purgeRequest.setAction(Action.INVALIDATE);
		purgeRequest.setDomain(Domain.STAGING);
		purgeRequest.setType(Type.ARL);
		
		PurgeResponse purgeResponse = ccuClient.addPurgeRequest(purgeRequest);
		
		assertEquals(Constants.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
	}

	@Test
	@Ignore("Integration Test")
	public void addPurgeRequest_WithInvalidateCpCodes_ShouldReturnHttpStatusSuccess() {
		List<String> cpCodes = new ArrayList<String>();
		cpCodes.add(TEST_PURGE_CPCODE);
		
		PurgeRequest purgeRequest = new PurgeRequest(cpCodes);
		purgeRequest.setAction(Action.INVALIDATE);
		purgeRequest.setDomain(Domain.STAGING);
		purgeRequest.setType(Type.CPCODE);
		
		PurgeResponse purgeResponse = ccuClient.addPurgeRequest(purgeRequest);
		
		assertEquals(Constants.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
	}

	@Test
	@Ignore("Integration Test")
	public void addPurgeRequest_WithRemoveArls_ShouldReturnHttpStatusSuccess() {
		List<String> arls = new ArrayList<String>();
		arls.add(TEST_PURGE_URL);
		
		PurgeRequest purgeRequest = new PurgeRequest(arls);
		purgeRequest.setAction(Action.REMOVE);
		purgeRequest.setDomain(Domain.STAGING);
		purgeRequest.setType(Type.ARL);
		
		PurgeResponse purgeResponse = ccuClient.addPurgeRequest(purgeRequest);
		
		assertEquals(Constants.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
	}

	@Test
	@Ignore("Integration Test")
	public void addPurgeRequest_WithRemoveCpCode_ShouldReturnHttpStatusSuccess() {
		List<String> cpCodes = new ArrayList<String>();
		cpCodes.add(TEST_PURGE_CPCODE);
		
		PurgeRequest purgeRequest = new PurgeRequest(cpCodes);
		purgeRequest.setAction(Action.REMOVE);
		purgeRequest.setDomain(Domain.STAGING);
		purgeRequest.setType(Type.CPCODE);
		
		PurgeResponse purgeResponse = ccuClient.addPurgeRequest(purgeRequest);
		
		assertEquals(Constants.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
	}

	@Test
	public void addPurgeRequest_WithFailThenSuccessResponse_ShouldReturnsHttpStatusSuccess() {
		int callsBeforeSuccess = 3;
		
		List<String> arls = new ArrayList<String>();
		arls.add(HttpTesting.SIMPLE_URL);
		
		PurgeRequest purgeRequest = new PurgeRequest(arls);
		
		FailThenSuccessResponseMockHttpTransport mockTransport = new FailThenSuccessResponseMockHttpTransport(callsBeforeSuccess);
		CcuClient ccuClient = new CcuV2Client(new MockHttpTransportProvider(mockTransport));
		PurgeResponse purgeResponse = ccuClient.addPurgeRequest(purgeRequest);
		
		assertEquals(Constants.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
		// Total number of tries is initial request + callsBeforeSuccess 
		assertEquals(callsBeforeSuccess + 1, mockTransport.lowLevelExecCalls);
	}

	@Test
	public void addPurgeRequest_WithNoHttpResponse_ShouldFailAfterRetries() {
		int numberOfRetries = 3;
		
		List<String> arls = new ArrayList<String>();
		arls.add(HttpTesting.SIMPLE_URL);
		
		PurgeRequest purgeRequest = new PurgeRequest(arls);
		
		NoHttpResponseMockHttpTransport mockTransport = new NoHttpResponseMockHttpTransport();
		CcuClient ccuClient = new CcuV2Client(new MockHttpTransportProvider(mockTransport));
		try {
			ccuClient.addPurgeRequest(purgeRequest);
		} catch (CcuClientException e) {
			if (e.getCause() instanceof NoHttpResponseException) {
				// Total number of tries is initial request + numberOfRetries 
				assertEquals(numberOfRetries + 1, mockTransport.lowLevelExecCalls);
				// Test successful
				return;
			}
			fail(e.toString());
		}
	}

	@Test
	public void addPurgeRequest_WithServerErrorResponse_ShouldFailAfterRetries() {
		int numberOfRetries = 3;
		
		List<String> arls = new ArrayList<String>();
		arls.add(HttpTesting.SIMPLE_URL);
		
		PurgeRequest purgeRequest = new PurgeRequest(arls);
		
		ServerErrorResponseMockHttpTransport mockTransport = new ServerErrorResponseMockHttpTransport();
		CcuClient ccuClient = new CcuV2Client(new MockHttpTransportProvider(mockTransport));
		try {
			ccuClient.addPurgeRequest(purgeRequest);
			
		} catch (CcuClientException e) {
			if (e.getCause() instanceof HttpResponseException) {
				// Total number of tries is initial request + numberOfRetries 
				assertEquals(numberOfRetries + 1, mockTransport.lowLevelExecCalls);
				// Test successful
				return;
			}
			fail(e.toString());
		}
	}

	@Test
	public void addPurgeRequest_WithUnautorizedResponse_ShouldFailAfterRetries() {
		int numberOfRetries = 3;
		
		List<String> arls = new ArrayList<String>();
		arls.add(HttpTesting.SIMPLE_URL);
		
		PurgeRequest purgeRequest = new PurgeRequest(arls);
		
		UnathorizedResponseMockHttpTransport mockTransport = new UnathorizedResponseMockHttpTransport();
		CcuClient ccuClient = new CcuV2Client(new MockHttpTransportProvider(mockTransport));
		try {
			ccuClient.addPurgeRequest(purgeRequest);
		} catch (CcuClientException e) {
			if (e.getCause() instanceof HttpResponseException) {
				// Total number of tries is initial request + numberOfRetries 
				assertEquals(numberOfRetries + 1, mockTransport.lowLevelExecCalls);
				// Test successful
				return;
			}
			fail(e.toString());
		}
	}

	@Test
	@Ignore("Integration Test")
	public void getPurgeStatus_CheckStatusProgressUri_ReturnsHttpStatusSuccess() {
		List<String> arls = new ArrayList<String>();
		arls.add(TEST_PURGE_URL);
		
		PurgeRequest purgeRequest = new PurgeRequest(arls);
		purgeRequest.setAction(Action.INVALIDATE);
		purgeRequest.setDomain(Domain.STAGING);
		purgeRequest.setType(Type.ARL);
		
		PurgeResponse purgeResponse = ccuClient.addPurgeRequest(purgeRequest);
		
		PurgeStatusRequest statusRequest = new PurgeStatusRequest(purgeResponse.getProgressUri());
		
		PurgeStatusResponse statusResponse = ccuClient.getPurgeStatus(statusRequest);
		
		assertEquals(Constants.HTTP_STATUS_GET_SUCCESS, statusResponse.getHttpStatus());
		assertEquals(PurgeStatus.IN_PROGRESS, statusResponse.getPurgeStatus());
	}

	@Test
	@Ignore("Integration Test")
	public void getQueueLength_CheckStatusProgressUri_ReturnsHttpStatusSuccess() {
		QueueLengthResponse response = ccuClient.getQueueLength();
		
		assertEquals(Constants.HTTP_STATUS_GET_SUCCESS, response.getHttpStatus());
	}

}
