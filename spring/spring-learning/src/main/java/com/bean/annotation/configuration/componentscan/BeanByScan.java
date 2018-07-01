package com.bean.annotation.configuration.componentscan;

import org.springframework.stereotype.Component;

@Component
public class BeanByScan {
  public BeanByScan() {
    System.out.println("instance BeanByScan");
  }
}
