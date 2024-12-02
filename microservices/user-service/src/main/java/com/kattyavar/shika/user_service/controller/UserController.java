package com.kattyavar.shika.user_service.controller;

import com.kattyavar.shika.user_service.feign.UserOrdersInterface;
import com.kattyavar.shika.user_service.model.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

  @Autowired
  Environment environment;

  @Autowired
  UserOrdersInterface userOrdersInterface;

  @GetMapping("hello")
  String hello() throws UnknownHostException {

    InetAddress inetAddress = InetAddress.getLocalHost();


    return "Hello from User service running on port number " + environment.getProperty("local.server.port")
      + inetAddress.getHostAddress();
  }

  @GetMapping("/users/{userId}")
  List<OrderResponse> getOrderByUserId(@PathVariable String userId) {
    return userOrdersInterface.getOrderByUser(userId);
  }


}
