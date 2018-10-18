package org.suurd.akamai.ccu.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suurd.akamai.ccu.client.facade.EdgeGridFacade;
import org.suurd.akamai.ccu.client.model.Configuration;
import org.suurd.akamai.ccu.client.model.v2.PurgeRequest;
import org.suurd.akamai.ccu.client.model.v2.PurgeResponse;
import org.suurd.akamai.ccu.client.model.v2.PurgeStatusRequest;
import org.suurd.akamai.ccu.client.model.v2.PurgeStatusResponse;
import org.suurd.akamai.ccu.client.model.v2.Queue;
import org.suurd.akamai.ccu.client.model.v2.QueueLengthResponse;
import org.suurd.akamai.ccu.client.provider.ConfigurationProvider;

/**
 * Implementation of the CCU client exposing methods provided by the Akamai
 * Content Control Utility v2 API.
 * 
 * @author jsuurd
 */
public class CcuV2Client implements CcuClient {

	private static final Logger LOG = LoggerFactory.getLogger(CcuV2Client.class);

	private Configuration configuration;

	private EdgeGridFacade edgeGridFacade;

	/**
	 * Constructs a CCU v2 client with the specified configuration provider and
	 * EdgeGrid facade.
	 * 
	 * @param configurationProvider the configuration provider
	 * @param edgeGridFacade the EdgeGrid facade
	 */
	public CcuV2Client(ConfigurationProvider configurationProvider, EdgeGridFacade edgeGridFacade) {
		this.configuration = configurationProvider.getConfiguration();
		this.edgeGridFacade = edgeGridFacade;
	}

	@Override
	public PurgeResponse addPurgeRequest(PurgeRequest purgeRequest) {
		return addPurgeRequest(purgeRequest, Queue.DEFAULT);
	}

	@Override
	public PurgeResponse addPurgeRequest(PurgeRequest purgeRequest, Queue queue) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Sending purge request {} to queue {}", purgeRequest, queue);
		}
		
		String requestPath = String.join("/", configuration.getQueuesEndpoint(), queue.getQueueName());
		
		PurgeResponse purgeResponse = edgeGridFacade.submitPostRequest(requestPath, purgeRequest, PurgeResponse.class);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Received purge response {}", purgeResponse);
		}
		return purgeResponse;
		
	}

	@Override
	public PurgeStatusResponse getPurgeStatus(PurgeStatusRequest statusRequest) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Sending status request {}", statusRequest);
		}
		
		PurgeStatusResponse statusResponse = edgeGridFacade.submitGetRequest(statusRequest.getProgressUri(), PurgeStatusResponse.class);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Received status response {}", statusResponse);
		}
		
		return statusResponse;
	}

	@Override
	public QueueLengthResponse getQueueLength() {
		return getQueueLength(Queue.DEFAULT);
	}

	@Override
	public QueueLengthResponse getQueueLength(Queue queue) {
		if (LOG.isDebugEnabled()) {
			LOG.debug("Sending queue length request to queue {}", queue);
		}
		
		String requestPath = String.join("/", configuration.getQueuesEndpoint(), queue.getQueueName());
		
		QueueLengthResponse queueLengthResponse =  edgeGridFacade.submitGetRequest(requestPath, QueueLengthResponse.class);
		if (LOG.isDebugEnabled()) {
			LOG.debug("Received queue length response {}", queueLengthResponse);
		}
		
		return queueLengthResponse;
	}

}
