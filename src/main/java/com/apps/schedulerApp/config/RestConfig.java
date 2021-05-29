package com.apps.schedulerApp.config;

import  org.apache.http.Header;
import  org.apache.http.client.config.RequestConfig;
import  org.apache.http.impl.client.HttpClientBuilder;
import  org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import  org.apache.http.message.BasicHeader;
import  org.springframework.beans.factory.annotation.Value;
import  org.springframework.context.annotation.Bean;
import  org.springframework.context.annotation.Configuration;
import  org.springframework.http.HttpHeaders;
import  org.springframework.http.client.ClientHttpRequestFactory;
import  org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import  org.springframework.web.client.RestTemplate;
import  org.apache.http.client.HttpClient;

import  java.util.Collections;
import  java.util.List;

@Configuration
public class RestConfig {

    @Value("${rest-template.max-connections}")
    private String maxConnections;

    @Value("${rest-template.max-connections-per-route}")
    private String maxConnectionsPerRoute;

    @Value("${rest-template.http-client.connection-timeout}")
    private String connectionTimeout;

    @Value("${rest-template.http-client.socket-timeout}")
    private String socketTimeout;

    @Value("${rest-template.http-client.connection-request-timeout}")
    private String connectionRequestTimeout;

    private HttpClient createHttpClient() {
        //Create HttpClient Builder
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        //Set Connection Manager
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(Integer.parseInt(maxConnections));
        connectionManager.setDefaultMaxPerRoute(Integer.parseInt(maxConnectionsPerRoute));
        httpClientBuilder.setConnectionManager(connectionManager);

        //Set Request Configuration
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(Integer.parseInt(connectionTimeout))
                .setSocketTimeout(Integer.parseInt(socketTimeout))
                .setConnectionRequestTimeout(Integer.parseInt(connectionRequestTimeout)).build();
        httpClientBuilder.setDefaultRequestConfig(config);

        //Set Default Headers
        List<Header> requestHeaders = Collections.singletonList(new BasicHeader(HttpHeaders.ACCEPT_ENCODING, "gzip"));
        httpClientBuilder.setDefaultHeaders(requestHeaders);

        return httpClientBuilder.build();
    }

    private ClientHttpRequestFactory createHttpRequestFactory() {
        return new HttpComponentsClientHttpRequestFactory(createHttpClient());
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(createHttpRequestFactory());
    }
}