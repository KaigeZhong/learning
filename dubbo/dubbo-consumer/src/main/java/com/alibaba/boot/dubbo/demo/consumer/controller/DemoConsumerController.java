package com.alibaba.boot.dubbo.demo.consumer.controller;

import com.alibaba.boot.dubbo.demo.provider.DemoService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoConsumerController {

  @Reference(version = "${demo.service.version}",
    application = "${dubbo.application.id}",
    registry = "zookeeper-registry")
  private DemoService demoService;

  @RequestMapping("/sayHello")
  public String sayHello(@RequestParam String name) {
    return demoService.sayHello(name);
  }

}
