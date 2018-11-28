package com.learning.spring.basic.annotation.ioc.config;

import com.learning.spring.basic.annotation.ioc.bean.BeanA;
import com.learning.spring.basic.annotation.ioc.bean.BeanB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
  @Bean
  public BeanA beanA() {
    return new BeanA();
  }

  @Bean
  public BeanB beanB() {
    return new BeanB();
  }
}
