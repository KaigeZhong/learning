package com;

import com.bean.annotation.configuration.Import.BeanByImported;
import com.bean.annotation.configuration.Import.ConfigurationByImported;
import com.bean.annotation.configuration.Import.ConfigurationImport;
import com.bean.annotation.configuration.basic.BeanByBeanAnnotation;
import com.bean.annotation.configuration.basic.ConfigurationBean;
import com.bean.annotation.configuration.componentscan.BeanByScan;
import com.bean.annotation.configuration.componentscan.ConfigurationBeanScan;
import com.bean.annotation.configuration.propertysource.BeanByBeanAnnotationPropertySource;
import com.bean.annotation.configuration.propertysource.ConfigurationBeanProperty;
import com.bean.parentcontext.ChildConfig;
import com.bean.parentcontext.ParentConfig;
import com.bean.parentcontext.bean.ChildBean;
import com.bean.parentcontext.bean.ParentBean;
import com.bean.xml.aop.AopTargetObjectI;
import com.bean.xml.factorybean.BeanByFactoryBean;
import com.bean.xmlandannotaion.annotationconfig.XmlAnnotationBean;
import com.bean.xmlandannotaion.componentscan.ComponentScanBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

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

    @Test
    public void testXmlComponentScan() {
    /*
    component-scan会在obtainFreshBeanFactory() -> ContextNameSpaceHandler ->
    ComponentScanBeanDefinitionParser进行bean扫描注册
     */
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application-component-scan.xml");

        ComponentScanBean componentScanBean = (ComponentScanBean) context.getBean("componentScanBean");
        System.out.println(componentScanBean.getComponentScanAutowiredBean());
    }

    @Test
    public void testConfiguration() {
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
    public void testConfigurationPropertySource() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationBeanProperty.class);

        BeanByBeanAnnotationPropertySource beanByBeanAnnotation =
                (BeanByBeanAnnotationPropertySource) ctx.getBean("beanByBeanAnnotationPropertySource");
        System.out.println(beanByBeanAnnotation.getName());
    }

    @Test
    public void testConfigurationImport() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigurationImport.class);
        BeanByImported beanByImported = (BeanByImported) ctx.getBean("beanByImported");

        System.out.println(beanByImported);
    }

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
