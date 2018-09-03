package com.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/*
DispatcherServlet会在onRefresh()创建tomcat的时候在后台线程中实例化并注册到tomcat
而DispatcherServlet的handlerMappings（例如RequestMappingHandlerMapping
）则会在后面的finishBeanFactoryInitialization(beanFactory)主线程中被实例化。
 */
@RestController
public class SampleCtrl {
  @RequestMapping("/")
  String home() {
    return "Hello World!";
  }
  @RequestMapping("/test")
  String test() {
    return "test";
  }
}
