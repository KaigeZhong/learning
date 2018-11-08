package com.learning.spring.cloud.sleuth.zipkin.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SampleCtrl {
  @Autowired
  private RestTemplate restTemplate;



  @RequestMapping("/zipkin")
  public String zipkin(){
    return "zipkin result";
  }

}
