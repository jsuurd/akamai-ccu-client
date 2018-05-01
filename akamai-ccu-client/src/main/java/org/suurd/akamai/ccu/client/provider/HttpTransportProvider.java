package org.suurd.akamai.ccu.client.provider;

import com.google.api.client.http.HttpTransport;

public interface HttpTransportProvider {

	HttpTransport getHttpTransport();

}
