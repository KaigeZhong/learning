package com.learning.spring.basic.annotation.applicationListener.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/*
等价于
public class TestEventHandler implements ApplicationListener<TestEvent> {
    @Override
    public void onApplicationEvent(TestEvent testEvent) {
        System.out.println(".......开始.......");
        System.out.println("testEvent key:"+testEvent.getKey());
        System.out.println(".......结束.....");
    }
}
 */
public class TestEventHandler {

    @Async
    @EventListener
    public void handleTestEvent(TestEvent testEvent) {

        System.out.println(".......开始.......");
        System.out.println("testEvent key:"+testEvent.getKey());
        System.out.println(".......结束.....");
    }
}