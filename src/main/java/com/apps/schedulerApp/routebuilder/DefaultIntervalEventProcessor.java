package com.apps.schedulerApp.routebuilder;

import  com.apps.schedulerApp.service.SampleService;

import  org.slf4j.Logger;
import  org.slf4j.LoggerFactory;
import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Component;
import  org.apache.camel.Exchange;
import  org.apache.camel.Processor;

@Component
public class DefaultIntervalEventProcessor implements Processor {
    private static Logger LOG = LoggerFactory.getLogger(DefaultIntervalEventProcessor.class);

    @Autowired
    private SampleService    sampleService;

    public void process(Exchange exchange) throws Exception {
        LOG.info("DefaultIntervalEventProcessor saying hello!!");
        sampleService.process();
    }
}
