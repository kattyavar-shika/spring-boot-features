package com.kattyavar.shika.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

  @Autowired
  Environment environment;

  @GetMapping("hello")
  String hello(){

    return "Hello from User service running on port number " + environment.getProperty("local.server.port");
  }


}
