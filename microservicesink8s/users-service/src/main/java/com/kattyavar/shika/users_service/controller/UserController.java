package com.kattyavar.shika.users_service.controller;

import com.kattyavar.shika.users_service.model.UserOrderResponse;
import com.kattyavar.shika.users_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/{userId}/orders")
  UserOrderResponse getUserOrderInformation(@PathVariable String userId) {
    return userService.getUserOrdersByUserId(userId);
  }

}
