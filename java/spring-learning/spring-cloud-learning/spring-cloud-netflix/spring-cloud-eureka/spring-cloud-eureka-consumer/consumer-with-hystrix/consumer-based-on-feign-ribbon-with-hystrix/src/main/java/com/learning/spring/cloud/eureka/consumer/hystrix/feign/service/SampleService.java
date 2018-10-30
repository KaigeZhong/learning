package com.learning.spring.cloud.eureka.consumer.hystrix.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Feign 是一个声明web服务客户端
 * feign包含了ribbon
 */
@FeignClient(value = "spring-cloud-eureka-provider",fallback = SampleServiceHystrix.class)
public interface SampleService {
    @RequestMapping(value = "/provider",method = RequestMethod.GET)
    String callService();
}
