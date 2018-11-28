package com.learning.spring.basic.annotation.bean.comfigurationandbean.config;

import com.learning.spring.basic.annotation.bean.comfigurationandbean.bean.BeanByBeanAnnotation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationBeanConfig {

  @Bean
  public BeanByBeanAnnotation beanByBeanAonotation() {
    return new BeanByBeanAnnotation();
  }
}
