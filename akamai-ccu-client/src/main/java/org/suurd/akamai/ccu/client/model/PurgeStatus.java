package org.suurd.akamai.ccu.client.model;

import com.google.api.client.util.Value;

/**
 * Enumeration of the status of the purge request, which is either Done,
 * In-Progress, or Unknown.
 * 
 * @author jsuurd
 */
public enum PurgeStatus {

	/**
	 * The Done purge status.
	 */
	@Value("Done")
	DONE,

	/**
	 * The In-Progress purge status.
	 */
	@Value("In-Progress")
	IN_PROGRESS,

	/**
	 * The Unknown purge status
	 */
	@Value("Unknown")
	UNKNOWN

}
