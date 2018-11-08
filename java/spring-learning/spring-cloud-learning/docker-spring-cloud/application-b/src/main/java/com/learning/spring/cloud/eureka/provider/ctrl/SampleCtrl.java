package com.learning.spring.cloud.eureka.provider.ctrl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleCtrl {

    @Value("${spring.application.name}")
    String applicationName;

    @RequestMapping("/")
    public String info() {
        return "this is " + applicationName;
    }
}
