package com.bean.xmlandannotaion.componentscan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComponentScanBean {

  public ComponentScanBean() {
    System.out.println("instance ComponentScanBean");
  }

  @Autowired
  ComponentScanAutowiredBean componentScanAutowiredBean;

  public ComponentScanAutowiredBean getComponentScanAutowiredBean() {
    return componentScanAutowiredBean;
  }
}
