package com.example.flow.swf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Scope;
import org.springframework.retry.annotation.EnableRetry;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.amazonaws.services.simpleworkflow.model.DomainInfo;
import com.amazonaws.services.simpleworkflow.model.DomainInfos;
import com.amazonaws.services.simpleworkflow.model.ListDomainsRequest;
import com.amazonaws.services.simpleworkflow.model.RegisterDomainRequest;
import com.amazonaws.services.simpleworkflow.model.RegistrationStatus;

@Configuration
@EnableRetry
public class ConfigurationForSwf {

	public static final Log log = LogFactory.getLog(ConfigurationForSwf.class);
	
	@Autowired
	WorkflowSettings workflowSettings;
	
	boolean setupSwf = true;

	@Bean(name = "activitiesClient")
	@Scope("workflow")
	public SimpleNewsActivitiesClient simpleNewsActivitiesClient() {
		return new SimpleNewsActivitiesClientImpl();
	}

	@Bean(name = "service")
	public AmazonSimpleWorkflow swfService() {
		log.info("Initializing AWS SWF...");
		ClientConfiguration config = new ClientConfiguration().withSocketTimeout(workflowSettings.getClientSocketTimeout());
		AmazonSimpleWorkflow service = new AmazonSimpleWorkflowClient(config);
		service.setEndpoint(workflowSettings.getEndpoint());
		
		// Make sure domain is ready
		checkDomainRegistration(service);

		return service;
	}

	private void checkDomainRegistration(AmazonSimpleWorkflow service) {
		if (setupSwf) {
			try {
				// Search for domain
				boolean found = false;
				ListDomainsRequest request = new ListDomainsRequest().withMaximumPageSize(30);
				request.setRegistrationStatus(RegistrationStatus.REGISTERED);
				DomainInfos domains = service.listDomains(request);
				for (DomainInfo di : domains.getDomainInfos()) {
					if (di.getName().equals(workflowSettings.getDomain())) {
						found = true;
						break;
					}
				}
				
				if (!found) {
					registerDomain(service);
				}
			} catch (Exception t) {
				log.error("Exception while searching for domain.", t);
			}
		}
	}
	
	private void registerDomain(AmazonSimpleWorkflow service) {
		
		try {
			RegisterDomainRequest registerDomainRequest = new RegisterDomainRequest();
			registerDomainRequest.setName(workflowSettings.getDomain());
			registerDomainRequest.setDescription("Domain for us " + workflowSettings.getDomain());
			registerDomainRequest.setWorkflowExecutionRetentionPeriodInDays(
					workflowSettings.getWorkflowExecutionRetentionPeriodInDays());
			service.registerDomain(registerDomainRequest);
		} catch (Exception t) {
			log.error("Exception while registering the SWF Domain.", t);
		}
	}

	@Bean(name = "simpleNewsActivities")
	@DependsOn({ "service" })
	public SimpleNewsActivities simpleNewsActivities() {
		return new SimpleNewsActivitiesImpl();
	}

	@Bean(name = "simpleNewsWorkflow")
	@DependsOn({ "service" })
	@Scope("workflow")
	public SimpleNewsWorkflow simpleNewsWorkflow() {
		return new SimpleNewsWorkflowImpl();
	}
}
