package com.bean.xml.aop;

public class AopTargetObject implements AopTargetObjectI {

  public AopTargetObject() {
    System.out.println("instance AopTargetObject");
  }
  public void targetMethod() {
    System.out.println("AopTargetObject targetMethod");
  }
}
