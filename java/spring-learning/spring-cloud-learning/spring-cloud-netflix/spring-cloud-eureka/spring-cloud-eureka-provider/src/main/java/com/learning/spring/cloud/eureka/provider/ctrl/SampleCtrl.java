package com.learning.spring.cloud.eureka.provider.ctrl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class SampleCtrl {

    @Value("${server.port}")
    String port;
    @Value("${spring.application.name}")
    String applicationName;

    @RequestMapping("/provider")
    public String home() throws UnknownHostException {
        return "from: " + InetAddress.getLocalHost().getHostAddress() + ":" + port + " -> service: " + applicationName;
    }
}
