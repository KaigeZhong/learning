package com.jmx.rmi.mbean;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;

public class MyRmiMBeanServerAgent {
  private MBeanServer mbs;

  public MyRmiMBeanServerAgent() throws Exception {
    this.mbs = MBeanServerFactory.createMBeanServer("DemoBeanServer");

    MyRmi hw = new MyRmi();
    /**
     * 名字是有一定规则的，格式为：“域名:name=MBean名称”，域名和MBean名称都可以任意取
     */
    ObjectName demo = new ObjectName("application:name=my.rmi.demo");
    mbs.registerMBean(hw, demo);
    startHtmlAdaptorServer();
  }

  public void startHtmlAdaptorServer() {

  }

  public static void main(String args[]) throws Exception {
    System.out.println(" hello agent is running");
    MyRmiMBeanServerAgent agent = new MyRmiMBeanServerAgent();
  }
}
