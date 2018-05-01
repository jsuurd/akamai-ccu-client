package org.suurd.akamai.ccu.client;

public class CcuClientException extends RuntimeException {

	private static final long serialVersionUID = -7120321485546136205L;

	public CcuClientException(String message) {
		super(message);
	}

	public CcuClientException(String message, Throwable cause) {
		super(message, cause);
	}

}
