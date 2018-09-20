package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * docker run --rm -p 8080:8080 --name mybatis --network docker_spring_third -d spring-third/mybatis:1.0-SNAPSHOT
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
