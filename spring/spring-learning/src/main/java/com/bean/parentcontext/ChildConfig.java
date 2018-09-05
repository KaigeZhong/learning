package com.bean.parentcontext;

import com.bean.parentcontext.bean.ChildBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:parentContext/child.properties")
public class ChildConfig {
    @Bean
    public ChildBean childBean() {
        return new ChildBean();
    }
}
