package org.suurd.akamai.ccu.client;

import org.suurd.akamai.ccu.client.model.PurgeRequest;
import org.suurd.akamai.ccu.client.model.PurgeResponse;
import org.suurd.akamai.ccu.client.model.QueueLengthResponse;
import org.suurd.akamai.ccu.client.model.PurgeStatusRequest;
import org.suurd.akamai.ccu.client.model.PurgeStatusResponse;
import org.suurd.akamai.ccu.client.model.Queue;

/**
 * Interface providing methods representing the Akamai Content Control Utility
 * REST API.
 * 
 * @author jsuurd
 */
public interface CcuClient {

	/**
	 * Adds a purge request to the default queue.
	 * 
	 * @param purgeRequest the purge request
	 * @return the purge response
	 */
	PurgeResponse addPurgeRequest(PurgeRequest purgeRequest);

	/**
	 * Adds a purge request to the specified queue.
	 * 
	 * @param purgeRequest the purge request
	 * @param queue the queue
	 * @return the purge response
	 */
	PurgeResponse addPurgeRequest(PurgeRequest purgeRequest, Queue queue);

	/**
	 * Gets the status of a purge request.
	 * 
	 * @param purgeStatusRequest the purge status request
	 * @return the purge status response
	 */
	PurgeStatusResponse getPurgeStatus(PurgeStatusRequest purgeStatusRequest);

	/**
	 * Gets the length of the default queue.
	 * 
	 * @return the queue length response
	 */
	QueueLengthResponse getQueueLength();

	/**
	 * Gets the length of the specified queue.
	 * 
	 * @param queue the queue
	 * @return the queue length response 
	 */
	QueueLengthResponse getQueueLength(Queue queue);

}
