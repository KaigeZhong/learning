package com.bean.annotation.configuration.Import;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationByImported {

  @Bean
  public BeanByImported beanByImported() {
    return new BeanByImported();
  }
}
