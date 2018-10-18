package com.learning.spring.basic.annotation.bean.componentconig;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ComponentConfig {

  @Bean
  public ComponentBean componentBean() {
    return new ComponentBean();
  }

}
