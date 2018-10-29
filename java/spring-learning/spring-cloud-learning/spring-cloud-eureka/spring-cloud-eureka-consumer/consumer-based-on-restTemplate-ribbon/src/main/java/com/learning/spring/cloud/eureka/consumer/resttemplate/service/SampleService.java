package com.learning.spring.cloud.eureka.consumer.resttemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SampleService {
    @Autowired
    private RestTemplate restTemplate;

    public String call() {
        return restTemplate.getForObject("http://spring-cloud-eureka-provider/provider", String.class);
    }
}
