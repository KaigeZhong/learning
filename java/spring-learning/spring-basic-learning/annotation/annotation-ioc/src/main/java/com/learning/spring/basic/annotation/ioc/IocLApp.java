package com.learning.spring.basic.annotation.ioc;

import com.learning.spring.basic.annotation.ioc.bean.BeanA;
import com.learning.spring.basic.annotation.ioc.config.BeanConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class IocLApp {
  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(BeanConfig.class);
    BeanA beanA = (BeanA) ctx.getBean("beanA");

    System.out.println(beanA.getBeanB());
  }
}
