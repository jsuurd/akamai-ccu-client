package org.suurd.akamai.ccu.client;

import org.suurd.akamai.ccu.client.model.PurgeRequest;
import org.suurd.akamai.ccu.client.model.PurgeResponse;
import org.suurd.akamai.ccu.client.model.QueueLengthResponse;
import org.suurd.akamai.ccu.client.model.PurgeStatusRequest;
import org.suurd.akamai.ccu.client.model.PurgeStatusResponse;
import org.suurd.akamai.ccu.client.model.Queue;

public interface CcuClient {

	PurgeResponse addPurgeRequest(PurgeRequest purgeRequest);

	PurgeResponse addPurgeRequest(PurgeRequest purgeRequest, Queue queue);

	PurgeStatusResponse getPurgeStatus(PurgeStatusRequest purgeStatusRequest);

	QueueLengthResponse getQueueLength();

	QueueLengthResponse getQueueLength(Queue queue);

}
