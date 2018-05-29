package com.bean.annotation.configuration.basic;

import org.springframework.stereotype.Component;

@Component
public class BeanByScan {
  public BeanByScan() {
    System.out.println("instance BeanByScan");
  }
}
