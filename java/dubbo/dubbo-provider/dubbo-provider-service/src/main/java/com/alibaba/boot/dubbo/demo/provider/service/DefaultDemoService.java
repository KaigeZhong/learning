package com.alibaba.boot.dubbo.demo.provider.service;

import com.alibaba.boot.dubbo.demo.provider.DemoService;
import com.alibaba.dubbo.config.annotation.Service;

@Service(
  application = "${dubbo.application.id}",
  protocol = "${dubbo.protocol.id}",
  registry = "${dubbo.registry.id}"
)
public class DefaultDemoService implements DemoService {

  public String sayHello(String name) {
    return "Hello, " + name;
  }

}