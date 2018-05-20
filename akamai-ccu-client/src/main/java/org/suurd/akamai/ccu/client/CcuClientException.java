package org.suurd.akamai.ccu.client;

/**
 * Exception typically re-thrown after catching and handling an exception within
 * the CCU client.
 * 
 * @author jsuurd
 */
public class CcuClientException extends RuntimeException {

	private static final long serialVersionUID = -7120321485546136205L;

	/**
	 * Constructs a new CCU client exception with the specified detail message.
	 * 
	 * @param message the detail message 
	 */
	public CcuClientException(String message) {
		super(message);
	}

	/**
	 * Constructs a new CCU client exception with the specified detail message and
	 * cause.
	 * 
	 * @param message the detail message 
	 * @param cause the cause
	 */
	public CcuClientException(String message, Throwable cause) {
		super(message, cause);
	}

}
