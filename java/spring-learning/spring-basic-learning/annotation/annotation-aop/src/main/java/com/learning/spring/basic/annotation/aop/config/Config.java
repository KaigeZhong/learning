package com.learning.spring.basic.annotation.aop.config;

import com.learning.spring.basic.annotation.aop.AopLApp;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackageClasses = {AopLApp.class})
public class Config {
}
