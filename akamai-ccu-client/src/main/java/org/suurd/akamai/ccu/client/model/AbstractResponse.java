package org.suurd.akamai.ccu.client.model;

import com.google.api.client.util.Key;

public abstract class AbstractResponse {

	@Key
	private int httpStatus;

	@Key
	private String detail;	

	@Key
	private String supportId;

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getSupportId() {
		return supportId;
	}

	public void setSupportId(String supportId) {
		this.supportId = supportId;
	}

}
