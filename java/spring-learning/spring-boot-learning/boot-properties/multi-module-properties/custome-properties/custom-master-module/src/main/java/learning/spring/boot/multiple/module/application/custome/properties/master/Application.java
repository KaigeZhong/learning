package learning.spring.boot.multiple.module.application.custome.properties.master;

import learning.spring.boot.multiple.module.application.custome.properties.slave.CustomPropertiesBeanSlave;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {CustomPropertiesBeanMaster.class, CustomPropertiesBeanSlave.class})
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}