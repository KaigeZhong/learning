package com.bean.parentcontext;

import com.bean.parentcontext.bean.ParentBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:parentContext/parent.properties")
public class ParentConfig {
    @Bean
    public ParentBean parentBean() {
        return new ParentBean();
    }
}
