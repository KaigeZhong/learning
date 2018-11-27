package com.learning.spring.basic.annotation.applicationListener.config;

import com.learning.spring.basic.annotation.applicationListener.listener.TestEventHandler;
import com.learning.spring.basic.annotation.applicationListener.listener.TestPublish;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
//@EnableAsync + @Async 可以让listener在独立线程执行，与事件发布者异步执行
@EnableAsync
public class ListenerConfig {
    @Bean
    public TestEventHandler testEventHandler() {
        return new TestEventHandler();
    }

    @Bean
    public TestPublish testPublish() {
        return new TestPublish();
    }
}
