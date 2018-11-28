package com.learning.spring.basic.annotation.bean.Import.config;

import com.learning.spring.basic.annotation.bean.Import.bean.BeanByImported;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationByImported {

  @Bean
  public BeanByImported beanByImported() {
    return new BeanByImported();
  }
}
