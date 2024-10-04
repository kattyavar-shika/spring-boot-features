package com.kattyavar.shika.prototypescopeexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

@Service
public class PrototypeCarService {

  @Autowired
  private PrototypeCar prototypeCar;

  public String getTimeStamp() {
    return getPrototypeCar().getTimeStamp();
  }

  @Lookup
  protected PrototypeCar getPrototypeCar() {
    return null;
  }

}
