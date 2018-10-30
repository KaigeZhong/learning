package com.learning.spring.cloud.eureka.consumer.hystrix.resttemplate.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SampleService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String call() {
        return restTemplate.getForObject("http://spring-cloud-eureka-provider/provider", String.class);
    }
    public String hiError() {
        return "hiError";
    }
}
