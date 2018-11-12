package learning.spring.boot.multiple.module.application.custome.properties.slave;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 得出一下几个结论：
 * 1. 多个模块跟在同一个模块是一样的，所有的properties文件都会加载。
 * 2. 当文件名相同时，只有一个文件能被加载，这个文件为master的文件
 */
@Component
public class CustomPropertiesBeanSlave {

    private String testKey;
    private String customKey;

    /**
     * 值为testValue_master
     */
    @Value("${testKey}")
    public void setTestKey(String testKey) {
        this.testKey = testKey;
        System.out.println(testKey);
    }

    /**
     * 值为customValue_slave
     */
    @Value("${customKey}")
    public void setCustomKey(String customKey) {
        this.customKey = customKey;
        System.out.println(customKey);
    }
}
