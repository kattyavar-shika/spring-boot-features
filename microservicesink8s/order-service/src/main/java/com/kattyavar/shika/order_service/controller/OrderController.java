package com.kattyavar.shika.order_service.controller;

import com.kattyavar.shika.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class OrderController {

  @Autowired
  OrderService orderService;

  @GetMapping("{userId}/orders")
  public OrderResponse getOrdersByUserId(@PathVariable String userId) {

    return orderService.getOrderByUserId(userId);
  }

  @PostMapping("/{userId}/orders")
  public OrderResponse createOrder(@RequestBody OrderRequest orders) {
    return orderService.createOrder(orders);
  }

}
