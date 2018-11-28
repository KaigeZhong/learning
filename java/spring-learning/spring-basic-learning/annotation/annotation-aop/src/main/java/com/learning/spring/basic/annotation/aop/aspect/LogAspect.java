package com.learning.spring.basic.annotation.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
  /**
   * 第一个“*”符号	表示返回值的类型任意
   * 包名后面的“..” 表示当前包及子包
   * 第二个“*” 表示类名，*即所有类
   * .*(..) 表示任何方法名，括号表示参数，两个点表示任何参数类型
   */
  @Pointcut("execution (* com.learning.spring.basic.annotation.aop.bean..*.*(..))")
  public void pointcutName(){}

  @Before("pointcutName()")
  public void performance(){
    System.out.println("log before");
  }
}
