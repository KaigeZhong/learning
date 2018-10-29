package com.learning.spring.cloud.eureka.consumer.hystrix.feign.ctrl;

import com.learning.spring.cloud.eureka.consumer.hystrix.feign.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleCtrl {

    @Autowired
    private SampleService sampleService;

    @RequestMapping("/")
    public String test() {
        return sampleService.callService();
    }
}
