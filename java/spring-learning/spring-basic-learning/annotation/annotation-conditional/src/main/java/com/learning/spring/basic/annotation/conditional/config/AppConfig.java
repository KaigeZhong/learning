package com.learning.spring.basic.annotation.conditional.config;

import com.learning.spring.basic.annotation.conditional.bean.ConditionalBean;
import com.learning.spring.basic.annotation.conditional.condition.CustomCondition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @Conditional({CustomCondition.class})
    public ConditionalBean conditionalBean() {
        return new ConditionalBean();
    }

}
