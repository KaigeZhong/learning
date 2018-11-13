package com.learning.spring.basic.annotation.properties.propertysource;

import org.springframework.beans.factory.annotation.Value;

public class BeanByBeanAnnotationPropertySource {
  @Value("${name}")
  private String name;
  @Value("${id}")
  private String id;

  public String getName() {
    return name;
  }

  public String getId() {
    return id;
  }
}
