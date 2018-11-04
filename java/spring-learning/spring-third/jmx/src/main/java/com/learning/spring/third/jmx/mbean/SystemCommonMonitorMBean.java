package com.learning.spring.third.jmx.mbean;

import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component
@ManagedResource(objectName = "monitor:name=SystemCommonMonitor")
public class SystemCommonMonitorMBean {

  private String systemName;

  @ManagedAttribute
  public String getSystemName() {
    return this.systemName;
  }

  @ManagedAttribute(description = "system_name", defaultValue = "demo")
  public void setSystemName(String name) {
    this.systemName = name;
  }

  @ManagedOperation(description = "systemInfo")
  public String systemInfo() {
    return "cpuCoreSize: 4, memorySize: 8G";
  }
}