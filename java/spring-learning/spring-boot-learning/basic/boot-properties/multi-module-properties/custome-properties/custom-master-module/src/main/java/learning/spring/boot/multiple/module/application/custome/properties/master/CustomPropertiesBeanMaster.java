package learning.spring.boot.multiple.module.application.custome.properties.master;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomPropertiesBeanMaster {

    private String testKey;

    @Value("${testKey}")
    public void setTestKey(String testKey) {
        this.testKey = testKey;
        System.out.println(testKey);
    }
}
