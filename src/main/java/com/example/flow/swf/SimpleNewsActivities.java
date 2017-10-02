package com.example.flow.swf;

import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;
import com.amazonaws.services.simpleworkflow.flow.annotations.ExponentialRetry;

@Activities(version="4.5")
@ActivityRegistrationOptions(defaultTaskScheduleToStartTimeoutSeconds = 3600,
                             defaultTaskStartToCloseTimeoutSeconds = 600)
public interface SimpleNewsActivities {
   
	/**
	 * Unzip zip file stored in S3.
	 * 
	 * @param s3bucket The S3 bucket.
	 * @param s3key The S3 Key.
	 * @return returns a ActivityResult for the activity.
	 * @throws InterruptedException throws an exception if interrupted.
	 */
	@ActivityRegistrationOptions(defaultTaskScheduleToStartTimeoutSeconds = 3600,
            defaultTaskStartToCloseTimeoutSeconds = 600)
	@ExponentialRetry(initialRetryIntervalSeconds = 120, maximumAttempts = 2)
	public String processZip(String s3bucket, String s3key) throws InterruptedException, Exception;
	
}
