package com.bean.annotation.configuration.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBean {

  @Bean
  public BeanByBeanAnnotation beanByBeanAonotation() {
    return new BeanByBeanAnnotation();
  }
}
