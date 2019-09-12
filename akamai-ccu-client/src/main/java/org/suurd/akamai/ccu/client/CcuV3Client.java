package org.suurd.akamai.ccu.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suurd.akamai.ccu.client.facade.EdgeGridFacade;
import org.suurd.akamai.ccu.client.model.Configuration;
import org.suurd.akamai.ccu.client.model.v3.PurgeRequest;
import org.suurd.akamai.ccu.client.model.v3.PurgeResponse;
import org.suurd.akamai.ccu.client.provider.ConfigurationProvider;

/**
 * Implementation of the CCU client exposing methods provided by the Akamai
 * Content Control Utility v3 API.
 * 
 * @author jsuurd
 */
public class CcuV3Client implements FastPurgeClient {

	private static final Logger LOG = LoggerFactory.getLogger(CcuV3Client.class);

	private Configuration configuration;

	private EdgeGridFacade edgeGridFacade;

	/**
	 * Constructs a CCU v3 client with the specified configuration provider and
	 * EdgeGrid facade.
	 * 
	 * @param configurationProvider the configuration provider
	 * @param edgeGridFacade the EdgeGrid facade
	 */
	public CcuV3Client(ConfigurationProvider configurationProvider, EdgeGridFacade edgeGridFacade) {
		this.configuration = configurationProvider.getConfiguration();
		this.edgeGridFacade = edgeGridFacade;
	}

	@Override
	public PurgeResponse addPurgeRequest(PurgeRequest purgeRequest) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Sending purge request {}", purgeRequest);
		}
		
		String requestPath = String.join("/", configuration.getQueuesEndpoint(), String.valueOf(purgeRequest.getAction()), String.valueOf(purgeRequest.getType()), String.valueOf(purgeRequest.getNetwork())).toLowerCase();
		
		PurgeResponse purgeResponse = edgeGridFacade.submitPostRequest(requestPath, purgeRequest, PurgeResponse.class);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Received purge response {}", purgeResponse);
		}
		
		return purgeResponse;
	}

}
