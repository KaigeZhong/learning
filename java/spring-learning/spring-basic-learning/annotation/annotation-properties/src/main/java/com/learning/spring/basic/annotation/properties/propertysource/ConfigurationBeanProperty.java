package com.learning.spring.basic.annotation.properties.propertysource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 对于multi配置，也可以采取这种形式
 * @PropertySources({
 *
 * @PropertySource("classpath:config.properties"),
 *
 * @PropertySource("classpath:db.properties")
 *
 * })
 */
@Configuration
@PropertySource(value = {"classpath:config1.properties", "classpath:config2.properties"})
public class ConfigurationBeanProperty {

  @Bean
  public BeanByBeanAnnotationPropertySource beanByBeanAnnotationPropertySource() {
    return new BeanByBeanAnnotationPropertySource();
  }
}
