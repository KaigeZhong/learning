package com.learning.spring.basic.annotation.applicationListener;

import com.learning.spring.basic.annotation.applicationListener.config.ListenerConfig;
import com.learning.spring.basic.annotation.applicationListener.listener.TestEvent;
import com.learning.spring.basic.annotation.applicationListener.listener.TestPublish;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * spring只是引入了监听机制供应用使用，目前spring容器自身并没有使用
 */
public class ListenerLApp {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ListenerConfig.class);
        TestPublish testPublish = (TestPublish) ctx.getBean("testPublish");
        testPublish.publishEvent(new TestEvent(testPublish,"test event ke"));
    }
}
