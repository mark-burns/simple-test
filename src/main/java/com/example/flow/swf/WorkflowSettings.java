package com.example.flow.swf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource({"classpath:application.properties"})
public class WorkflowSettings {

	@Value("${swf.domain}")
	private String domain;
	
	@Value("${swf.endpoint}")
	private String endpoint;
	
	@Value("${swf.tasklist}")
	private String taskList;
	
	@Value("${include.decider}")
	private boolean includeDecider;
	
	@Value("${import.bucket.name}")
	private String importBucketName;
	
	@Value("${activity.extracttext.unit.size}")
	private int extractTextUnitSize;

	@Value("${activity.imagegen.unit.size}")
	private int imageGenUnitSize;

	@Value("${activity.worker.poll.thread.count}")
	private int activityWorkerPollThreadCount;
	
	@Value("${activity.worker.exec.thread.count}")
	private int activityWorkerExecThreadCount;
	
	@Value("${activity.imagegen.thread.count}")
	private int imageGenThreadCount;
	
	@Value("${activity.ocr.thread.count}")
	private int ocrThreadCount;

	@Value("${workflowExecutionRetentionPeriodInDays}")
	private String workflowExecutionRetentionPeriodInDays;
	
	@Value("${clientSocketTimeout}")
	private int clientSocketTimeout;

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getTaskList() {
		return taskList;
	}

	public void setTaskList(String taskList) {
		this.taskList = taskList;
	}

	public boolean isIncludeDecider() {
		return includeDecider;
	}

	public void setIncludeDecider(boolean includeDecider) {
		this.includeDecider = includeDecider;
	}

	public String getImportBucketName() {
		return importBucketName;
	}

	public void setImportBucketName(String importBucketName) {
		this.importBucketName = importBucketName;
	}

	public int getExtractTextUnitSize() {
		return extractTextUnitSize;
	}

	public void setExtractTextUnitSize(int extractTextUnitSize) {
		this.extractTextUnitSize = extractTextUnitSize;
	}

	public int getImageGenUnitSize() {
		return imageGenUnitSize;
	}

	public void setImageGenUnitSize(int imageGenUnitSize) {
		this.imageGenUnitSize = imageGenUnitSize;
	}

	public int getActivityWorkerPollThreadCount() {
		return activityWorkerPollThreadCount;
	}

	public void setActivityWorkerPollThreadCount(int activityWorkerPollThreadCount) {
		this.activityWorkerPollThreadCount = activityWorkerPollThreadCount;
	}

	public int getActivityWorkerExecThreadCount() {
		return activityWorkerExecThreadCount;
	}

	public void setActivityWorkerExecThreadCount(int activityWorkerExecThreadCount) {
		this.activityWorkerExecThreadCount = activityWorkerExecThreadCount;
	}

	public int getImageGenThreadCount() {
		return imageGenThreadCount;
	}

	public void setImageGenThreadCount(int imageGenThreadCount) {
		this.imageGenThreadCount = imageGenThreadCount;
	}

	public int getOcrThreadCount() {
		return ocrThreadCount;
	}

	public void setOcrThreadCount(int ocrThreadCount) {
		this.ocrThreadCount = ocrThreadCount;
	}
	
	public String getWorkflowExecutionRetentionPeriodInDays() {
		return workflowExecutionRetentionPeriodInDays;
	}

	public void setWorkflowExecutionRetentionPeriodInDays(String workflowExecutionRetentionPeriodInDays) {
		this.workflowExecutionRetentionPeriodInDays = workflowExecutionRetentionPeriodInDays;
	}

	public int getClientSocketTimeout() {
		return clientSocketTimeout;
	}

	public void setClientSocketTimeout(int clientSocketTimeout) {
		this.clientSocketTimeout = clientSocketTimeout;
	}
}
