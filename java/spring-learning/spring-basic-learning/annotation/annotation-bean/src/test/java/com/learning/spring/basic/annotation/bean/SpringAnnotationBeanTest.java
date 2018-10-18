package com.learning.spring.basic.annotation.bean;

import com.learning.spring.basic.annotation.bean.Import.BeanByImported;
import com.learning.spring.basic.annotation.bean.Import.ConfigurationImport;
import com.learning.spring.basic.annotation.bean.comfigurationandbean.BeanByBeanAnnotation;
import com.learning.spring.basic.annotation.bean.comfigurationandbean.ConfigurationBean;
import com.learning.spring.basic.annotation.bean.componentscan.BeanByScan;
import com.learning.spring.basic.annotation.bean.componentscan.ConfigurationBeanScan;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotationBeanTest {
    @Test
    public void testConfigurationAndBean() {
    /*
    @Configuration会注入BeanFactoryPostProcessor: ConfigurationClassPostProcessor
    ConfigurationClassPostProcessor会去beanDefinitionMap查找先前注册的config class(@Configuration)来处理:
    处理@PropertySource标签，用来解析属性文件并设置到Environment中。
    处理@ComponentScan标签，扫描package下的所有Class并进行迭代解析。
    处理@Import标签。
    处理@ImportResource标签。
    处理@Bean标签。
     */
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationBean.class);
        BeanByBeanAnnotation beanByBeanAonotation =
                (BeanByBeanAnnotation) ctx.getBean("beanByBeanAonotation");
        System.out.println(beanByBeanAonotation);
    }

    @Test
    public void testConfigurationScan() {
        /*
         */
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationBeanScan.class);

        BeanByScan beanByScan = (BeanByScan) ctx.getBean("beanByScan");
        System.out.println(beanByScan);
    }
    @Test
    public void testConfigurationImport() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationImport.class);
        BeanByImported beanByImported = (BeanByImported) ctx.getBean("beanByImported");

        System.out.println(beanByImported);
    }
}
