package com.kattyavar.shika.conversiondemo.controller;

import com.kattyavar.shika.conversiondemo.convertor.StringToUserConverter;
import com.kattyavar.shika.conversiondemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserController {

  @Autowired
  ConversionService conversionService;
  //Other way
  //StringToUserConverter stringToUserConverter;

  //consume with this url example:  http://localhost:8081/api/v1/user?userInput=test,20

  @GetMapping("/user")
  public User getuser(@RequestParam String userInput) {
    //stringToUserConverter.convert(userInput);
    return conversionService.convert(userInput, User.class);
  }
}
