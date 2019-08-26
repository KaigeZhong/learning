package com.learning.spring.boot.basic.conditional.conditionalonbean.commander;

import com.learning.spring.boot.basic.conditional.conditionalonbean.bean.ConditionalOnBeanBeanA;
import com.learning.spring.boot.basic.conditional.conditionalonbean.bean.ConditionalOnBeanBeanB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SimpleCommandLineRunner implements CommandLineRunner {
    @Autowired
    ConditionalOnBeanBeanA conditionalOnBeanBeanA;
    @Autowired
    ConditionalOnBeanBeanB conditionalOnBeanBeanB;
    public void run(String... args) {
        System.out.println("spring-boot-learning-basic run");
    }
}
