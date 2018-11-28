package com.learning.spring.basic.annotation.bean.componentconig.config;

import com.learning.spring.basic.annotation.bean.componentconig.bean.ComponentBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ComponentConfig {

  @Bean
  public ComponentBean componentBean() {
    return new ComponentBean();
  }

}
