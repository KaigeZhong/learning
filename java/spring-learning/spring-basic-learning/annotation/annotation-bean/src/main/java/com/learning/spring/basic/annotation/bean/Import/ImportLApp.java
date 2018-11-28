package com.learning.spring.basic.annotation.bean.Import;

import com.learning.spring.basic.annotation.bean.Import.bean.BeanByImported;
import com.learning.spring.basic.annotation.bean.Import.config.ConfigurationImport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ImportLApp {
  public static void main(String[] args) {
    ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationImport.class);
    BeanByImported beanByImported = (BeanByImported) ctx.getBean("beanByImported");

    System.out.println(beanByImported);
  }
}
