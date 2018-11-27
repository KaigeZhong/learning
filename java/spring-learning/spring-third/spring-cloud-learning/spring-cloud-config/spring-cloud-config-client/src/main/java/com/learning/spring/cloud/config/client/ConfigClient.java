package com.learning.spring.cloud.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1. 在List<ApplicationListener<?>> listeners中去创建了父容器(BootstrapApplicationListener中创建)
 * 2. 从远程获取配置是发生在子容器的prepareContext --> applyInitializers, 在List<ApplicationContextInitializer<?>> initializers中去fetch远程config server的配置(由PropertySourceBootstrapConfiguration --> ConfigServicePropertySourceLocator最终调用getRemoteEnvironment去获取)
 */
@SpringBootApplication
public class ConfigClient {
    public static void main(String[] args) {
        SpringApplication.run(ConfigClient.class, args);
    }
}
