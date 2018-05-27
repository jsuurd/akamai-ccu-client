package org.suurd.akamai.ccu.client.model.v2;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.suurd.akamai.ccu.client.model.AbstractResponse;
import org.suurd.akamai.ccu.client.model.Constants;

import com.google.api.client.util.Key;

/**
 * Object representation of the Get Purge Status JSON response content.
 * 
 * @author jsuurd
 */
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

	/**
	 * Gets the status of the purge request.
	 * 
	 * @return the purge status
	 */
	public PurgeStatus getPurgeStatus() {
		return purgeStatus;
	}

	/**
	 * Sets the status of the purge request.
	 * 
	 * @param purgeStatus the purge status to set
	 */
	public void setPurgeStatus(PurgeStatus purgeStatus) {
		this.purgeStatus = purgeStatus;
	}

	/**
	 * Gets the approximate queue length at the time of the submission.
	 * 
	 * @return the original queue length
	 */
	public int getOriginalQueueLength() {
		return originalQueueLength;
	}

	/**
	 * Sets the approximate queue length at the time of the submission.
	 * 
	 * @param originalQueueLength the original queue length to set
	 */
	public void setOriginalQueueLength(int originalQueueLength) {
		this.originalQueueLength = originalQueueLength;
	}

	/**
	 * Gets the estimated time in seconds originally estimated.
	 * 
	 * @return the original estimated seconds
	 */
	public int getOriginalEstimatedSeconds() {
		return originalEstimatedSeconds;
	}

	/**
	 * Sets the estimated time in seconds originally estimated.
	 * 
	 * @param originalEstimatedSeconds the original estimated seconds to set
	 */
	public void setOriginalEstimatedSeconds(int originalEstimatedSeconds) {
		this.originalEstimatedSeconds = originalEstimatedSeconds;
	}

	/**
	 * Gets the progress URI to make Purge Status calls.
	 * 
	 * @return the progress URI
	 */
	public String getProgressUri() {
		return progressUri;
	}

	/**
	 * Sets the progress URI to make Purge Status calls.
	 * 
	 * @param progressUri the progress URI to set
	 */
	public void setProgressUri(String progressUri) {
		this.progressUri = progressUri;
	}

	/**
	 * Gets the unique ID for the purge request.
	 * 
	 * @return the purgeId
	 */
	public String getPurgeId() {
		return purgeId;
	}

	/**
	 * Sets the unique ID for the purge request.
	 * 
	 * @param purgeId the purgeId to set
	 */
	public void setPurgeId(String purgeId) {
		this.purgeId = purgeId;
	}

	/**
	 * Gets the minimum amount of time to wait before for the next Purge Status call.
	 * 
	 * @return the ping after seconds
	 */
	public int getPingAfterSeconds() {
		return pingAfterSeconds;
	}

	/**
	 * Sets the minimum amount of time to wait before for the next Purge Status call.
	 * 
	 * @param pingAfterSeconds the ping after seconds to set
	 */
	public void setPingAfterSeconds(int pingAfterSeconds) {
		this.pingAfterSeconds = pingAfterSeconds;
	}

	/**
	 * Gets the submitter of the purge request.
	 * 
	 * @return the submitted by
	 */
	public String getSubmittedBy() {
		return submittedBy;
	}

	/**
	 * Sets the submitter of the purge request.
	 * 
	 * @param submittedBy the submitted by to set
	 */
	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}

	/**
	 * Gets the time the purge request was accepted.
	 * 
	 * @return the submission time
	 */
	public String getSubmissionTime() {
		return submissionTime;
	}

	/**
	 * Sets the time the purge request was accepted.
	 * 
	 * @param submissionTime the submission time to set
	 */
	public void setSubmissionTime(String submissionTime) {
		this.submissionTime = submissionTime;
	}

	/**
	 * Gets the time the purge request was completed.
	 * 
	 * @return the completion time
	 */
	public String getCompletionTime() {
		return completionTime;
	}

	/**
	 * Sets the time the purge request was completed.
	 * 
	 * @param completionTime the completion time to set
	 */
	public void setCompletionTime(String completionTime) {
		this.completionTime = completionTime;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, Constants.TO_STRING_STYLE);
	}

}
