package com.learning.spring.cloud.bus.ctrl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleCtrl {
    /**
     * config 的配置更新，需要手动刷新每一个服务
     * http://localhost:8088/actuator/refresh
     */
    private String foo;

    @RequestMapping("/bus")
    public String value() {
        return foo;
    }

    @Value("${foo}")
    public void setFoo(String foo) {
        this.foo = foo;
    }

}
