package com.propertyresource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:propertyResource.properties")
public class propertiesConfig {

  private String testKey;

  @Bean
  public PropertiesBean propertiesBean() {
    System.out.println(testKey);
    return new PropertiesBean();
  }

  @Value("${testKey}")
  public void setTestKey(String testKey) {
    this.testKey = testKey;
  }
}
