package com.example.helloworld.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

  @Before("execution(* com.example.helloworld.controller.*.*(..))")
  public void logBefore(JoinPoint joinPoint) {
    System.out.println("Method is about to be called: " + joinPoint.getSignature().getName());
  }
}
