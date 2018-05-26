package org.suurd.akamai.ccu.client.model.v2;

/**
 * Enumeration of the queue to use, either default (default) or emergency.
 * 
 * @author jsuurd
 */
public enum Queue {	

	/**
	 * The default queue.
	 */
	DEFAULT("default"),

	/**
	 * The emergency queue. 
	 */
	EMERGENCY("emergency");	

	/**
	 * The queue to use
	 */
	private String queueName;

	/**
	 * Constructs a queue with the specified queue name.
	 * 
	 * @param queueName the queue name
	 */
	private Queue(String queueName) {
		this.queueName = queueName;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[queueName=");
		sb.append(getQueueName());
		sb.append("]");
		return sb.toString();
	}

	/**
	 * @return the queueName
	 */
	public String getQueueName() {
		return queueName;
	}

}
