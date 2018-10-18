package com.learning.spring.boot.basic.autoconfig.conditiononclass;

import com.learning.spring.boot.basic.autoconfig.conditiononclass.condition.BeanCondition;
import com.learning.spring.boot.basic.autoconfig.conditiononclass.condition.ClassCondition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ClassCondition.class)
public class ConditionOnClassConfig {
  @Bean
  @ConditionalOnClass(BeanCondition.class)
  public ConditionBean conditionBean() {
    return new ConditionBean();
  }
}
