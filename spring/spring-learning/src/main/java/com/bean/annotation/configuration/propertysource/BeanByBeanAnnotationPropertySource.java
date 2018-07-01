package com.bean.annotation.configuration.propertysource;

import org.springframework.beans.factory.annotation.Value;

public class BeanByBeanAnnotationPropertySource {
  @Value("${name}")
  private String name;

  public String getName() {
    return name;
  }
}
