package com.jmx.rmi.mbean;

import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.ObjectName;
import javax.management.remote.JMXAuthenticator;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXPrincipal;
import javax.management.remote.JMXServiceURL;
import javax.security.auth.Subject;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *如果要演示jconsole和jvisualvm的远程连接，需将应用放到docker中，应为没有任何第三方依赖，就只需要将编译的class文件复制到docker容器中即可
 * docker cp target/classes/com containerName:/opt/com
 */
public class MyRmiMBeanServerAgent {
  private MBeanServer mbs;

  public MyRmiMBeanServerAgent() throws Exception {
    this.mbs = MBeanServerFactory.createMBeanServer("DemoBeanServer");

    MyRmi myRmi = new MyRmi();
    /**
     * 名字是有一定规则的，格式为：“域名:name=MBean名称”，域名和MBean名称都可以任意取
     */
    ObjectName demoName = new ObjectName("application:name=my.rmi.demo");
    mbs.registerMBean(myRmi, demoName);
    startRmiAdaptorServer();
  }

  /**
   * 客户端可以通过jconsole或jvisualvm访问,也可以自己写client端访问
   * 如果采用jvisualvm需要在jvisualvm中安装MBean插件
   */
  public void startRmiAdaptorServer() throws IOException {
    LocateRegistry.createRegistry(8888);
    JMXServiceURL url = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:8888/jmxrmi");

    Map<String, Object> jmxEnvironment = new HashMap<>();
    //     需要认证则这么写：
//    JMXAuthenticator auth = createJMXAuthenticator();
//    jmxEnvironment.put(JMXConnectorServer.AUTHENTICATOR, auth);

    JMXConnectorServer cs = JMXConnectorServerFactory.newJMXConnectorServer(url, jmxEnvironment, mbs);
    cs.start();
    System.out.println("....................rmi start.....");
  }

  public static void main(String args[]) throws Exception {
    MyRmiMBeanServerAgent agent = new MyRmiMBeanServerAgent();
    System.out.println(" hello agent is running");
  }


  private String user = "admin";
  private String pw = "admin";
  private JMXAuthenticator createJMXAuthenticator() {
    return new JMXAuthenticator() {
      public Subject authenticate(Object credentials) {
        String[] sCredentials = (String[]) credentials;
        if (null == sCredentials || sCredentials.length != 2) {
          throw new SecurityException("Authentication failed!");
        }
        String userName = sCredentials[0];
        String pValue = sCredentials[1];
        if (user.equals(userName) && pw.equals(pValue)) {
          Set principals = new HashSet();
          principals.add(new JMXPrincipal(userName));
          return new Subject(true, principals, Collections.EMPTY_SET,
            Collections.EMPTY_SET);
        }
        throw new SecurityException("Authentication failed!");
      }
    };
  }
}
