package com.example.flow.swf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.flow.spring.SpringActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.spring.SpringWorkflowWorker;

@Configuration
public class ConfigurationForSwfAdvanced {

	private static final Log log = LogFactory.getLog(ConfigurationForSwfAdvanced.class);
	
	@Autowired
	WorkflowSettings workflowSettings;
	
	@Autowired
	AmazonSimpleWorkflow service;
	
	@Autowired
	SimpleNewsActivities simpleNewsActivities;

	@Autowired
	SimpleNewsWorkflow simpleNewsWorkflow;

	@Bean(name = "activityWorker")
	public SpringActivityWorker activityWorker() {

		SpringActivityWorker aw = new SpringActivityWorker(service,
				workflowSettings.getDomain(),
				workflowSettings.getTaskList());
		try {
			// Default PollThreadCount is 1 but we could change it here
			aw.setPollThreadCount(workflowSettings.getActivityWorkerPollThreadCount());
			// For testing we can set the number of concurrent threads
			aw.setTaskExecutorThreadPoolSize(workflowSettings.getActivityWorkerExecThreadCount());
			aw.setTerminationTimeoutSeconds(1);
			aw.addActivitiesImplementation(simpleNewsActivities);
			log.info("Starting activity worker...");
			aw.start();
		} catch (Exception e) {
			log.error("Exception while creating the activity worker.", e);
		}
		
		return aw;
	}

	@Bean(name = "workflowWorker")
	public SpringWorkflowWorker workflowWorker() {

		if (workflowSettings.isIncludeDecider()) {
			SpringWorkflowWorker wfw = new SpringWorkflowWorker(service,
					workflowSettings.getDomain(),
					workflowSettings.getTaskList());
			try {
				wfw.setTerminationTimeoutSeconds(1);
				wfw.addWorkflowImplementation(simpleNewsWorkflow);
				log.info("Starting workflow worker...");
				wfw.start();
			} catch (Exception e) {
				log.error("Exception while creating the workflow workers.", e);
			}
			
			return wfw;
		}
		
		return null;
	}

}
