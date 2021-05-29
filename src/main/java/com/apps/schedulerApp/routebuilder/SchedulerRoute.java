package com.apps.schedulerApp.routebuilder;

import  com.apps.schedulerApp.api.inbound.ConfigurationService;
import  com.apps.schedulerApp.api.model.ConfigurationsResource;

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

    private static String DEFAULT_INTERVAL = "DefaultInterval";
    private static String MAXIMUM_INTERVAL = "MaximumInterval";

    @Value("${quartz2.app-default-scheduler}")
    private String rawDefaultIntervalExpression;

    @Value("${quartz2.app-maximum-scheduler}")
    private String rawMaximumIntervalScheduler;

    @Autowired
    private DefaultIntervalEventProcessor defaultIntervalEventProcessor;

    @Autowired
    private MaximumIntervalEventProcessor maximumIntervalEventProcessor;

    @Autowired
    private ConfigurationService configService;

    @Override
    public void configure() throws Exception {
        LOG.debug("Entering method " + Thread.currentThread().getStackTrace()[1].getMethodName());

        ConfigurationsResource globalConfig = configService.getConfigurations();

        LOG.info("Global configurations: " + globalConfig.getConfigurationsMap());

        String defaultIntervalValue = globalConfig.getConfigurationsMap().get(DEFAULT_INTERVAL);
        if((null == defaultIntervalValue) || (defaultIntervalValue.length() <= 0)) {
        	defaultIntervalValue = "2";
        }

        String swipeIntervalSchedulerExpression = String.format(rawDefaultIntervalExpression, defaultIntervalValue);

        from(swipeIntervalSchedulerExpression)
            .log(LoggingLevel.INFO, "Default interval processing started for......")
            .process(defaultIntervalEventProcessor);

        String maximumIntervalValue = globalConfig.getConfigurationsMap().get(MAXIMUM_INTERVAL);
        if((null == maximumIntervalValue) || (maximumIntervalValue.length() <= 0)) {
        	maximumIntervalValue = "10";
        }

        String maximumThresholdSchedulerExpression = String.format(rawMaximumIntervalScheduler, maximumIntervalValue);
        from(maximumThresholdSchedulerExpression)
                .log(LoggingLevel.INFO, "Maximum interval processing started for......")
                .process(maximumIntervalEventProcessor);

        LOG.debug("Exiting method " + Thread.currentThread().getStackTrace()[1].getMethodName());
    }
}