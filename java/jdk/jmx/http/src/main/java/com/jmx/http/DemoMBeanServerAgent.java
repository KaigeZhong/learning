package com.jmx.http;

import com.sun.jdmk.comm.HtmlAdaptorServer;

import javax.management.*;

public class DemoMBeanServerAgent {
  private MBeanServer mbs;

  public DemoMBeanServerAgent() throws Exception {
    this.mbs = MBeanServerFactory.createMBeanServer("DemoBeanServer");

    Demo hw = new Demo();
    /**
     * 名字是有一定规则的，格式为：“域名:name=MBean名称”，域名和MBean名称都可以任意取
     */
    ObjectName demo = new ObjectName("application:name=demo");
    mbs.registerMBean(hw, demo);
    startHtmlAdaptorServer();
  }

  public void startHtmlAdaptorServer() {
    HtmlAdaptorServer htmlAdaptorServer = new HtmlAdaptorServer();
    ObjectName adapterName = null;
    try {
      // 多个属性使用,分隔
      adapterName = new ObjectName("Adapter:name=htmlAdaptorServer");
      htmlAdaptorServer.setPort(9092);
      mbs.registerMBean(htmlAdaptorServer, adapterName);
      htmlAdaptorServer.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String args[]) throws Exception {
    System.out.println(" hello agent is running");
    DemoMBeanServerAgent agent = new DemoMBeanServerAgent();
  }
}
