package com.aop;

import org.springframework.stereotype.Component;

@Component
public class TargetBean implements TargetBeanI {

  @LogAopAnnotation
  @TimeAopAnnotation
  @Override
  public void say(){
    System.out.println("Business Code");
  }
}
