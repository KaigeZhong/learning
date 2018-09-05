package com;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ConfigClient implements ApplicationContextAware {

    private String foo;
    private String testKey;
    private String bootKey;
    private String customKey;
    private ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(ConfigClient.class, args);
    }

    @RequestMapping("/")
    public String testKeyValue() {
        String testValue = value("testKey");
        return testValue;
    }
    @RequestMapping("/{key}")
    public String value(@PathVariable String key) {
        String property = context.getEnvironment().getProperty(key);
        String propertyFromParent = context.getParent().getEnvironment().getProperty(key);
        return property + ", " + propertyFromParent;
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
