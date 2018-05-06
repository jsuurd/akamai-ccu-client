package org.suurd.akamai.ccu.client.model;

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
		StringBuilder sb = new StringBuilder();
		sb.append("[queueLength=");
		sb.append(getQueueLength());
		sb.append(" httpStatus=");
		sb.append(getHttpStatus());
		sb.append(" detail=");
		sb.append(getDetail());
		sb.append(", supportId=");
		sb.append(getSupportId());
		sb.append("]");
		return sb.toString();
	}

}
