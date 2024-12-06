package com.kattyavar.shika.order_service.controller;

import com.kattyavar.shika.order_service.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/config")
public class ConfigTestController {

  @Autowired
  AppConfig appConfig;

  @GetMapping
  public String getConfig() {
    return appConfig.toString();
  }

}
