package com.kattyavar.shika.prototypescopeexample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
public class PrototypeScopeExampleApplication {

  public static void main(String[] args) throws InterruptedException {
    ConfigurableApplicationContext run = SpringApplication.run(PrototypeScopeExampleApplication.class, args);

    PrototypeCar obj1 = run.getBean(PrototypeCar.class);
    log.info("Obj1 timestamp " + obj1.getTimeStamp());

    Thread.sleep(2000);

    PrototypeCar obj2 = run.getBean(PrototypeCar.class);
    log.info("Obj1 timestamp " + obj2.getTimeStamp());


  }

}
