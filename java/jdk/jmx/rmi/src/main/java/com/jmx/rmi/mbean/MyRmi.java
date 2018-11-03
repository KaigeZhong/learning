package com.jmx.rmi.mbean;

public class MyRmi implements MyRmiMBean {
  private String greeting;

  public MyRmi(String greeting) {
    this.greeting = greeting;
  }
  public MyRmi() {
    this("hello world!");
  }
  public String getGreeting() {
    return greeting;
  }
  public void setGreeting(String greeting) {
    this.greeting = greeting;
  }
  public void printGreeting() {
    System.out.println(greeting);
  }
}
