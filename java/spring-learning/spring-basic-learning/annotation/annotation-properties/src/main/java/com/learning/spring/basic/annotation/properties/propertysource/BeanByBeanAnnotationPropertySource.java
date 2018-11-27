package com.learning.spring.basic.annotation.properties.propertysource;

import org.springframework.beans.factory.annotation.Value;

/**
 * populateBean -> postProcessPropertyValues ->inject -> beanFactory.resolveDependency ->
 * doResolveDependency -> resolveEmbeddedValue -> resolveStringValue
 * ....最终会调用PropertyPlaceholderHelper.replacePlaceholders来替换${name}, ${id}
 */
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

  public void setName(String name) {
    this.name = name;
  }

  public void setId(String id) {
    this.id = id;
  }
}
