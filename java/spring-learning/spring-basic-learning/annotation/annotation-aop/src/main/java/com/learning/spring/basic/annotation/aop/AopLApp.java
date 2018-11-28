package com.learning.spring.basic.annotation.aop;

import com.learning.spring.basic.annotation.aop.bean.BusinessService;
import com.learning.spring.basic.annotation.aop.config.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopLApp {
  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(Config.class);
    BusinessService businessService = (BusinessService) ctx.getBean("businessService");
    businessService.say();
  }
}
