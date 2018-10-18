package com.learning.sprong.basic.xml.bean;

import com.learning.spring.basic.xml.bean.basic.BasicBeanByXml;
import com.learning.spring.basic.xml.bean.factorybean.BeanByFactoryBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpringXmlBean {
    @Test
    public void testBasicByxml() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-basic.xml");
        BasicBeanByXml basicBean = (BasicBeanByXml) context.getBean("basicBean");
        System.out.println(basicBean);
    }

    @Test
    public void testFactoryBeanByxml() {
        // 会doGetBean，检查sharedInstance是否为空，由于factorybean已经创建，sharedInstance
        // 不会为空，则执行getObjectForBeanInstance方法，调用factorybean的getObject方法生成对象
        //If implementing SmartFactoryBean and isEagerInit is true, will create bean when init.
        ApplicationContext context = new ClassPathXmlApplicationContext("application-factorybean.xml");
        //If implementing FactoryBean, will create bean when using it.
        BeanByFactoryBean beanByFactoryBean = (BeanByFactoryBean) context.getBean("beanByFactoryBean");
        System.out.println(beanByFactoryBean);
    }
}
