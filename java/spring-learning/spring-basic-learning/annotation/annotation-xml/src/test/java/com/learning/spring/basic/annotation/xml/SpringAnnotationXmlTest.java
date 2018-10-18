package com.learning.spring.basic.annotation.xml;

import com.learning.spring.basic.annotation.xml.importresource.ImportResourceConfig;
import com.learning.spring.basic.annotation.xml.importresource.XmlBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringAnnotationXmlTest {
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
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ImportResourceConfig.class);
        XmlBean xmlBean =
                (XmlBean) ctx.getBean("xmlBean");
        System.out.println(xmlBean);
    }
}
