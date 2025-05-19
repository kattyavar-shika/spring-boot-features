package com.example.helloworld.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

  @Before("execution(* com.example.helloworld.controller.*.*(..))")
  public void logBefore(JoinPoint joinPoint) {
    log.info("Method is about to be called:  {}", joinPoint.getSignature().getName());
  }
}
