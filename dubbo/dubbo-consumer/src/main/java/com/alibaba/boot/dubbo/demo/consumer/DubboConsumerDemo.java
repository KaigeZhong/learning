package com.alibaba.boot.dubbo.demo.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.alibaba.boot.dubbo.demo.consumer.controller")
public class DubboConsumerDemo {

  public static void main(String[] args) {

    SpringApplication.run(DubboConsumerDemo.class,args);

  }

}