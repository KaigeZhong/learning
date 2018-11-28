package com.learning.spring.boot.basic.conditional.conditiononclass.config;

import com.learning.spring.boot.basic.conditional.conditiononclass.bean.ConditionBean;
import com.learning.spring.boot.basic.conditional.conditiononclass.condition.ClassCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ClassCondition.class)
public class ConditionOnClassConfig {
  @Bean
  public ConditionBean conditionBean() {
    return new ConditionBean();
  }
}
