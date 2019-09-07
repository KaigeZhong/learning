package com.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {

  @Pointcut("@annotation(com.aop.LogAopAnnotation)")
  public void pointcut(){}

  @Around("pointcut()")
  public Object runLog(ProceedingJoinPoint pjp) throws Throwable {
    System.out.println("around before");
    Object proceed = pjp.proceed();
    System.out.println("around after");
    return proceed;
  }
}
