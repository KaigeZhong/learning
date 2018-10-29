package com.learning.spring.cloud.eureka.consumer.hystrix.feign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ConsumerBastedOnFeignWithHystrix {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerBastedOnFeignWithHystrix.class, args);
    }
}
