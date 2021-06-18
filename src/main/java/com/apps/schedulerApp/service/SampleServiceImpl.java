package com.apps.schedulerApp.service;

import  org.slf4j.Logger;
import  org.slf4j.LoggerFactory;
import  org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {
    private static Logger LOG = LoggerFactory.getLogger(SampleServiceImpl.class);

    public SampleServiceImpl() {
    }

    @Override
    public void process() throws Exception {
    	LOG.info("Processing: " + Thread.currentThread().getStackTrace()[1].getMethodName());
    	LOG.info("Will invoke configured persistance service at a later time!!");
    }
}