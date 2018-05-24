package org.suurd.akamai.ccu.client.v3;

import org.suurd.akamai.ccu.client.v3.model.PurgeRequest;
import org.suurd.akamai.ccu.client.v3.model.PurgeResponse;

/**
 * Interface exposing methods provided by the Akamai Content Control Utility v3
 * API.
 * 
 * @author jsuurd
 */
public interface CcuClient {

	/**
	 * Adds a purge request to the queue.
	 * 
	 * @param purgeRequest the purge request
	 * @return the purge response
	 */
	PurgeResponse addPurgeRequest(PurgeRequest purgeRequest);

}