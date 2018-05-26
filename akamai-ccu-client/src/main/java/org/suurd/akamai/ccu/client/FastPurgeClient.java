package org.suurd.akamai.ccu.client;

import org.suurd.akamai.ccu.client.model.v3.PurgeRequest;
import org.suurd.akamai.ccu.client.model.v3.PurgeResponse;

/**
 * Interface exposing methods provided by the Akamai Fast Purge API (formerly
 * known as Content Control Utility v3 API).
 * 
 * @author jsuurd
 */
public interface FastPurgeClient {

	/**
	 * Adds a purge request to the queue.
	 * 
	 * @param purgeRequest the purge request
	 * @return the purge response
	 */
	PurgeResponse addPurgeRequest(PurgeRequest purgeRequest);

}