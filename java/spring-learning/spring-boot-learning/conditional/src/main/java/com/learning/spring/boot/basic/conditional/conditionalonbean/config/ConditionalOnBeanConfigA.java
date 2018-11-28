package com.learning.spring.boot.basic.conditional.conditionalonbean.config;

import com.learning.spring.boot.basic.conditional.conditionalonbean.bean.ConditionalOnBeanBeanA;
import com.learning.spring.boot.basic.conditional.conditionalonbean.bean.ConditionalOnBeanBeanB;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ConditionalOnBean: beanFactory中已经有ConditionalOnBeanBeanB的beandefinition才为true.这里只有确保ConditionalOnBeanBeanB的beandefinition先被加载进beanfactory才会配置ConditionalOnBeanConfigA
 * 可通过@AutoConfigureBefore、AutoConfigureAfter AutoConfigureOrder控制beandefinition处理的先后顺序，但这三个注解只对自动配置类的先后顺序生效，所以需要配置到spring.factories
 */
@Configuration
@ConditionalOnBean(ConditionalOnBeanBeanB.class)
@AutoConfigureAfter(ConditionalOnBeanConfigB.class)
public class ConditionalOnBeanConfigA {
    @Bean
    public ConditionalOnBeanBeanA conditionalOnBeanBeanA() {
        return new ConditionalOnBeanBeanA();
    }
}
