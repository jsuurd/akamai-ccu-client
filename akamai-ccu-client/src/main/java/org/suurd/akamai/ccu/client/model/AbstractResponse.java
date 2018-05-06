package org.suurd.akamai.ccu.client.model;

import com.google.api.client.util.Key;

/**
 * Abstract base class for object representation of the CCU JSON response
 * content. Contains common properties valid for every CCU response.
 * 
 * @author jsuurd
 */
public abstract class AbstractResponse {

	@Key
	private int httpStatus;

	@Key
	private String detail;	

	@Key
	private String supportId;

	/**
	 * Gets the HTTP status code.
	 * 
	 * @return the httpStatus
	 */
	public int getHttpStatus() {
		return httpStatus;
	}

	/**
	 * Sets the HTTP status code.
	 * 
	 * @param httpStatus the HTTP status code to set
	 */
	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	/**
	 * Gets the detail about the HTTP status code.
	 * 
	 * @return the detail
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * Sets the detail about the HTTP status code.
	 * 
	 * @param detail the detail to set
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * Gets the support ID provided to Akamai Technical Support if needed.
	 * 
	 * @return the support ID
	 */
	public String getSupportId() {
		return supportId;
	}

	/**
	 * Sets the support ID provided to Akamai Technical Support if needed.
	 * 
	 * @param supportId the support ID to set
	 */
	public void setSupportId(String supportId) {
		this.supportId = supportId;
	}

}
