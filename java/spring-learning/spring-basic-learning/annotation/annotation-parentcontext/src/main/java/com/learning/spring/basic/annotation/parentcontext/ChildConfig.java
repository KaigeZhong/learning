package com.learning.spring.basic.annotation.parentcontext;

import com.learning.spring.basic.annotation.parentcontext.bean.ChildBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:child.properties")
public class ChildConfig {
    @Bean
    public ChildBean childBean() {
        return new ChildBean();
    }
}
