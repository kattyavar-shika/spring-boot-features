package com.kattyavar.shika.order_service.service.model;

public class UserAccountInfo {
  private String name;

  public UserAccountInfo() {
  }

  public UserAccountInfo(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
