package com;

import com.aop.TargetBean;
import org.springframework.boot.SpringApplication;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.ConfigurableApplicationContext;

public class Application {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, Application.class.getClassLoader().getResource("").getFile());//将cglib生成的class落盘
        ConfigurableApplicationContext context = SpringApplication.run(ApplicationConfig.class, args);
        TargetBean bean = context.getBean(TargetBean.class);
        bean.say();
        //调用exit 可触发shutDownHook
        System.exit(0);
    }
}
