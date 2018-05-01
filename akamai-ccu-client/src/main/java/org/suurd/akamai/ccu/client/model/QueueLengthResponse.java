package org.suurd.akamai.ccu.client.model;

import com.google.api.client.util.Key;

public class QueueLengthResponse extends AbstractResponse {

	@Key
	private int queueLength;

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

	public int getQueueLength() {
		return queueLength;
	}

	public void setQueueLength(int queueLength) {
		this.queueLength = queueLength;
	}

}
