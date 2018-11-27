package com.learning.spring.cloud.eureka.consumer.hystrix.resttemplate.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableHystrix
public class RestTemplateConfig {
    /**
     * restTemplate + ribbon
     * ribbon为客户端负载均衡器
     */
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
