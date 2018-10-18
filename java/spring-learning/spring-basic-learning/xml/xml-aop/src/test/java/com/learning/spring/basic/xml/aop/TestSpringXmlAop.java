package com.learning.spring.basic.xml.aop;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringXmlAop {
    @Test
    public void testAopByxml() {
        // <aop:config>会生成AspectJAwareAdvisorAutoProxyCreator BeanPostProcessor
        // aop 代理处理发生在AspectJAwareAdvisorAutoProxyCreator BeanPostProcessor中，生成逻辑在AopProxy中
        ApplicationContext context = new ClassPathXmlApplicationContext("application-aop.xml");
        AopTargetObjectI proxy = (AopTargetObjectI) context.getBean("targetObject");
        proxy.targetMethod();
    }
}
