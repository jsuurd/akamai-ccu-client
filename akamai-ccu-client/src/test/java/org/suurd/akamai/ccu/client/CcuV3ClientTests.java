package org.suurd.akamai.ccu.client;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.suurd.akamai.ccu.client.facade.EdgeGridFacade;
import org.suurd.akamai.ccu.client.facade.GoogleHttpClientEdgeGridFacade;
import org.suurd.akamai.ccu.client.mock.AkamaiCcuMockHttpTransport;
import org.suurd.akamai.ccu.client.mock.MockHttpTransportProvider;
import org.suurd.akamai.ccu.client.model.Configuration;
import org.suurd.akamai.ccu.client.model.v3.Action;
import org.suurd.akamai.ccu.client.model.v3.Network;
import org.suurd.akamai.ccu.client.model.v3.PurgeRequest;
import org.suurd.akamai.ccu.client.model.v3.PurgeResponse;
import org.suurd.akamai.ccu.client.model.v3.Type;
import org.suurd.akamai.ccu.client.provider.ConfigurationProvider;
import org.suurd.akamai.ccu.client.provider.HttpTransportProvider;

public class CcuV3ClientTests {

	private ConfigurationProvider configurationProvider;

	private EdgeGridFacade edgeGridFacade;

	private String testPurgeUrl;

	private String testPurgeCpcode;

	private String testPurgeTag;

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
		this.testPurgeCpcode = testProperties.getProperty("purgeCpcode");
		this.testPurgeUrl = testProperties.getProperty("purgeUrl");
		this.testPurgeTag = testProperties.getProperty("purgeTag");
	}

	@Test
	public void addPurgeRequest_WithDeleteUrls_ShouldReturnHttpStatusSuccess() {
		List<String> urls = new ArrayList<>();
		urls.add(testPurgeUrl);
		
		PurgeRequest purgeRequest = PurgeRequest.builder()
				.action(Action.DELETE)
				.type(Type.URL)
				.network(Network.STAGING)
				.objects(urls)
				.build();
		
		FastPurgeClient client = new CcuV3Client(configurationProvider, edgeGridFacade);
		PurgeResponse purgeResponse = client.addPurgeRequest(purgeRequest);
		
		assertEquals(PurgeResponse.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
	}

	@Test
	public void addPurgeRequest_WithDeleteCpCodes_ShouldReturnHttpStatusSuccess() {
		List<String> urls = new ArrayList<>();
		urls.add(testPurgeCpcode);
		
		PurgeRequest purgeRequest = PurgeRequest.builder()
				.action(Action.DELETE)
				.type(Type.CPCODE)
				.network(Network.STAGING)
				.objects(urls)
				.build();
		
		FastPurgeClient client = new CcuV3Client(configurationProvider, edgeGridFacade);
		PurgeResponse purgeResponse = client.addPurgeRequest(purgeRequest);
		
		assertEquals(PurgeResponse.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
	}

	@Test
	public void addPurgeRequest_WithDeleteTags_ShouldReturnHttpStatusSuccess() {
		List<String> tags = new ArrayList<>();
		tags.add(testPurgeTag);
		
		PurgeRequest purgeRequest = PurgeRequest.builder()
				.action(Action.DELETE)
				.type(Type.TAG)
				.network(Network.STAGING)
				.objects(tags)
				.build();
		
		FastPurgeClient client = new CcuV3Client(configurationProvider, edgeGridFacade);
		PurgeResponse purgeResponse = client.addPurgeRequest(purgeRequest);
		
		assertEquals(PurgeResponse.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
	}

	@Test
	public void addPurgeRequest_WithInvalidateUrls_ShouldReturnHttpStatusSuccess() {
		List<String> urls = new ArrayList<>();
		urls.add(testPurgeUrl);
		
		PurgeRequest purgeRequest = PurgeRequest.builder()
				.action(Action.INVALIDATE)
				.type(Type.URL)
				.network(Network.STAGING)
				.objects(urls)
				.build();
		
		FastPurgeClient client = new CcuV3Client(configurationProvider, edgeGridFacade);
		PurgeResponse purgeResponse = client.addPurgeRequest(purgeRequest);
		
		assertEquals(PurgeResponse.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
	}

	@Test
	public void addPurgeRequest_WithInvalidateCpCodes_ShouldReturnHttpStatusSuccess() {
		List<String> urls = new ArrayList<>();
		urls.add(testPurgeCpcode);
		
		PurgeRequest purgeRequest = PurgeRequest.builder()
				.action(Action.INVALIDATE)
				.type(Type.CPCODE)
				.network(Network.STAGING)
				.objects(urls)
				.build();
		
		FastPurgeClient client = new CcuV3Client(configurationProvider, edgeGridFacade);
		PurgeResponse purgeResponse = client.addPurgeRequest(purgeRequest);
		
		assertEquals(PurgeResponse.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
	}

	@Test
	public void addPurgeRequest_WithInvalidateTags_ShouldReturnHttpStatusSuccess() {
		List<String> tags = new ArrayList<>();
		tags.add(testPurgeTag);
		
		PurgeRequest purgeRequest = PurgeRequest.builder()
				.action(Action.INVALIDATE)
				.type(Type.TAG)
				.network(Network.STAGING)
				.objects(tags)
				.build();
		
		FastPurgeClient client = new CcuV3Client(configurationProvider, edgeGridFacade);
		PurgeResponse purgeResponse = client.addPurgeRequest(purgeRequest);
		
		assertEquals(PurgeResponse.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
	}

}
