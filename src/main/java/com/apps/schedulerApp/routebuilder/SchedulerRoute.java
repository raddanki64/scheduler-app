package com.apps.schedulerApp.routebuilder;

import  org.apache.camel.LoggingLevel;
import  org.apache.camel.builder.RouteBuilder;
import  org.slf4j.Logger;
import  org.slf4j.LoggerFactory;
import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Component;

@Component
public class SchedulerRoute extends RouteBuilder {
    private static Logger LOG = LoggerFactory.getLogger(SchedulerRoute.class);

    private String rawDefaultIntervalExpression = "quartz2://appGroup/sampleTimer?cron=0+0/%s+1-23+?+*+MON-SUN";
    
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