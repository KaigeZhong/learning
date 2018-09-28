package com.bean.xml.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBean implements FactoryBean<BeanByFactoryBean> {

  public MyFactoryBean() {
    System.out.println("instance MyFactoryBean");
  }
  public BeanByFactoryBean getObject() throws Exception {
    return new BeanByFactoryBean();
  }

  public Class<?> getObjectType() {
    return BeanByFactoryBean.class;
  }
}
