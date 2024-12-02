package com.kattyavar.shika.user_order_service.controller;


import com.kattyavar.shika.user_order_service.model.OrderResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

  private Map<String, List<OrderResponse>> ordersByUsers;

  public OrderController() {
    ordersByUsers = new HashMap<>();

    ordersByUsers.put("1", prepareOrderData("1"));
    ordersByUsers.put("2", prepareOrderData("2"));
    ordersByUsers.put("3", prepareOrderData("3"));
  }

  @GetMapping("/user/{userId}")
  List<OrderResponse> getOrderByUser(@PathVariable String userId) {
    return ordersByUsers.get(userId);
  }

  private List<OrderResponse> prepareOrderData(String userId) {
    List<OrderResponse> list = new ArrayList<>();
    list.add(new OrderResponse(userId, userId + "1", "Order 1 for user" + userId));
    list.add(new OrderResponse(userId, userId + "2", "Order 2 for user" + userId));
    list.add(new OrderResponse(userId, userId + "3", "Order 3 for user" + userId));
    return list;
  }

}
