package com.learning.spring.basic.annotation.bean.componentscan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ConfigurationBeanScan.class)
public class ConfigurationBeanScan {

}
