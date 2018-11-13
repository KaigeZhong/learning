package com.learning.spring.basic.annotation.properties;

import com.learning.spring.basic.annotation.properties.propertysource.BeanByBeanAnnotationPropertySource;
import com.learning.spring.basic.annotation.properties.propertysource.ConfigurationBeanProperty;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotationPropertiesTest {
    @Test
    public void testConfigurationPropertySource() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationBeanProperty.class);

        BeanByBeanAnnotationPropertySource beanByBeanAnnotation =
                (BeanByBeanAnnotationPropertySource) ctx.getBean("beanByBeanAnnotationPropertySource");
        System.out.println(beanByBeanAnnotation.getName());
        System.out.println(beanByBeanAnnotation.getId());
    }
}
