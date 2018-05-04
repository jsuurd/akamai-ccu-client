package org.suurd.akamai.ccu.client;

import static org.junit.Assert.*;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NoHttpResponseException;
import org.apache.http.conn.ConnectTimeoutException;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.suurd.akamai.ccu.client.mock.FailThenSuccessResponseMockHttpTransport;
import org.suurd.akamai.ccu.client.mock.MockConfigurationProvider;
import org.suurd.akamai.ccu.client.mock.MockHttpTransportProvider;
import org.suurd.akamai.ccu.client.mock.NoHttpResponseMockHttpTransport;
import org.suurd.akamai.ccu.client.mock.ServerErrorResponseMockHttpTransport;
import org.suurd.akamai.ccu.client.mock.UnathorizedResponseMockHttpTransport;
import org.suurd.akamai.ccu.client.model.Action;
import org.suurd.akamai.ccu.client.model.Configuration;
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
import org.suurd.akamai.ccu.client.provider.ConfigurationProvider;
import org.suurd.akamai.ccu.client.provider.HttpTransportProvider;

import com.google.api.client.http.HttpResponseException;
import com.google.api.client.testing.http.HttpTesting;

public class CcuV2ClientTests {

	private static final String TEST_BASE_AUTHORITY = "test-base-authority";

	private static final String TEST_ACCESS_TOKEN = "test-access-token";

	private static final String TEST_CLIENT_TOKEN = "test-client-token";

	private static final String TEST_CLIENT_SECRET = "test-client-cecret";

	private static final String TEST_PURGE_URL = "http://google.com";

	private static final String TEST_PURGE_CPCODE = "123456";

	private ConfigurationProvider configurationProvider;

	private HttpTransportProvider httpTransportProvider;

	@Before
	public void setUp() throws Exception {
		this.configurationProvider = new ConfigurationProvider() {
			
			@Override
			public Configuration getConfiguration() {
				Configuration configuration = new Configuration(
						TEST_BASE_AUTHORITY,
						TEST_ACCESS_TOKEN,
						TEST_CLIENT_TOKEN,
						TEST_CLIENT_SECRET,
						"/ccu/v2/queues/");
				configuration.setConnectTimeout(5000);
				configuration.setReadTimeout(10000);
				configuration.setNumberOfRetries(3);
				return configuration;
			}
		};
		this.httpTransportProvider = new ApacheHttpTransportProvider();
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
		
		CcuClient ccuClient = new CcuV2Client(configurationProvider, httpTransportProvider);
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
		
		CcuClient ccuClient = new CcuV2Client(configurationProvider, httpTransportProvider);
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
		
		CcuClient ccuClient = new CcuV2Client(configurationProvider, httpTransportProvider);
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
		
		CcuClient ccuClient = new CcuV2Client(configurationProvider, httpTransportProvider);
		PurgeResponse purgeResponse = ccuClient.addPurgeRequest(purgeRequest);
		
		assertEquals(Constants.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
	}

	@Test
	@Ignore("Integration Test")
	public void addPurgeRequest_WithConnectTimeout_ShouldThrowConnectTimeoutException() {
		List<String> arls = new ArrayList<String>();
		arls.add(HttpTesting.SIMPLE_URL);
		
		PurgeRequest purgeRequest = new PurgeRequest(arls);
		purgeRequest.setAction(Action.INVALIDATE);
		purgeRequest.setDomain(Domain.STAGING);
		purgeRequest.setType(Type.ARL);
		
		Configuration configuration = configurationProvider.getConfiguration();
		configuration.setConnectTimeout(1);
		configuration.setNumberOfRetries(1);
		
		CcuClient ccuClient = new CcuV2Client(new MockConfigurationProvider(configuration), httpTransportProvider);
		try{
			ccuClient.addPurgeRequest(purgeRequest);
		} catch (CcuClientException e) {
			if (e.getCause() instanceof ConnectTimeoutException) {
				// Test successful
				return;
			}
			fail(e.toString());
		}
	}

	@Test
	@Ignore("Integration Test")
	public void addPurgeRequest_WithReadTimeout_ShouldThrowSocketTimeoutException() {
		List<String> arls = new ArrayList<String>();
		arls.add("http://11-id-en.test.starbucks.com/jeroen.html");
		
		PurgeRequest purgeRequest = new PurgeRequest(arls);
		purgeRequest.setAction(Action.INVALIDATE);
		purgeRequest.setDomain(Domain.STAGING);
		purgeRequest.setType(Type.ARL);
		
		Configuration configuration = configurationProvider.getConfiguration();
		configuration.setReadTimeout(1000);
		configuration.setNumberOfRetries(1);
		
		CcuClient ccuClient = new CcuV2Client(new MockConfigurationProvider(configuration), httpTransportProvider);
		try {
			ccuClient.addPurgeRequest(purgeRequest);
		} catch (CcuClientException e) {
			if (e.getCause() instanceof SocketTimeoutException) {
				// Test successful
				return;
			}
			fail(e.toString());
		} catch (Exception e) {
			fail(e.toString());
		}
	}

	@Test
	public void addPurgeRequest_WithFailThenSuccessResponse_ShouldReturnsHttpStatusSuccess() {
		int callsBeforeSuccess = 3;
		
		List<String> arls = new ArrayList<String>();
		arls.add(HttpTesting.SIMPLE_URL);
		
		PurgeRequest purgeRequest = new PurgeRequest(arls);
		
		FailThenSuccessResponseMockHttpTransport mockTransport = new FailThenSuccessResponseMockHttpTransport(callsBeforeSuccess);
		CcuClient ccuClient = new CcuV2Client(configurationProvider, new MockHttpTransportProvider(mockTransport));
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
		CcuClient ccuClient = new CcuV2Client(configurationProvider, new MockHttpTransportProvider(mockTransport));
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
		CcuClient ccuClient = new CcuV2Client(configurationProvider, new MockHttpTransportProvider(mockTransport));
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
		CcuClient ccuClient = new CcuV2Client(configurationProvider, new MockHttpTransportProvider(mockTransport));
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
		
		CcuClient ccuClient = new CcuV2Client(configurationProvider, httpTransportProvider);
		PurgeResponse purgeResponse = ccuClient.addPurgeRequest(purgeRequest);
		
		PurgeStatusRequest statusRequest = new PurgeStatusRequest(purgeResponse.getProgressUri());
		
		PurgeStatusResponse statusResponse = ccuClient.getPurgeStatus(statusRequest);
		
		assertEquals(Constants.HTTP_STATUS_GET_SUCCESS, statusResponse.getHttpStatus());
		assertEquals(PurgeStatus.IN_PROGRESS, statusResponse.getPurgeStatus());
	}

	@Test
	@Ignore("Integration Test")
	public void getQueueLength_CheckStatusProgressUri_ReturnsHttpStatusSuccess() {
		CcuClient ccuClient = new CcuV2Client(configurationProvider, httpTransportProvider);
		QueueLengthResponse response = ccuClient.getQueueLength();
		
		assertEquals(Constants.HTTP_STATUS_GET_SUCCESS, response.getHttpStatus());
	}

}