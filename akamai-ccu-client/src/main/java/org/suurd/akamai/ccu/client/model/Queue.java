package org.suurd.akamai.ccu.client.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

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
		return new ToStringBuilder(this, Constants.TO_STRING_STYLE)
				.append("queueName", getQueueName())
				.build();
	}

	/**
	 * @return the queueName
	 */
	public String getQueueName() {
		return queueName;
	}

}
