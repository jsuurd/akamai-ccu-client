package org.suurd.akamai.ccu.client.facade;

import static org.junit.Assert.assertEquals;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.http.NoHttpResponseException;
import org.apache.http.conn.ConnectTimeoutException;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.suurd.akamai.ccu.client.CcuClientException;
import org.suurd.akamai.ccu.client.mock.AkamaiCcuMockHttpTransport;
import org.suurd.akamai.ccu.client.mock.ConnectTimeoutMockHttpTransport;
import org.suurd.akamai.ccu.client.mock.FailThenSuccessResponseMockHttpTransport;
import org.suurd.akamai.ccu.client.mock.MockConfigurationProvider;
import org.suurd.akamai.ccu.client.mock.MockHttpTransportProvider;
import org.suurd.akamai.ccu.client.mock.NoHttpResponseMockHttpTransport;
import org.suurd.akamai.ccu.client.mock.ReadTimeoutMockHttpTransport;
import org.suurd.akamai.ccu.client.mock.ServerErrorResponseMockHttpTransport;
import org.suurd.akamai.ccu.client.mock.UnathorizedResponseMockHttpTransport;
import org.suurd.akamai.ccu.client.model.Configuration;
import org.suurd.akamai.ccu.client.model.v2.Action;
import org.suurd.akamai.ccu.client.model.v2.Domain;
import org.suurd.akamai.ccu.client.model.v2.PurgeRequest;
import org.suurd.akamai.ccu.client.model.v2.PurgeResponse;
import org.suurd.akamai.ccu.client.model.v2.QueueLengthResponse;
import org.suurd.akamai.ccu.client.model.v2.Type;
import org.suurd.akamai.ccu.client.provider.ConfigurationProvider;
import org.suurd.akamai.ccu.client.provider.HttpTransportProvider;

import com.google.api.client.http.HttpResponseException;

public class GoogleHttpClientEdgeGridFacadeTests {

	private ConfigurationProvider configurationProvider;

	private EdgeGridFacade edgeGridFacade;

	private String testGetEndpoint;

	private String testPurgeUrl;

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() throws Exception {
		Properties testProperties = new Properties();
		testProperties.load(getClass().getResourceAsStream("/testing.properties"));
		
		this.configurationProvider = new ConfigurationProvider() {
			@Override
			public Configuration getConfiguration() {
				return Configuration.builder()
						.baseAuthority(testProperties.getProperty("baseAuthority"))
						.accessToken(testProperties.getProperty("accessToken"))
						.clientToken(testProperties.getProperty("clientToken"))
						.clientSecret(testProperties.getProperty("clientSecret"))
						.queuesEndpoint(testProperties.getProperty("queuesEndpoint"))
						.numberOfRetries(Integer.valueOf(testProperties.getProperty("numberOfRetries")))
						.build();
				
			}
		};
		
		// Switch to ApacheHttpTransportProvider for running test against Akamai CCU
		//HttpTransportProvider httpTransportProvider = new ApacheHttpTransportProvider();
		HttpTransportProvider httpTransportProvider = new MockHttpTransportProvider(new AkamaiCcuMockHttpTransport(1));
		this.edgeGridFacade = new GoogleHttpClientEdgeGridFacade(configurationProvider, httpTransportProvider);
		this.testGetEndpoint = testProperties.getProperty("queuesEndpoint") + "/default";
		this.testPurgeUrl = testProperties.getProperty("purgeUrl");
	}

	@Test
	public void sendGetRequest_WithValidRequest_ShouldReturnHttpStatusSuccess() {
		QueueLengthResponse queueLengthResponse = edgeGridFacade.submitGetRequest(testGetEndpoint, QueueLengthResponse.class);
		
		assertEquals(QueueLengthResponse.HTTP_STATUS_GET_SUCCESS, queueLengthResponse.getHttpStatus());
	}

	@Test
	public void sendGetRequest_WithConnectTimeout_ShouldThrowConnectTimeoutException() {
		HttpTransportProvider httpTransportProvider = new MockHttpTransportProvider(new ConnectTimeoutMockHttpTransport());
		EdgeGridFacade edgeGridFacade = new GoogleHttpClientEdgeGridFacade(configurationProvider, httpTransportProvider);
		
		thrown.expect(CcuClientException.class);
		thrown.expectCause(IsInstanceOf.instanceOf(ConnectTimeoutException.class));
		edgeGridFacade.submitGetRequest(testGetEndpoint, QueueLengthResponse.class);
	}

	@Test
	public void sendGetRequest_WithReadTimeout_ShouldThrowSocketTimeoutException() {
		HttpTransportProvider httpTransportProvider = new MockHttpTransportProvider(new ReadTimeoutMockHttpTransport());
		EdgeGridFacade edgeGridFacade = new GoogleHttpClientEdgeGridFacade(configurationProvider, httpTransportProvider);
		
		thrown.expect(CcuClientException.class);
		thrown.expectCause(IsInstanceOf.instanceOf(SocketTimeoutException.class));
		edgeGridFacade.submitGetRequest(testGetEndpoint, QueueLengthResponse.class);
	}

	@Test
	public void sendGetRequest_WithFailThenSuccessResponse_ShouldReturnsHttpStatusSuccess() {
		int callsBeforeSuccess = 3;
		
		Configuration configuration = configurationProvider.getConfiguration();
		configuration.setNumberOfRetries(callsBeforeSuccess);
		
		ConfigurationProvider mockConfigurationProvider = new MockConfigurationProvider(configuration);
		FailThenSuccessResponseMockHttpTransport mockTransport = new FailThenSuccessResponseMockHttpTransport(callsBeforeSuccess);
		EdgeGridFacade edgeGridFacade = new GoogleHttpClientEdgeGridFacade(mockConfigurationProvider, new MockHttpTransportProvider(mockTransport));
		
		QueueLengthResponse queueLengthResponse = edgeGridFacade.submitGetRequest(testGetEndpoint, QueueLengthResponse.class);
		
		assertEquals(QueueLengthResponse.HTTP_STATUS_GET_SUCCESS, queueLengthResponse.getHttpStatus());
		// Total number of tries is initial request + callsBeforeSuccess 
		assertEquals(callsBeforeSuccess + 1, mockTransport.lowLevelExecCalls);
	}

	@Test
	public void sendGetRequest_WithNoHttpResponse_ShouldFailAfterRetries() {
		int numberOfRetries = 3;
		
		Configuration configuration = configurationProvider.getConfiguration();
		configuration.setNumberOfRetries(numberOfRetries);
		
		ConfigurationProvider mockConfigurationProvider = new MockConfigurationProvider(configuration);
		NoHttpResponseMockHttpTransport mockTransport = new NoHttpResponseMockHttpTransport();
		EdgeGridFacade edgeGridProvider = new GoogleHttpClientEdgeGridFacade(mockConfigurationProvider, new MockHttpTransportProvider(mockTransport));
		
		thrown.expect(CcuClientException.class);
		thrown.expectCause(IsInstanceOf.instanceOf(NoHttpResponseException.class));
		edgeGridProvider.submitGetRequest(testGetEndpoint, QueueLengthResponse.class);
		
		// Total number of tries is initial request + numberOfRetries 
		assertEquals(numberOfRetries + 1, mockTransport.lowLevelExecCalls);
	}

	@Test
	public void sendGetRequest_WithServerErrorResponse_ShouldFailAfterRetries() {
		int numberOfRetries = 3;
		
		Configuration configuration = configurationProvider.getConfiguration();
		configuration.setNumberOfRetries(numberOfRetries);
		
		ConfigurationProvider mockConfigurationProvider = new MockConfigurationProvider(configuration);
		ServerErrorResponseMockHttpTransport mockTransport = new ServerErrorResponseMockHttpTransport();
		EdgeGridFacade edgeGridFacade = new GoogleHttpClientEdgeGridFacade(mockConfigurationProvider, new MockHttpTransportProvider(mockTransport));
		
		thrown.expect(CcuClientException.class);
		thrown.expectCause(IsInstanceOf.instanceOf(HttpResponseException.class));
		edgeGridFacade.submitGetRequest(testGetEndpoint, QueueLengthResponse.class);
		
		// Total number of tries is initial request + numberOfRetries 
		assertEquals(numberOfRetries + 1, mockTransport.lowLevelExecCalls);
	}

	@Test
	public void sendGetRequest_WithUnautorizedResponse_ShouldFailAfterRetries() {
		int numberOfRetries = 3;
		
		Configuration configuration = configurationProvider.getConfiguration();
		configuration.setNumberOfRetries(numberOfRetries);
		
		ConfigurationProvider mockConfigurationProvider = new MockConfigurationProvider(configuration);
		UnathorizedResponseMockHttpTransport mockTransport = new UnathorizedResponseMockHttpTransport();
		EdgeGridFacade edgeGridFacade = new GoogleHttpClientEdgeGridFacade(mockConfigurationProvider, new MockHttpTransportProvider(mockTransport));
		
		thrown.expect(CcuClientException.class);
		thrown.expectCause(IsInstanceOf.instanceOf(HttpResponseException.class));
		edgeGridFacade.submitGetRequest(testGetEndpoint, QueueLengthResponse.class);
		
		// Total number of tries is initial request + numberOfRetries 
		assertEquals(numberOfRetries + 1, mockTransport.lowLevelExecCalls);
	}

	@Test
	public void sendPostRequest_WithValidRequest_ShouldReturnHttpStatusSuccess() {
		List<String> arls = new ArrayList<>();
		arls.add(testPurgeUrl);
		
		PurgeRequest purgeRequest = PurgeRequest.builder()
				.action(Action.INVALIDATE)
				.type(Type.ARL)
				.domain(Domain.STAGING)
				.objects(arls)
				.build();
		
		PurgeResponse purgeResponse = edgeGridFacade.submitPostRequest(testGetEndpoint, purgeRequest, PurgeResponse.class);
		
		assertEquals(PurgeResponse.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
	}

}
