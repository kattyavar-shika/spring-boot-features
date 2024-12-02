package com.kattyavar.shika.user_order_service.controller;


import com.kattyavar.shika.user_order_service.model.Order;
import com.kattyavar.shika.user_order_service.model.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

  private Map<String, List<Order>> ordersByUsers;

  @Value("${spring.application.name}")
  private String applicationName;

  @Autowired
  Environment environment;

  public OrderController() {
    ordersByUsers = new HashMap<>();

    ordersByUsers.put("1", prepareOrderData("1"));
    ordersByUsers.put("2", prepareOrderData("2"));
    ordersByUsers.put("3", prepareOrderData("3"));
  }

  @GetMapping("/user/{userId}")
  OrderResponse getOrderByUser(@PathVariable String userId) throws UnknownHostException {

    String port = environment.getProperty("local.server.port");
    InetAddress inetAddress = InetAddress.getLocalHost();

    String ip = inetAddress.getHostAddress();

    List<Order> orders = ordersByUsers.get(userId);

    return new OrderResponse(ip, port, applicationName, orders);
  }

  private List<Order> prepareOrderData(String userId) {
    List<Order> list = new ArrayList<>();
    list.add(new Order(userId, userId + "1", "Order 1 for user" + userId));
    list.add(new Order(userId, userId + "2", "Order 2 for user" + userId));
    list.add(new Order(userId, userId + "3", "Order 3 for user" + userId));
    return list;
  }

}
