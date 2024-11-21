package com.kattyavar.shika.basic.security.controller;

import com.kattyavar.shika.basic.security.model.User;
import com.kattyavar.shika.basic.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users1")
public class UserController {

  @Autowired
  UserService userService;

  @PostMapping("/")
  User registerUser(@RequestBody User user) {
    return userService.registerUser(user);
  }
}
