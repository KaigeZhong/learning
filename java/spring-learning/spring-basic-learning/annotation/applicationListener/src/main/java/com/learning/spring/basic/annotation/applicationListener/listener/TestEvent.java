package com.learning.spring.basic.annotation.applicationListener.listener;

import org.springframework.context.ApplicationEvent;


public class TestEvent extends ApplicationEvent {

    private String key;

    public TestEvent(Object source, String key) {
        super(source);
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
