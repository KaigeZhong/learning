package com.learning.spring.cloud.sleuth.zipkin.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SampleCtrl {
  @Autowired
  private RestTemplate restTemplate;



  @RequestMapping("/miya")
  public String info(){
    return restTemplate.getForObject("http://localhost:8988/info",String.class);
  }

  @RequestMapping("/hi")
  public String home(){
    return "hi i'm service-B!";
  }

}
