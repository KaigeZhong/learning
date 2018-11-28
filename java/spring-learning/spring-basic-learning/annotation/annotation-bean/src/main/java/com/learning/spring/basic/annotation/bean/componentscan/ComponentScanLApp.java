package com.learning.spring.basic.annotation.bean.componentscan;

import com.learning.spring.basic.annotation.bean.componentscan.bean.BeanByScan;
import com.learning.spring.basic.annotation.bean.componentscan.config.ComponentScanConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComponentScanLApp {
  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(ComponentScanConfig.class);

    BeanByScan beanByScan = (BeanByScan) ctx.getBean("beanByScan");
    System.out.println(beanByScan);
  }
}
