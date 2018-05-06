package org.suurd.akamai.ccu.client.util;

import java.io.IOException;
import java.text.MessageFormat;

import org.suurd.akamai.ccu.client.CcuClientException;
import org.suurd.akamai.ccu.client.model.Constants;

import com.google.api.client.http.HttpRequest;

/**
 * Helper providing helper methods for {@link HttpRequest}.
 * 
 * @author jsuurd
 */
public class HttpRequestHelper {

	private HttpRequestHelper() {}

	/**
	 * Validated the content size of the HTTP request.
	 * 
	 * @param request the HTTP request
	 * @throws IOException
	 */
	public static void validateContentSize(HttpRequest request) throws IOException {
		if (request.getContent() != null) {
			long requestContentSize = request.getContent().getLength(); 
			if (requestContentSize > Constants.REQUEST_MAX_CONTENT_SIZE) {
				throw new CcuClientException(MessageFormat.format("Request content size [{0}] exceeds the maximum size[{1}]]", requestContentSize, Constants.REQUEST_MAX_CONTENT_SIZE));
			}
		}
	}

}
