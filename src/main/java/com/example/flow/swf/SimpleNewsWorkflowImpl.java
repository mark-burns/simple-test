package com.example.flow.swf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.amazonaws.services.simpleworkflow.flow.ActivitySchedulingOptions;
import com.amazonaws.services.simpleworkflow.flow.ActivityTaskFailedException;
import com.amazonaws.services.simpleworkflow.flow.annotations.Asynchronous;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.core.Settable;

public class SimpleNewsWorkflowImpl implements SimpleNewsWorkflow {
	
	private static Log log = LogFactory.getLog(SimpleNewsWorkflowImpl.class);
	
	@Autowired
	SimpleNewsActivitiesClient activitiesClient;
	
	private String state = "Started";
	
	@Override
	public void processUnit(String s3bucket, String s3key) {
		log.info("WORKFLOW: entered processUnit");

		// Step 1: Process the import zip
		final Promise<String> importActivityResult = activitiesClient.processZip(s3bucket, s3key);
		
		log.info("WORKFLOW: exiting processUnit");
	}
	
    @Override
    public String getState() {
        return state;
    }
    
    @Asynchronous
    private void setCompletedState(Promise<String> ar) {
    	state = "Completed";
    }
}
