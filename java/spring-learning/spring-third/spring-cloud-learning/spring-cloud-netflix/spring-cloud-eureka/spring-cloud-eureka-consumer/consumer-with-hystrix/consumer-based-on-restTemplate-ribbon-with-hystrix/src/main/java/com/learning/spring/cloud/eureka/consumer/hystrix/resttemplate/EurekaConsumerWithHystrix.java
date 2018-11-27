package com.learning.spring.cloud.eureka.consumer.hystrix.resttemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EurekaConsumerWithHystrix {
    public static void main(String[] args) {
        SpringApplication.run(EurekaConsumerWithHystrix.class, args);
    }
}

