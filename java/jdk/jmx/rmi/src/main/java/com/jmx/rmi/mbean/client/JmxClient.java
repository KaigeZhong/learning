package com.jmx.rmi.mbean.client;

import com.jmx.rmi.mbean.MyRmiMBean;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.util.HashMap;
import java.util.Map;

public class JmxClient {

  public static void main(String[] args) throws Exception {
      Map<String, Object> jmxEnvironment = new HashMap<String, Object>();
      // 如果服務端有认证，則客戶端也要这么写：
//       jmxEnvironment.put("jmx.remote.credentials", new String[] {"admin", "admin"});

      JMXConnector connector = JMXConnectorFactory.connect(new JMXServiceURL("service:jmx:rmi://172.17.0.2:8888/jndi/rmi://172.17.0.2:8888/jmxrmi"),
        jmxEnvironment);
      MBeanServerConnection connection = connector.getMBeanServerConnection();
      MyRmiMBean myRmi = JMX.newMXBeanProxy(connection, new ObjectName("application:name=my.rmi.demo"), MyRmiMBean.class);

      //operation on Mbean
      System.out.println(myRmi.getGreeting());
      myRmi.setGreeting("changed by java client");
      myRmi.printGreeting();
      connector.close();
  }
}