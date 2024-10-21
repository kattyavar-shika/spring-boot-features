package com.kattyavar.shika.cacheExample.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {


  @Pointcut("execution(* com.kattyavar.shika.cacheExample.service.PersonImpl.*(..))")
  public void serviceAllMethods() {
  }


  @Before("execution(* com.kattyavar.shika.cacheExample.service.PersonImpl.getPerson(..))")
  public void testing(JoinPoint joinPoint) {
    log.info("In testing part...");
  }

  @Before("serviceAllMethods()")
  public void logBefore(JoinPoint joinPoint) {
    log.info("Before executing {} ", joinPoint.getSignature().getName());
  }

  @After("serviceAllMethods()")
  public void logAfter(JoinPoint joinPoint) {
    log.info("After execution {}", joinPoint.getSignature().getName());
  }

  @Around("serviceAllMethods()")
  public Object logAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    log.info("Around before  method {}  with arg {}", proceedingJoinPoint.getSignature().getName(), proceedingJoinPoint.getArgs());
    long startTime = System.currentTimeMillis(); // Start time
    Object result = proceedingJoinPoint.proceed();
    long executionTime = System.currentTimeMillis() - startTime; // Calculate time taken


    log.info("Around after execution {}, time taken {} ms", proceedingJoinPoint.getSignature().getName(), executionTime);

    return result;
  }

}
