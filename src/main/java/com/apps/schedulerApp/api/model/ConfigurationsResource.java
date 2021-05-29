package com.apps.schedulerApp.api.model;

import lombok.Data;

import java.util.HashMap;

@Data
public class ConfigurationsResource {
    private HashMap<String, String> configurations = new HashMap<String, String>();

    public ConfigurationsResource() {
    }

    public HashMap<String,String> getConfigurationsMap() {
        return configurations;
    }
}