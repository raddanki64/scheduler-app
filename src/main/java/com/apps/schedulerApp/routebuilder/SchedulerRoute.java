package com.apps.schedulerApp.routebuilder;

import  org.apache.camel.LoggingLevel;
import  org.apache.camel.builder.RouteBuilder;
import  org.slf4j.Logger;
import  org.slf4j.LoggerFactory;
import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.beans.factory.annotation.Value;
import  org.springframework.stereotype.Component;

@Component
public class SchedulerRoute extends RouteBuilder {
    private static Logger LOG = LoggerFactory.getLogger(SchedulerRoute.class);

    @Value("${quartz2.app-default-scheduler}")
    private String rawDefaultIntervalExpression;
    
    @Autowired
    private DefaultIntervalEventProcessor defaultIntervalEventProcessor;

    @Override
    public void configure() throws Exception {
        LOG.debug("Entering method " + Thread.currentThread().getStackTrace()[1].getMethodName());

        String defaultIntervalValue = "1";
        if((null == defaultIntervalValue) || (defaultIntervalValue.length() <= 0)) {
        	
        }

        String swipeIntervalSchedulerExpression = String.format(rawDefaultIntervalExpression, defaultIntervalValue);
        from(swipeIntervalSchedulerExpression)
            .log(LoggingLevel.INFO, "Default interval processing started for......")
            .process(defaultIntervalEventProcessor);

        LOG.debug("Exiting method " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}