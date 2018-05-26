package org.suurd.akamai.ccu.client;

import org.suurd.akamai.ccu.client.model.v2.PurgeRequest;
import org.suurd.akamai.ccu.client.model.v2.PurgeResponse;
import org.suurd.akamai.ccu.client.model.v2.PurgeStatusRequest;
import org.suurd.akamai.ccu.client.model.v2.PurgeStatusResponse;
import org.suurd.akamai.ccu.client.model.v2.Queue;
import org.suurd.akamai.ccu.client.model.v2.QueueLengthResponse;

/**
 * Interface exposing methods provided by the Akamai Content Control Utility v2
 * API.
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
