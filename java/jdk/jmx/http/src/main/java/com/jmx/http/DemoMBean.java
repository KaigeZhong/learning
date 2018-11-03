package com.jmx.http;

/**
 * 每一个MBean必须定义一个接口，而且这个接口的名字必须是其被管理的资源的对象类的名称后面加上”MBean”
 * 例如DemoMBean和Demo， MyApplicationMBean和MyApplication
 */
public interface DemoMBean {
    String getGreeting();
    void setGreeting(String greeting);
    void printGreeting();
}
