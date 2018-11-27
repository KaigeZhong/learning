package com.learning.spring.cloud.eureka.provider.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SampleCtrl {

    @Value("${spring.application.name}")
    private String applicationName;
    @Value("${foo}")
    private String foo;

    @RequestMapping("/")
    public String info() {
        return "this is " + applicationName;
    }

    @RequestMapping("/config")
    public String config() {
        return "foo: " + foo;
    }


    @Autowired
    private RestTemplate restTemplate;
    @RequestMapping("/zipkin")
    public String zipKinTrace() {
        return applicationName + " --> " + restTemplate.getForObject("http://application-c/zipkin",String.class);
    }
}
