package com.learning.spring.basic.annotation.bean;

import com.learning.spring.basic.annotation.bean.Import.BeanByImported;
import com.learning.spring.basic.annotation.bean.Import.ConfigurationImport;
import com.learning.spring.basic.annotation.bean.comfigurationandbean.BeanByBeanAnnotation;
import com.learning.spring.basic.annotation.bean.comfigurationandbean.ConfigurationBean;
import com.learning.spring.basic.annotation.bean.componentconig.ComponentBean;
import com.learning.spring.basic.annotation.bean.componentconig.ComponentConfig;
import com.learning.spring.basic.annotation.bean.componentscan.BeanByScan;
import com.learning.spring.basic.annotation.bean.componentscan.ConfigurationBeanScan;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotationBeanTest {

    @Test
    public void testComponentConfig() {
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

    @Test
    public void testConfigurationAndBean() {
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
