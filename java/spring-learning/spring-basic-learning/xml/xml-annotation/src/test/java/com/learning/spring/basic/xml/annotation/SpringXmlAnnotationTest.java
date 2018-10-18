package com.learning.spring.basic.xml.annotation;

import com.learning.spring.basic.xml.annotation.ioc.annotationconfig.XmlAnnotationBean;
import com.learning.spring.basic.xml.annotation.bean.componentscan.ComponentScanBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringXmlAnnotationTest {
    @Test
    public void testXmlComponentScan() {
    /*
    component-scan会在obtainFreshBeanFactory() -> ContextNameSpaceHandler ->
    ComponentScanBeanDefinitionParser进行bean扫描注册
     */
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application-component-scan.xml");

        ComponentScanBean componentScanBean = (ComponentScanBean) context.getBean("componentScanBean");
        System.out.println(componentScanBean);
    }

    @Test
    public void testXmlAnnotationConfig() {
    /*
    annotation-config会注入这四个BeanPostProcessor: CommonAnnotationBeanPostProcessor,
    AutowiredAnnotationBeanPostProcessor, PersistenceAnnotationBeanPostProcessor,
    RequiredAnnotationBeanPostProcessor
    在createBeanInstance之后，populateBean之前，会调用applyMergedBeanDefinitionPostProcessors
    去执行这这四个bean post processor的postProcessMergedBeanDefinition方法，将类上的注解信息注册到beanDefinition
    CommonAnnotationBeanPostProcessor: 注册@PostConstruct, @PreDestroy
    AutowiredAnnotationBeanPostProcessor: 注册@Autowired
    RequiredAnnotationBeanPostProcessor: @Required
     */
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application-annotation-config.xml");

        XmlAnnotationBean xmlAnnotationBean = (XmlAnnotationBean) context.getBean("xmlAnnotationBean");
        System.out.println(xmlAnnotationBean.getAutoWiredBean());
    }

}
