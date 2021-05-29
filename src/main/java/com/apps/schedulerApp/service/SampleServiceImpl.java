package com.apps.schedulerApp.service;

import  com.apps.schedulerApp.persistance.SqlServerService;

import  org.slf4j.Logger;
import  org.slf4j.LoggerFactory;
import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.stereotype.Service;

import  com.google.gson.Gson;

@Service
public class SampleServiceImpl implements SampleService {
    private static Logger LOG = LoggerFactory.getLogger(SampleServiceImpl.class);
    private static Gson gson = new Gson();

    @Autowired
    private SqlServerService persistanceService;

    public SampleServiceImpl() {
    }

    @Override
    public void process() throws Exception {
        persistanceService.process();
    }
}