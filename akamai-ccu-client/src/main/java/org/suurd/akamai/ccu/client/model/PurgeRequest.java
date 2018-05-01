package org.suurd.akamai.ccu.client.model;

import java.util.List;

import com.google.api.client.util.Key;

public class PurgeRequest {

	@Key
	private Domain domain;

	@Key
	private Action action;

	@Key
	private Type type;

	@Key
	private List<String> objects;

	public PurgeRequest(List<String> objects) {
		setObjects(objects);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[domain=");
		sb.append(getDomain());
		sb.append(" action=");
		sb.append(getAction());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", objects=");
		sb.append(getObjects());
		sb.append("]");
		return sb.toString();
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<String> getObjects() {
		return objects;
	}

	public void setObjects(List<String> objects) {
		this.objects = objects;
	}

}
