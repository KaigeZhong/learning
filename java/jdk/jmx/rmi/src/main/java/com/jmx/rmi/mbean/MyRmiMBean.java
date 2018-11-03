package com.jmx.rmi.mbean;

public interface MyRmiMBean {
  String getGreeting();
  void setGreeting(String greeting);
  void printGreeting();
}
