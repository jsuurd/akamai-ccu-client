package org.suurd.akamai.ccu.client.facade;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suurd.akamai.ccu.client.CcuClientException;
import org.suurd.akamai.ccu.client.model.Configuration;
import org.suurd.akamai.ccu.client.provider.ConfigurationProvider;
import org.suurd.akamai.ccu.client.provider.HttpTransportProvider;
import org.suurd.akamai.ccu.client.util.HttpRequestHelper;

import com.akamai.edgegrid.signer.ClientCredential;
import com.akamai.edgegrid.signer.ClientCredentialProvider;
import com.akamai.edgegrid.signer.DefaultClientCredentialProvider;
import com.akamai.edgegrid.signer.googlehttpclient.GoogleHttpClientEdgeGridInterceptor;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpBackOffIOExceptionHandler;
import com.google.api.client.http.HttpBackOffUnsuccessfulResponseHandler;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.ExponentialBackOff;

/**
 * Implementation of the EdgeGrid facade leveraging the Google HTTP Client
 * Library binding.
 * 
 * @author jsuurd
 */
public class GoogleHttpClientEdgeGridFacade implements EdgeGridFacade {

	private static final Logger LOG = LoggerFactory.getLogger(GoogleHttpClientEdgeGridFacade.class);

	private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	private static final String CONTENT_TYPE_JSON = "application/json";

	private Configuration configuration;

	private HttpTransportProvider transportProvider;

	private ClientCredentialProvider credentialProvider;

	/**
	 * Constructs a Google HTTP client EdgeGrid facade with the specified
	 * configuration provider and HTTP transport provider.
	 * 
	 * @param configurationProvider the configuration provider
	 * @param transportProvider the HTTP transport provider
	 */
	public GoogleHttpClientEdgeGridFacade(ConfigurationProvider configurationProvider, HttpTransportProvider transportProvider) {
		this.configuration = configurationProvider.getConfiguration();
		this.transportProvider = transportProvider;
		this.credentialProvider = createCredentialProvider();
	}

	@Override
	public <T> T submitGetRequest(String path, Class<T> responseClass) {
		try {
			HttpRequestFactory requestFactory = createSigningRequestFactory();
			URI uri = new URI("https", configuration.getBaseAuthority(), path, null, null);
			HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(uri));
			
			return handleRequest(request, responseClass);
			
		} catch (IOException e) {
			String message = "Error processing HTTP request/response";
			LOG.error(message, e);
			throw new CcuClientException(e.getMessage(), e);
		} catch (URISyntaxException e) {
			String message = "Error creating request URI";
			LOG.error(message, e);
			throw new CcuClientException(message, e);
		}
	}

	@Override
	public <T> T submitPostRequest(String path, Object data, Class<T> responseClass) {
		try {
			HttpContent content = new JsonHttpContent(JSON_FACTORY, data);
			
			HttpRequestFactory requestFactory = createSigningRequestFactory();
			URI uri = new URI("https", configuration.getBaseAuthority(), path, null, null);
			HttpRequest request = requestFactory.buildPostRequest(new GenericUrl(uri), content);
			
			return handleRequest(request, responseClass);
			
		} catch (IOException e) {
			String message = "Error processing HTTP request/response";
			LOG.error(message, e);
			throw new CcuClientException(message, e);
		} catch (URISyntaxException e) {
			String message = "Error creating request URI";
			LOG.error(message, e);
			throw new CcuClientException(message, e);
		}
	}

	private DefaultClientCredentialProvider createCredentialProvider() {
		ClientCredential credential = new ClientCredential.ClientCredentialBuilder()
				.accessToken(configuration.getAccessToken())
				.clientToken(configuration.getClientToken())
				.clientSecret(configuration.getClientSecret())
				.host(configuration.getBaseAuthority())
				.build();
		
		return new DefaultClientCredentialProvider(credential);
	}

	private HttpRequestFactory createSigningRequestFactory() {
		HttpBackOffUnsuccessfulResponseHandler responseHandler = new HttpBackOffUnsuccessfulResponseHandler(new ExponentialBackOff());
		responseHandler.setBackOffRequired(HttpBackOffUnsuccessfulResponseHandler.BackOffRequired.ALWAYS);
		
		HttpTransport transport = transportProvider.getHttpTransport();
		return transport.createRequestFactory(new HttpRequestInitializer() {
			public void initialize(HttpRequest request) throws IOException {
				request.setInterceptor(new GoogleHttpClientEdgeGridInterceptor(credentialProvider));
				request.setParser(new JsonObjectParser(JSON_FACTORY));
				request.setIOExceptionHandler(new HttpBackOffIOExceptionHandler(new ExponentialBackOff()));
				request.setUnsuccessfulResponseHandler(responseHandler);
				if (configuration.getConnectTimeout() != null) {
					request.setConnectTimeout(configuration.getConnectTimeout());
				}
				if (configuration.getReadTimeout() != null) {
					request.setReadTimeout(configuration.getReadTimeout());
				}
				if (configuration.getNumberOfRetries() != null) {
					request.setNumberOfRetries(configuration.getNumberOfRetries());
				}
			}
		});
	}

	private <T> T handleRequest(HttpRequest request, Class<T> responseClass) throws IOException {
		HttpRequestHelper.validateContentSize(request);
		
		HttpHeaders headers = request.getHeaders();
		headers.set("Host", configuration.getBaseAuthority());
		headers.set("Pragma", "edgegrid-fingerprints-on");
		headers.setContentType(CONTENT_TYPE_JSON);
		headers.setAccept(CONTENT_TYPE_JSON);
		
		T ccuResponse;
		HttpResponse response = request.execute();
		try {
			int statusCode = response.getStatusCode();
			if (LOG.isDebugEnabled()) {
				LOG.debug("Received HTTP response status [statusCode={}]", statusCode);
			}
			ccuResponse = response.parseAs(responseClass);
		} finally {
			response.disconnect();
		}
		return ccuResponse;
	}

}
