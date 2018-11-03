package com.jmx.http;

public class Demo implements DemoMBean {
    private String greeting;

    public Demo(String greeting) {
        this.greeting = greeting;
    }
    public Demo() {
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
