package learning.spring.boot.multiple.module.application.properties.slave;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertiesBeanSlave {

    private String testKey;
//    private String customKey;

    /**
     * 值为testValue_master
     */
    @Value("${testKey}")
    public void setTestKey(String testKey) {
        this.testKey = testKey;
        System.out.println(testKey);
    }

    /**
     * 会报错Could not resolve placeholder 'customKey' in value "${customKey}"
     * 由此可见slave的application.properties根本不会被加载
     */
//    @Value("${customKey}")
//    public void setCustomKey(String customKey) {
//        this.customKey = customKey;
//        System.out.println(customKey);
//    }
}
