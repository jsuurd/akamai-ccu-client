package org.suurd.akamai.ccu.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.MessageFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.suurd.akamai.ccu.client.model.Constants;
import org.suurd.akamai.ccu.client.model.PurgeRequest;
import org.suurd.akamai.ccu.client.model.PurgeResponse;
import org.suurd.akamai.ccu.client.model.QueueLengthResponse;
import org.suurd.akamai.ccu.client.model.PurgeStatusRequest;
import org.suurd.akamai.ccu.client.model.PurgeStatusResponse;
import org.suurd.akamai.ccu.client.model.Queue;
import org.suurd.akamai.ccu.client.provider.HttpTransportProvider;
import org.suurd.akamai.ccu.client.util.Configuration;
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
 * https://github.com/akamai/AkamaiOPEN-edgegrid-java/tree/master/edgegrid-signer-core
 * https://github.com/akamai/AkamaiOPEN-edgegrid-java/tree/master/edgegrid-signer-google-http-client
 * 
 * @author jsuurd
 */
public class CcuV2Client implements CcuClient {

	private static final Logger LOG = LoggerFactory.getLogger(CcuV2Client.class);

	private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	private static Configuration configuration = Configuration.getInstance();

	private HttpTransportProvider transportProvider;

	private ClientCredentialProvider credentialProvider;

	public CcuV2Client(HttpTransportProvider transportProvider) {
		this.transportProvider = transportProvider;
		this.credentialProvider = createCredentialProvider();
	}

	@Override
	public PurgeResponse addPurgeRequest(PurgeRequest purgeRequest) {
		return addPurgeRequest(purgeRequest, Queue.DEFAULT);
	}

	@Override
	public PurgeResponse addPurgeRequest(PurgeRequest purgeRequest, Queue queue) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(MessageFormat.format("Sending purge request {0} to queue {1}", purgeRequest, queue));
		}
		
		String requestPath = configuration.getQueuesEndpoint() + queue.getQueueName();
		
		try {
			HttpContent content = new JsonHttpContent(JSON_FACTORY, purgeRequest);
			
			HttpRequestFactory requestFactory = createSigningRequestFactory();
			URI uri = new URI("https", configuration.getBaseAuthority(), requestPath, null, null);
			HttpRequest request = requestFactory.buildPostRequest(new GenericUrl(uri), content);
			
			PurgeResponse purgeResponse = handleRequest(request, PurgeResponse.class); 
			if (LOG.isDebugEnabled()) {
				LOG.debug(MessageFormat.format("Received purge response {0}", purgeResponse));
			}
			return purgeResponse;
		
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

	@Override
	public PurgeStatusResponse getPurgeStatus(PurgeStatusRequest statusRequest) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(MessageFormat.format("Sending status request {0}", statusRequest));
		}
		
		try {
			HttpRequestFactory requestFactory = createSigningRequestFactory();
			URI uri = new URI("https", configuration.getBaseAuthority(), statusRequest.getProgressUri(), null, null);
			HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(uri));
			
			PurgeStatusResponse statusResponse =  handleRequest(request, PurgeStatusResponse.class);
			if (LOG.isDebugEnabled()) {
				LOG.debug(MessageFormat.format("Received status response {0}", statusResponse));
			}
			return statusResponse;
			
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
	public QueueLengthResponse getQueueLength() {
		return getQueueLength(Queue.DEFAULT);
	}

	@Override
	public QueueLengthResponse getQueueLength(Queue queue) {
		if (LOG.isDebugEnabled()) {
			LOG.debug(MessageFormat.format("Sending queue length request to queue {0}", queue));
		}
		
		String requestPath = configuration.getQueuesEndpoint() + queue.getQueueName();
		
		try {
			HttpRequestFactory requestFactory = createSigningRequestFactory();
			URI uri = new URI("https", configuration.getBaseAuthority(), requestPath, null, null);
			HttpRequest request = requestFactory.buildGetRequest(new GenericUrl(uri));
			
			QueueLengthResponse queueLengthResponse =  handleRequest(request, QueueLengthResponse.class);
			if (LOG.isDebugEnabled()) {
				LOG.debug(MessageFormat.format("Received queue length response {0}", queueLengthResponse));
			}
			return queueLengthResponse;
			
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
		headers.setContentType(Constants.CONTENT_TYPE_JSON);
		headers.setAccept(Constants.CONTENT_TYPE_JSON);		
		
		HttpResponse response = request.execute();
		T ccuResponse;
		try {
			int statusCode = response.getStatusCode();
			if (LOG.isDebugEnabled()) {
				LOG.debug(MessageFormat.format("Received HTTP response status [statusCode={0}]", statusCode));
			}
			ccuResponse = response.parseAs(responseClass);
		} finally {
			response.disconnect();
		}
		return ccuResponse;
	}

}
