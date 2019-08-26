package learning.spring.boot.multiple.module.application.properties.master;

import learning.spring.boot.multiple.module.application.properties.slave.PropertiesBeanSlave;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {PropertiesBeanSlave.class, PropertiesBeanMaster.class})
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}