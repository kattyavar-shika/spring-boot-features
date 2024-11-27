package com.kattyavar.shika.oauth2withjwttokenflow.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

  @GetMapping("/hello")
  String sayHello(){
    return "Hello";
  }

  @GetMapping("/home")
  String homeDefault(){
    return "Home entry point";
  }

  @GetMapping("/home/oauth")
  public OAuth2User welcomeMessage(@AuthenticationPrincipal OAuth2User princple) {

    return princple;
    //return "Welcome to Oauth2 demo" + princple.toString();
  }
}
