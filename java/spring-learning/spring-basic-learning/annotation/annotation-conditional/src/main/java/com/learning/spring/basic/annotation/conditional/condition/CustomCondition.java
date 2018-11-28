package com.learning.spring.basic.annotation.conditional.condition;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * ConfigurationClassPostProcessor.processConfigBeanDefinitions --> parser.parse(candidates) ... ... -->ConfigurationClassParser -> conditionEvaluator.shouldSkip --> condition.matches
 */
public class CustomCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("os.name").contains("Linux");
//        return context.getEnvironment().getProperty("os.name").contains("Windows");
    }

}