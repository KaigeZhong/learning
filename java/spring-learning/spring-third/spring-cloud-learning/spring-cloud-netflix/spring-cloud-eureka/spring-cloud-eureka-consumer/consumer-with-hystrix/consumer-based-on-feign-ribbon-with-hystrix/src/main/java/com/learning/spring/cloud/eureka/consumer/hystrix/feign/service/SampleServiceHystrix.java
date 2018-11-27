package com.learning.spring.cloud.eureka.consumer.hystrix.feign.service;

import org.springframework.stereotype.Component;

@Component
public class SampleServiceHystrix implements SampleService {
    @Override
    public String callService() {
        return "HiError";
    }
}
