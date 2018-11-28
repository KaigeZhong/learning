package com.learning.spring.basic.annotation.conditional;

import com.learning.spring.basic.annotation.conditional.bean.ConditionalBean;
import com.learning.spring.basic.annotation.conditional.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConditionLApp {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ConditionalBean testPublish = (ConditionalBean) ctx.getBean("conditionalBean");
        System.out.println(testPublish);
    }
}
