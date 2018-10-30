package com.learning.spring.cloud.eureka.consumer.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumerBastedOnFeign {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerBastedOnFeign.class, args);
    }
}
