package com.learning.spring.third.swaggerconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class SwaggerConfigApp {
    public static void main(String[] args) {
        SpringApplication.run(SwaggerConfigApp.class, args);
    }
}
