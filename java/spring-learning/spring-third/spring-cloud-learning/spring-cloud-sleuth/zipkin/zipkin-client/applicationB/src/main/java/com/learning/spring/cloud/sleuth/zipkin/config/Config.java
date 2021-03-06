package com.learning.spring.cloud.sleuth.zipkin.config;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
  //只是配置restTemplate与zipkin无关
  @Bean
  public RestTemplate getRestTemplate(){
    return new RestTemplate();
  }

  @Bean
  public Sampler defaultSampler() {
    return Sampler.ALWAYS_SAMPLE;
  }
}
