package com.example.flow.swf;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.amazonaws.services.simpleworkflow.flow.ActivityExecutionContextProvider;
import com.amazonaws.services.simpleworkflow.flow.ActivityExecutionContextProviderImpl;

public class SimpleNewsActivitiesImpl implements SimpleNewsActivities {
    final ActivityExecutionContextProvider contextProvider;

    private static Log log = LogFactory.getLog(SimpleNewsActivitiesImpl.class);

    public SimpleNewsActivitiesImpl() {
        this(new ActivityExecutionContextProviderImpl());
    }

    public SimpleNewsActivitiesImpl(ActivityExecutionContextProvider contextProvider) {
        this.contextProvider = contextProvider;
        log.info("Created SimpleNewsActivitiesImpl...");
    }

    @Override
    public String processZip(String s3bucket, String s3key) throws InterruptedException {

        String swfTaskToken = this.getTaskToken();
        log.info(String.format("ACTIVITY: processZip: %s : %s : %s", s3bucket, s3key, swfTaskToken));

        return "Done";
    }

    protected String getTaskToken() {
        try {
            return this.contextProvider.getActivityExecutionContext().getTaskToken();
        } catch (IllegalStateException e) {
            throw e;
        }
    }

}
