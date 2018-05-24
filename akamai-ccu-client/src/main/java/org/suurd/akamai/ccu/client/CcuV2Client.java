package org.suurd.akamai.ccu.client;

import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suurd.akamai.ccu.client.facade.EdgeGridFacade;
import org.suurd.akamai.ccu.client.model.Configuration;
import org.suurd.akamai.ccu.client.model.PurgeRequest;
import org.suurd.akamai.ccu.client.model.PurgeResponse;
import org.suurd.akamai.ccu.client.model.PurgeStatusRequest;
import org.suurd.akamai.ccu.client.model.PurgeStatusResponse;
import org.suurd.akamai.ccu.client.model.Queue;
import org.suurd.akamai.ccu.client.model.QueueLengthResponse;
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
			LOG.debug(MessageFormat.format("Sending purge request {0} to queue {1}", purgeRequest, queue));
		}
		
		String requestPath = String.join("/", configuration.getQueuesEndpoint(), queue.getQueueName());
		
		PurgeResponse purgeResponse = edgeGridFacade.submitPostRequest(requestPath, purgeRequest, PurgeResponse.class);
		if (LOG.isDebugEnabled()) {
			LOG.debug(MessageFormat.format("Received purge response {0}", purgeResponse));
		}
		return purgeResponse;
		
	}

	@Override
	public PurgeStatusResponse getPurgeStatus(PurgeStatusRequest statusRequest) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(MessageFormat.format("Sending status request {0}", statusRequest));
		}
		
		PurgeStatusResponse statusResponse = edgeGridFacade.submitGetRequest(statusRequest.getProgressUri(), PurgeStatusResponse.class);
		if (LOG.isDebugEnabled()) {
			LOG.debug(MessageFormat.format("Received status response {0}", statusResponse));
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
			LOG.debug(MessageFormat.format("Sending queue length request to queue {0}", queue));
		}
		
		String requestPath = String.join("/", configuration.getQueuesEndpoint(), queue.getQueueName());
		
		QueueLengthResponse queueLengthResponse =  edgeGridFacade.submitGetRequest(requestPath, QueueLengthResponse.class);
		if (LOG.isDebugEnabled()) {
			LOG.debug(MessageFormat.format("Received queue length response {0}", queueLengthResponse));
		}
		
		return queueLengthResponse;
	}

}
