package com.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class TimeAspect {

  @Pointcut("@annotation(com.aop.TimeAopAnnotation)")
  public void pointcut(){}

  @Around("pointcut()")
  public Object runTime(ProceedingJoinPoint pjp) throws Throwable {
    System.out.println(System.currentTimeMillis());
    Object proceed = pjp.proceed();
    System.out.println(System.currentTimeMillis());
    return proceed;
  }
}
