package com.learning.spring.basic.xml.annotation.bean.componentscan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComponentScanBean {

  public ComponentScanBean() {
    System.out.println("instance ComponentScanBean");
  }

}
