package learning.spring.boot.multiple.module.application.properties.master;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesBeanMaster {

    private String testKey;

    @Value("${testKey}")
    public void setTestKey(String testKey) {
        this.testKey = testKey;
        System.out.println(testKey);
    }
}
