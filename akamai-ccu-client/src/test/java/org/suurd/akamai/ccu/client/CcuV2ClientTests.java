package org.suurd.akamai.ccu.client;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.suurd.akamai.ccu.client.facade.EdgeGridFacade;
import org.suurd.akamai.ccu.client.facade.GoogleHttpClientEdgeGridFacade;
import org.suurd.akamai.ccu.client.model.Configuration;
import org.suurd.akamai.ccu.client.model.v2.Action;
import org.suurd.akamai.ccu.client.model.v2.Domain;
import org.suurd.akamai.ccu.client.model.v2.PurgeRequest;
import org.suurd.akamai.ccu.client.model.v2.PurgeResponse;
import org.suurd.akamai.ccu.client.model.v2.PurgeStatus;
import org.suurd.akamai.ccu.client.model.v2.PurgeStatusRequest;
import org.suurd.akamai.ccu.client.model.v2.PurgeStatusResponse;
import org.suurd.akamai.ccu.client.model.v2.QueueLengthResponse;
import org.suurd.akamai.ccu.client.model.v2.Type;
import org.suurd.akamai.ccu.client.provider.ApacheHttpTransportProvider;
import org.suurd.akamai.ccu.client.provider.ConfigurationProvider;

public class CcuV2ClientTests {

	private ConfigurationProvider configurationProvider;

	private EdgeGridFacade edgeGridFacade;

	private String testPurgeCpcode;

	private String testPurgeUrl;

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
		this.edgeGridFacade = new GoogleHttpClientEdgeGridFacade(configurationProvider, new ApacheHttpTransportProvider());
		this.testPurgeCpcode = testProperties.getProperty("purgeCpcode");
		this.testPurgeUrl = testProperties.getProperty("purgeUrl");
	}

	@Test
	@Ignore("Integration Test")
	public void addPurgeRequest_WithInvalidateArls_ShouldReturnHttpStatusSuccess() {
		List<String> arls = new ArrayList<String>();
		arls.add(testPurgeUrl);
		
		PurgeRequest purgeRequest = PurgeRequest.builder()
				.domain(Domain.STAGING)
				.action(Action.INVALIDATE)
				.type(Type.ARL)
				.objects(arls)
				.build();
		
		CcuClient ccuClient = new CcuV2Client(configurationProvider, edgeGridFacade);
		PurgeResponse purgeResponse = ccuClient.addPurgeRequest(purgeRequest);
		
		assertEquals(PurgeResponse.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
	}

	@Test
	@Ignore("Integration Test")
	public void addPurgeRequest_WithInvalidateCpCodes_ShouldReturnHttpStatusSuccess() {
		List<String> cpCodes = new ArrayList<String>();
		cpCodes.add(testPurgeCpcode);
		
		PurgeRequest purgeRequest = PurgeRequest.builder()
				.domain(Domain.STAGING)
				.action(Action.INVALIDATE)
				.type(Type.CPCODE)
				.objects(cpCodes)
				.build();
		
		CcuClient ccuClient = new CcuV2Client(configurationProvider, edgeGridFacade);
		PurgeResponse purgeResponse = ccuClient.addPurgeRequest(purgeRequest);
		
		assertEquals(PurgeResponse.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
	}

	@Test
	@Ignore("Integration Test")
	public void addPurgeRequest_WithRemoveArls_ShouldReturnHttpStatusSuccess() {
		List<String> arls = new ArrayList<String>();
		arls.add(testPurgeUrl);
		
		PurgeRequest purgeRequest = PurgeRequest.builder()
				.domain(Domain.STAGING)
				.action(Action.INVALIDATE)
				.type(Type.ARL)
				.objects(arls)
				.build();
		
		CcuClient ccuClient = new CcuV2Client(configurationProvider, edgeGridFacade);
		PurgeResponse purgeResponse = ccuClient.addPurgeRequest(purgeRequest);
		
		assertEquals(PurgeResponse.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
	}

	@Test
	@Ignore("Integration Test")
	public void addPurgeRequest_WithRemoveCpCode_ShouldReturnHttpStatusSuccess() {
		List<String> cpCodes = new ArrayList<String>();
		cpCodes.add(testPurgeCpcode);
		
		PurgeRequest purgeRequest = PurgeRequest.builder()
				.domain(Domain.STAGING)
				.action(Action.INVALIDATE)
				.type(Type.CPCODE)
				.objects(cpCodes)
				.build();
		
		CcuClient ccuClient = new CcuV2Client(configurationProvider, edgeGridFacade);
		PurgeResponse purgeResponse = ccuClient.addPurgeRequest(purgeRequest);
		
		assertEquals(PurgeResponse.HTTP_STATUS_POST_SUCCESS, purgeResponse.getHttpStatus());
	}

	@Test
	@Ignore("Integration Test")
	public void getPurgeStatus_CheckStatusProgressUri_ReturnsHttpStatusSuccess() {
		List<String> arls = new ArrayList<String>();
		arls.add(testPurgeUrl);
		
		PurgeRequest purgeRequest = PurgeRequest.builder()
				.domain(Domain.STAGING)
				.action(Action.INVALIDATE)
				.type(Type.ARL)
				.objects(arls)
				.build();
		
		CcuClient ccuClient = new CcuV2Client(configurationProvider, edgeGridFacade);
		PurgeResponse purgeResponse = ccuClient.addPurgeRequest(purgeRequest);
		
		PurgeStatusRequest statusRequest = PurgeStatusRequest.builder()
				.progressUri(purgeResponse.getProgressUri())
				.build();
		
		PurgeStatusResponse statusResponse = ccuClient.getPurgeStatus(statusRequest);
		
		assertEquals(PurgeResponse.HTTP_STATUS_GET_SUCCESS, statusResponse.getHttpStatus());
		assertEquals(PurgeStatus.IN_PROGRESS, statusResponse.getPurgeStatus());
	}

	@Test
	@Ignore("Integration Test")
	public void getQueueLength_CheckStatusProgressUri_ReturnsHttpStatusSuccess() {
		CcuClient ccuClient = new CcuV2Client(configurationProvider, edgeGridFacade);
		QueueLengthResponse response = ccuClient.getQueueLength();
		
		assertEquals(PurgeResponse.HTTP_STATUS_GET_SUCCESS, response.getHttpStatus());
	}

}
