package com.kattyavar.shika.prototypescopeexample;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Scope("prototype")
public class PrototypeCar {
  private final String timeStamp;

  public PrototypeCar() {
    this.timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
  }

  public String getTimeStamp() {
    return timeStamp;
  }
}
