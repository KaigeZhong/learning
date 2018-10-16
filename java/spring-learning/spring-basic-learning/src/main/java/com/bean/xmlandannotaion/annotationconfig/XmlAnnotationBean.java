package com.bean.xmlandannotaion.annotationconfig;

import org.springframework.beans.factory.annotation.Autowired;

public class XmlAnnotationBean {

  AutoWiredBean autoWiredBean;

  public AutoWiredBean getAutoWiredBean() {
    return autoWiredBean;
  }

  @Autowired
  public void setAutoWiredBean(AutoWiredBean autoWiredBean) {
    this.autoWiredBean = autoWiredBean;
  }
}
