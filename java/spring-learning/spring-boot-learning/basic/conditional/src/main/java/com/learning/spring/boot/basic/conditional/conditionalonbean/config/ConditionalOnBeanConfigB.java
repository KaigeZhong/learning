package com.learning.spring.boot.basic.conditional.conditionalonbean.config;

import com.learning.spring.boot.basic.conditional.conditionalonbean.bean.ConditionalOnBeanBeanB;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConditionalOnBeanConfigB {
    @Bean
    public ConditionalOnBeanBeanB conditionalOnBeanBeanB() {
        return new ConditionalOnBeanBeanB();
    }
}
