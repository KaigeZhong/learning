package com.learning.spring.cloud.eureka.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * By having spring-cloud-starter-netflix-eureka-client on the classpath, your application automatically registers with the Eureka Server.
 */
@SpringBootApplication
public class ApplicationA {

  public static void main(String[] args) {
    SpringApplication.run(ApplicationA.class, args);
  }

}