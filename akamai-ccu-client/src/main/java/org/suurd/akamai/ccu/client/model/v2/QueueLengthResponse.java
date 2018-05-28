package org.suurd.akamai.ccu.client.model.v2;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.suurd.akamai.ccu.client.model.AbstractResponse;
import org.suurd.akamai.ccu.client.model.Constants;

import com.google.api.client.util.Key;

/**
 * Object representation of the Get Queue Length JSON response content.
 * 
 * @author jsuurd
 */
public class QueueLengthResponse extends AbstractResponse {

	@Key
	private int queueLength;

	/**
	 * Get the approximate number of outstanding requests.
	 * 
	 * @return the queue length
	 */
	public int getQueueLength() {
		return queueLength;
	}

	/**
	 * Sets the approximate number of outstanding requests.
	 * 
	 * @param queueLength the queue length to set
	 */
	public void setQueueLength(int queueLength) {
		this.queueLength = queueLength;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, Constants.TO_STRING_STYLE);
	}

}
