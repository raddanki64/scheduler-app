package com.apps.schedulerApp.api.inbound;

import  com.apps.schedulerApp.api.model.ConfigurationsResource;

import  org.slf4j.Logger;
import  org.slf4j.LoggerFactory;
import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.beans.factory.annotation.Value;
import  org.springframework.stereotype.Service;
import  org.springframework.web.client.RestClientException;
import  org.springframework.web.client.RestTemplate;

@Service
public class ConfigurationServiceImpl implements  ConfigurationService {
    private static Logger LOG = LoggerFactory.getLogger(ConfigurationServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${configServiceUrl}")
    private String configServiceUrl;

    public ConfigurationsResource getConfigurations() {
        try {
            return restTemplate.getForObject(configServiceUrl + "/configurations", ConfigurationsResource.class);
        }
        catch (RestClientException e) {
            LOG.error("Error while obtaining global configurations: {}", e);
        }

        return null;
    }
}
