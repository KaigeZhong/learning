package com;

import org.springframework.boot.SpringApplication;

public class Application {
    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
        //调用exit 可触发shutDownHook
        System.exit(0);
    }
}
