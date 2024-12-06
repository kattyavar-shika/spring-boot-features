package com.kattyavar.shika.config_server.controller;

import com.kattyavar.shika.config_server.config.AppConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/config")
public class TestController {


  @Autowired
  AppConfig appConfig;

  @GetMapping
  public String getConfig(){
    return appConfig.toString();
  }
}
