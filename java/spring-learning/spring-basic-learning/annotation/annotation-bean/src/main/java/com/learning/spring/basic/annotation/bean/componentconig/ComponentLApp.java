package com.learning.spring.basic.annotation.bean.componentconig;

import com.learning.spring.basic.annotation.bean.componentconig.bean.ComponentBean;
import com.learning.spring.basic.annotation.bean.componentconig.config.ComponentConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ComponentLApp {
  public static void main(String[] args) {
    /*
        AnnotationConfigApplicationContext在初始化时会默认初始化this.reader = new AnnotatedBeanDefinitionReader(this);
        在初始化AnnotatedBeanDefinitionReader时，会去调用registerAnnotationConfigProcessors向容器中注入ConfigurationClassPostProcessor

        ConfigurationClassPostProcessor会去beanDefinitionMap获取所有已注册beandefinition（包括了spring自身注入的和用户注入的),只要beandefinition包括了@Component，@Configuration，@ComponentScan，@Import，@ImportResource便会处理:
        处理@PropertySource标签，用来解析属性文件并设置到Environment中。
        处理@ComponentScan标签，扫描package下的所有Class并进行迭代解析。
        处理@Import标签。
        处理@ImportResource标签。
        处理@Bean标签。
        @Component也会被当做@Configuration处理
         */
    ApplicationContext ctx = new AnnotationConfigApplicationContext(ComponentConfig.class);
    ComponentBean componentBean =
      (ComponentBean) ctx.getBean("componentBean");
    System.out.println(componentBean);
  }
}
