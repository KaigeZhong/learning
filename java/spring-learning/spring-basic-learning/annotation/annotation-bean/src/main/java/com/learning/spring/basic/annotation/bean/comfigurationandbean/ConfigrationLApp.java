package com.learning.spring.basic.annotation.bean.comfigurationandbean;

import com.learning.spring.basic.annotation.bean.comfigurationandbean.bean.BeanByBeanAnnotation;
import com.learning.spring.basic.annotation.bean.comfigurationandbean.config.ConfigurationBeanConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigrationLApp {
  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationBeanConfig.class);
    BeanByBeanAnnotation beanByBeanAonotation =
      (BeanByBeanAnnotation) ctx.getBean("beanByBeanAonotation");
    System.out.println(beanByBeanAonotation);
  }
}
