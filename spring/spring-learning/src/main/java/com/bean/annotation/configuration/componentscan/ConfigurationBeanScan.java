package com.bean.annotation.configuration.componentscan;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ConfigurationBeanScan.class)
public class ConfigurationBeanScan {

}
