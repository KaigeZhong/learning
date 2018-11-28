package com.learning.spring.basic.annotation.parentcontext.config;

import com.learning.spring.basic.annotation.parentcontext.bean.ParentBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:parent.properties")
public class ParentConfig {
    @Bean
    public ParentBean parentBean() {
        return new ParentBean();
    }
}
