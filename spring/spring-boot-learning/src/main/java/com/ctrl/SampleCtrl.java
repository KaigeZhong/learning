package com.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
