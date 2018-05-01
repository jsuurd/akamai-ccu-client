package org.suurd.akamai.ccu.client.model;

import com.google.api.client.util.Key;

public class PurgeStatusResponse extends AbstractResponse {

	@Key
	private PurgeStatus purgeStatus;

	@Key
	private int originalQueueLength;

	@Key
	private int originalEstimatedSeconds;

	@Key
	private String progressUri;

	@Key
	private String purgeId;

	@Key
	private int pingAfterSeconds;

	@Key
	private String submittedBy;

	@Key
	private String submissionTime;

	@Key
	private String completionTime;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[purgeStatus=");
		sb.append(getPurgeStatus());
		sb.append(", originalQueueLength=");
		sb.append(getOriginalQueueLength());
		sb.append(", originalEstimatedSeconds=");
		sb.append(getOriginalEstimatedSeconds());
		sb.append(", progressUri=");
		sb.append(getProgressUri());
		sb.append(", purgeId=");
		sb.append(getPurgeId());
		sb.append(", pingAfterSeconds=");
		sb.append(getPingAfterSeconds());
		sb.append(", submittedBy=");
		sb.append(getSubmittedBy());
		sb.append(", submissionTime=");
		sb.append(getSubmissionTime());
		sb.append(", completionTime=");
		sb.append(getCompletionTime());
		sb.append(" httpStatus=");
		sb.append(getHttpStatus());
		sb.append(" detail=");
		sb.append(getDetail());
		sb.append(", supportId=");
		sb.append(getSupportId());
		sb.append("]");
		return sb.toString();
	}

	public PurgeStatus getPurgeStatus() {
		return purgeStatus;
	}

	public void setPurgeStatus(PurgeStatus purgeStatus) {
		this.purgeStatus = purgeStatus;
	}

	public int getOriginalQueueLength() {
		return originalQueueLength;
	}

	public void setOriginalQueueLength(int originalQueueLength) {
		this.originalQueueLength = originalQueueLength;
	}

	public int getOriginalEstimatedSeconds() {
		return originalEstimatedSeconds;
	}

	public void setOriginalEstimatedSeconds(int originalEstimatedSeconds) {
		this.originalEstimatedSeconds = originalEstimatedSeconds;
	}

	public String getProgressUri() {
		return progressUri;
	}

	public void setProgressUri(String progressUri) {
		this.progressUri = progressUri;
	}

	public String getPurgeId() {
		return purgeId;
	}

	public void setPurgeId(String purgeId) {
		this.purgeId = purgeId;
	}

	public int getPingAfterSeconds() {
		return pingAfterSeconds;
	}

	public void setPingAfterSeconds(int pingAfterSeconds) {
		this.pingAfterSeconds = pingAfterSeconds;
	}

	public String getSubmittedBy() {
		return submittedBy;
	}

	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

	public String getSubmissionTime() {
		return submissionTime;
	}

	public void setSubmissionTime(String submissionTime) {
		this.submissionTime = submissionTime;
	}

	public String getCompletionTime() {
		return completionTime;
	}

	public void setCompletionTime(String completionTime) {
		this.completionTime = completionTime;
	}

}
