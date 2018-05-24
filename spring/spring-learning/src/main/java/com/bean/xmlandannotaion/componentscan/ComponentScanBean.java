package com.bean.xmlandannotaion.componentscan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComponentScanBean {

  @Autowired
  ComponentScanAutowiredBean componentScanAutowiredBean;

  public ComponentScanAutowiredBean getComponentScanAutowiredBean() {
    return componentScanAutowiredBean;
  }
}
