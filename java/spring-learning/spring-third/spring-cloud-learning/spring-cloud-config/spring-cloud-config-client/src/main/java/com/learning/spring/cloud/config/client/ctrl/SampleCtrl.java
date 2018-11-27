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
    /**
     * config 的配置更新，需要手动刷新每一个服务
     * http://localhost:8088/actuator/refresh
     */
    private String foo;
    private String genericKey;
    private String bootKey;
    private String applicationKey;
    private ApplicationContext context;

    @RequestMapping("/config/{key}")
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

    @Value("${genericKey}")
    public void setGenericKey(String genericKey) {
        this.genericKey = genericKey;
    }

    @Value("${bootKey}")
    public void setBootKey(String bootKey) {
        this.bootKey = bootKey;
    }

    @Value("${applicationKey}")
    public void setApplicationKey(String applicationKey) {
        this.applicationKey = applicationKey;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }


}
