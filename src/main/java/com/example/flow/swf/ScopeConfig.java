package com.example.flow.swf;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.simpleworkflow.flow.spring.WorkflowScope;

@Configuration
public class ScopeConfig {

	@Bean
	public static CustomScopeConfigurer customScope() {
		CustomScopeConfigurer configurer = new CustomScopeConfigurer();
		Map<String, Object> workflowScope = new HashMap<String, Object>();
		workflowScope.put("workflow", new WorkflowScope());
		configurer.setScopes(workflowScope);

		return configurer;
	}

}
