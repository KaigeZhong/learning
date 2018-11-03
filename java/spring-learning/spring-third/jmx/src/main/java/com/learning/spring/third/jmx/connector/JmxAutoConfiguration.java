package com.learning.spring.third.jmx.connector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;

@Configuration
public class JmxAutoConfiguration {

  // 指定特定端口可以开放命名服务
  @Bean
  public RmiRegistryFactoryBean rmiRegistry() {
    RmiRegistryFactoryBean factoryBean = new RmiRegistryFactoryBean();
    factoryBean.setPort(7099);
    factoryBean.setAlwaysCreate(true);
    return factoryBean;
  }

  @DependsOn("rmiRegistry")
  @Bean
  public ConnectorServerFactoryBean jmxConnector() {
    ConnectorServerFactoryBean serverFactoryBean = new ConnectorServerFactoryBean();
    serverFactoryBean.setServiceUrl(String.format("service:jmx:rmi://%s:%s/jndi/rmi://%s:%s/%s", "localhost", 7099, "localhost", 7099, "jmxrmi"));
    return serverFactoryBean;
  }
}