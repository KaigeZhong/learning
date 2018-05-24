package com;

import com.bean.annotation.configuration.ConfigurationBean;
import com.bean.xml.aop.AopTargetObjectI;
import com.bean.xml.factorybean.BeanByFactoryBean;
import com.bean.xmlandannotaion.annotationconfig.XmlAnnotationBean;
import com.bean.xmlandannotaion.componentscan.ComponentScanBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

  @Test
  public void testBasicByxml() {
    ApplicationContext context = new ClassPathXmlApplicationContext("application-basic.xml");
  }

  @Test
  public void testFactoryBeanByxml() {
    // 会doGetBean，检查sharedInstance是否为空，由于factorybean已经创建，sharedInstance
    // 不会为空，则执行getObjectForBeanInstance方法，调用factorybean的getObject方法生成对象
    //If implementing SmartFactoryBean and isEagerInit is true, will create bean when init.
    ApplicationContext context = new ClassPathXmlApplicationContext("application-factorybean.xml");
    //If implementing FactoryBean, will create bean when using it.
    BeanByFactoryBean beanByFactoryBean = (BeanByFactoryBean) context.getBean("beanByFactoryBean");
  }

  @Test
  public void testAopByxml() {
    // <aop:config>会生成AspectJAwareAdvisorAutoProxyCreator BeanPostProcessor
    // aop 代理处理发生在AspectJAwareAdvisorAutoProxyCreator BeanPostProcessor中，生成逻辑在AopProxy中
    ApplicationContext context = new ClassPathXmlApplicationContext("application-aop.xml");
    AopTargetObjectI proxy = (AopTargetObjectI) context.getBean("targetObject");
    proxy.targetMethod();
  }

  @Test
  public void testXmlAnnotationConfig() {
    /*
    annotation-config会注入这四个BeanPostProcessor: CommonAnnotationBeanPostProcessor,AutowiredAnnotationBeanPostProcessor, PersistenceAnnotationBeanPostProcessor, RequiredAnnotationBeanPostProcessor
    在createBeanInstance之后，populateBean之前，会调用applyMergedBeanDefinitionPostProcessors
    去执行这这四个bean post processor的postProcessMergedBeanDefinition方法，将类上的注解信息注册到beanDefinition
    CommonAnnotationBeanPostProcessor: 注册@PostConstruct, @PreDestroy
    AutowiredAnnotationBeanPostProcessor: 注册@Autowired
    RequiredAnnotationBeanPostProcessor: @Required
     */
    ApplicationContext context = new ClassPathXmlApplicationContext("application-annotation-config.xml");

    XmlAnnotationBean xmlAnnotationBean = (XmlAnnotationBean) context.getBean("xmlAnnotationBean");
    System.out.println(xmlAnnotationBean.getAutoWiredBean());
  }

  @Test
  public void testXmlComponentScan() {
    /*
    component-scan会注入BeanFactoryPostProcessor: ConfigurationClassPostProcessor
    会在AbstractApplicationContext -> refresh -> postProcessBeanDefinitionRegistry处被调用.
     */
    ApplicationContext context = new ClassPathXmlApplicationContext("application-component-scan.xml");

    ComponentScanBean componentScanBean = (ComponentScanBean) context.getBean("componentScanBean");
    System.out.println(componentScanBean.getComponentScanAutowiredBean());
  }

  @Test
  public void testConfiguration() {
    /*
    @Configuration会注入BeanFactoryPostProcessor: ConfigurationClassPostProcessor
     */
    ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationBean.class);
  }
}
