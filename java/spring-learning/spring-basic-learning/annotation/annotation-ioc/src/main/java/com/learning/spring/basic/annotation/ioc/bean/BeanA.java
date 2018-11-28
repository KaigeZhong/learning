package com.learning.spring.basic.annotation.ioc.bean;

import org.springframework.beans.factory.annotation.Autowired;

public class BeanA {
  @Autowired
  private BeanB beanB;

  public BeanB getBeanB() {
    return beanB;
  }
}
