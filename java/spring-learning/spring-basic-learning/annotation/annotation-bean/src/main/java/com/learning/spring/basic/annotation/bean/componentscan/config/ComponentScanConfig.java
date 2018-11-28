package com.learning.spring.basic.annotation.bean.componentscan.config;

import com.learning.spring.basic.annotation.bean.componentscan.ComponentScanLApp;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = ComponentScanLApp.class)
public class ComponentScanConfig {

}
