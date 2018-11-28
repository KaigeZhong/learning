package com.learning.spring.basic.annotation.aop.bean;

import org.springframework.stereotype.Component;

@Component
public class BusinessService {

  public void say(){
    System.out.println("Business Code");
  }
}
