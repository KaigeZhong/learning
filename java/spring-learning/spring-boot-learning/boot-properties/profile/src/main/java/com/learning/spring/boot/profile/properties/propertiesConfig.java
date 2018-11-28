package com.learning.spring.boot.profile.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 优先级是profile, default, propertySource
 */
@Configuration
@PropertySource("classpath:propertyResource.properties")
public class propertiesConfig {

  private String testKey;
  private String customKey;

  @Bean
  public PropertiesBean propertiesBean() {
    System.out.println(testKey);
    return new PropertiesBean();
  }

  @Value("${testKey}")
  public void setTestKey(String testKey) {
    this.testKey = testKey;
  }

  @Value("${customKey}")
  public void setCustomKey(String customKey) {
    this.customKey = customKey;
  }
}
