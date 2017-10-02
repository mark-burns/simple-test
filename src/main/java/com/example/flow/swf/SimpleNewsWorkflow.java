package com.example.flow.swf;

import com.amazonaws.services.simpleworkflow.flow.annotations.Execute;
import com.amazonaws.services.simpleworkflow.flow.annotations.GetState;
import com.amazonaws.services.simpleworkflow.flow.annotations.Workflow;
import com.amazonaws.services.simpleworkflow.flow.annotations.WorkflowRegistrationOptions;

@Workflow
@WorkflowRegistrationOptions(defaultExecutionStartToCloseTimeoutSeconds = 86400 * 7,
							defaultTaskStartToCloseTimeoutSeconds = 60)
public interface SimpleNewsWorkflow {

   @Execute(version = "4.5")
   public void processUnit(String s3bucket, String s3key);
   
   @GetState
   public String getState();
}

