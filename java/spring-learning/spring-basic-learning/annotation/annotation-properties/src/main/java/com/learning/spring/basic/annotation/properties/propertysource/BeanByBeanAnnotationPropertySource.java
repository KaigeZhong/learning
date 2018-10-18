package com.learning.spring.basic.annotation.properties.propertysource;

import org.springframework.beans.factory.annotation.Value;

public class BeanByBeanAnnotationPropertySource {
  @Value("${name}")
  private String name;

  public String getName() {
    return name;
  }
}
