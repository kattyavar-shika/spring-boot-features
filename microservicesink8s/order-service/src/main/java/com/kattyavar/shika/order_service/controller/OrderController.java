package com.kattyavar.shika.order_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class OrderController {

  Map<String, OrderResponse> dummyData;

  public OrderController() {
    dummyData = new HashMap<>();

    dummyData.put("1", generateOrderData("1"));
    dummyData.put("2", generateOrderData("2"));
    dummyData.put("3", generateOrderData("3"));
    dummyData.put("4", generateOrderData("4"));
  }

  @GetMapping("{userId}/orders")
  public OrderResponse getOrdersByUserId(@PathVariable String userId) {
    return dummyData.get(userId);
  }

  private OrderResponse generateOrderData(String userId) {
    List<OrderInfo> orders = new ArrayList<>();
    orders.add(new OrderInfo("Order id 1 for user id " + userId, LocalDateTime.now(), 100));
    orders.add(new OrderInfo("Order id 2 for user id " + userId, LocalDateTime.now(), 200));
    orders.add(new OrderInfo("Order id 3 for user id " + userId, LocalDateTime.now(), 250));
    orders.add(new OrderInfo("Order id 4 for user id " + userId, LocalDateTime.now(), 250));
    return new OrderResponse(userId, orders);
  }

}
