package com.kattyavar.shika.user_service.controller;

import com.kattyavar.shika.user_service.feign.UserOrdersInterface;
import com.kattyavar.shika.user_service.model.OrderResponse;
import com.kattyavar.shika.user_service.model.UserOrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping("/api/v1")
public class UserController {

  @Autowired
  Environment environment;

  @Value("${spring.application.name}")
  private String applicationName;
  @Autowired
  UserOrdersInterface userOrdersInterface;

  @GetMapping("hello")
  String hello() throws UnknownHostException {

    InetAddress inetAddress = InetAddress.getLocalHost();


    return "Hello from User service running on port number " + environment.getProperty("local.server.port")
      + inetAddress.getHostAddress();
  }

  @GetMapping("/users/{userId}")
  UserOrderResponse getOrderByUserId(@PathVariable String userId) throws UnknownHostException {
    OrderResponse orderResponse = userOrdersInterface.getOrderByUser(userId);

    InetAddress inetAddress = InetAddress.getLocalHost();

    return new UserOrderResponse(inetAddress.getHostAddress(), environment.getProperty("local.server.port"), applicationName, orderResponse);

  }


}
