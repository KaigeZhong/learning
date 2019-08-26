package com.aop;

import org.springframework.stereotype.Component;

@Component
public class TargetBean {

  @AopAnnotation
  public void say(){
    System.out.println("Business Code");
  }
}
