package com.learning.spring.basic.annotation.bean.Import;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationByImported {

  @Bean
  public BeanByImported beanByImported() {
    return new BeanByImported();
  }
}
