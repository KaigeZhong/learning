package com.learning.spring.basic.annotation.bean.componentscan.bean;

import org.springframework.stereotype.Component;

@Component
public class BeanByScan {

  public BeanByScan() {
    System.out.println("instance BeanByScan");
  }
}
