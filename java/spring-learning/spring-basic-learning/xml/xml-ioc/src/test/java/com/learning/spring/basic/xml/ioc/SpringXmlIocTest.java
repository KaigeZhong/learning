package com.learning.spring.basic.xml.ioc;

import com.learning.spring.basic.xml.ioc.domain.IocBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXmlIocTest {
    @Test
    public void testIoc() {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-ioc.xml");
        IocBean iocBean = (IocBean) context.getBean("iocBean");
        System.out.println(iocBean.getDependency());
    }
}
