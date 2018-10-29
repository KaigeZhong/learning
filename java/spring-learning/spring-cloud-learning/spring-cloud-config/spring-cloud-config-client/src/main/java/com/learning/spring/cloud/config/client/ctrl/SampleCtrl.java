package com.learning.spring.cloud.config.client.ctrl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleCtrl implements ApplicationContextAware {
    private String foo;
    private String testKey;
    private String bootKey;
    private String customKey;
    private ApplicationContext context;

    @RequestMapping("/")
    public String testKeyValue() {
        String testValue = value("testKey");
        return testValue;
    }
    @RequestMapping("/{key}")
    public String value(@PathVariable String key) {
        /**
         * bootstrap.yml存在父context的Environment里
         * 远程配置，application-dev.yml, application.yml和bootstrap.yml存在子context的environment
         */
        String property = context.getEnvironment().getProperty(key);
        String propertyFromParent = context.getParent().getEnvironment().getProperty(key);
        return "valueFromChildContext: " + property + ", valueFromParentContext:  " + propertyFromParent;
    }

    @Value("${foo}")
    public void setFoo(String foo) {
        this.foo = foo;
    }

    @Value("${testKey}")
    public void setTestKey(String testKey) {
        this.testKey = testKey;
    }

    @Value("${bootKey}")
    public void setBootKey(String bootKey) {
        this.bootKey = bootKey;
    }

    @Value("${customKey}")
    public void setCustomKey(String customKey) {
        this.customKey = customKey;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }


}
