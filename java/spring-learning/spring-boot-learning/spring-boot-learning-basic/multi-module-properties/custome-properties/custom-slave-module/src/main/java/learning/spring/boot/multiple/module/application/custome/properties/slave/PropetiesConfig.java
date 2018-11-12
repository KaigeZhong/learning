package learning.spring.boot.multiple.module.application.custome.properties.slave;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:slave.properties")
public class PropetiesConfig {
}
