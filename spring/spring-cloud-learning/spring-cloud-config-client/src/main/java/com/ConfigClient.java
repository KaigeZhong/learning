package com;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConfigClient {

    private String foo;
    private String testKey;
    private String bootKey;
    private String customKey;

    public static void main(String[] args) {
        SpringApplication.run(ConfigClient.class, args);
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
}
