package com.kattyavar.shika.order_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/secure")
public class SecuredTestController {

  @GetMapping
  String getName() {
    return "Hello from secure end point";
  }
}
