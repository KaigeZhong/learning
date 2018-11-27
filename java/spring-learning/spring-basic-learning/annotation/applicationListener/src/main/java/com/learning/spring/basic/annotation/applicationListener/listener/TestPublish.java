package com.learning.spring.basic.annotation.applicationListener.listener;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

public class TestPublish implements ApplicationEventPublisherAware {

    private static ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        TestPublish.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishEvent(TestEvent testEvent) {
        applicationEventPublisher.publishEvent(testEvent);
    }
}