package com;

import com.aop.TargetBeanI;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class Application {
    public static void main(String[] args) {
        //将生成的class文件落磁盘, jdk8   - sun.misc.ProxyGenerator.saveGeneratedFiles jdk10  - jdk.proxy.ProxyGenerator.saveGeneratedFiles
//        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationConfig.class, args);
        TargetBeanI bean = context.getBean(TargetBeanI.class);
        bean.say();
        //调用exit 可触发shutDownHook
        System.exit(0);
    }
}
