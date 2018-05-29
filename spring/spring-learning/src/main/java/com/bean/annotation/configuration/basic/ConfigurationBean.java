package com.bean.annotation.configuration.basic;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ConfigurationBean.class)
public class ConfigurationBean {

  @Bean
  public BeanByBeanAnnotation beanByBeanAonotation() {
    return new BeanByBeanAnnotation();
  }
}
