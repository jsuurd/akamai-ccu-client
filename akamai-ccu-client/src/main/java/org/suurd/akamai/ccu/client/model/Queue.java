package org.suurd.akamai.ccu.client.model;

public enum Queue {	

	DEFAULT("default"),

	EMERGENCY("emergency");	

	private String queueName;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[queueName=");
		sb.append(getQueueName());
		sb.append("]");
		return sb.toString();
	}

	private Queue(String queueName) {
		this.queueName = queueName;
	}

	public String getQueueName() {
		return this.queueName;
	}

}
