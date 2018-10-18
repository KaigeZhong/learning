package com.learning.spring.basic.annotation.properties.propertysource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class ConfigurationBeanProperty {

  @Bean
  public BeanByBeanAnnotationPropertySource beanByBeanAnnotationPropertySource() {
    return new BeanByBeanAnnotationPropertySource();
  }
}
