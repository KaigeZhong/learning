package com.learning.spring.basic.annotation.parentcontext;

import com.learning.spring.basic.annotation.parentcontext.bean.ChildBean;
import com.learning.spring.basic.annotation.parentcontext.bean.ParentBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

public class SpringAnnotationParentContextTest {
    @Test
    public void testParentAndChildContext() {
        ApplicationContext parentContext = new AnnotationConfigApplicationContext(ParentConfig.class);
        AnnotationConfigApplicationContext childContext = new AnnotationConfigApplicationContext(ChildConfig.class);
        childContext.setParent(parentContext);
        //get bean
        ChildBean childFromChild = childContext.getBean(ChildBean.class);
        ParentBean parentFromChild = childContext.getBean(ParentBean.class);
        ChildBean childBeanFromParent = null;
        System.out.println(childFromChild);
        System.out.println(parentFromChild);
        ParentBean parentBeanFromParent = parentContext.getBean(ParentBean.class);
        System.out.println(parentBeanFromParent);
        try {
            childBeanFromParent = parentContext.getBean(ChildBean.class);
        } catch (Exception e) {
            System.out.println(childBeanFromParent);
        }

        //父context的environment会被merge进子context的environment，意味着父properties属性会同时存在于子context和父context
        //get properties
        ConfigurableEnvironment childContextEnvironment = childContext.getEnvironment();
        Environment parentContextEnvironment = parentContext.getEnvironment();
        String testKeyFromChild = childContextEnvironment.getProperty("testKey");
        String childCustomKeyFromChild = childContextEnvironment.getProperty("childCustomKey");
        String parentCustomKeyFromChild = childContextEnvironment.getProperty("parentCustomKey");
        System.out.println(testKeyFromChild);
        System.out.println(parentCustomKeyFromChild);
        System.out.println(childCustomKeyFromChild);
        String testKeyFromParent = parentContextEnvironment.getProperty("testKey");
        String childCustomKeyFromParent = parentContextEnvironment.getProperty("childCustomKey");
        String parentCustomKeyFromParent = parentContextEnvironment.getProperty("parentCustomKey");
        System.out.println(testKeyFromParent);
        System.out.println(childCustomKeyFromParent);
        System.out.println(parentCustomKeyFromParent);


    }
}
