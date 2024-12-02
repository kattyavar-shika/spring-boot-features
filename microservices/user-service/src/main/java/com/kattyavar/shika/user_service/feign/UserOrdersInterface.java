package com.kattyavar.shika.user_service.feign;

import com.kattyavar.shika.user_service.model.OrderResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("USER-ORDER-SERVICE")
public interface UserOrdersInterface {
  @GetMapping("api/v1/orders/user/{userId}")
  List<OrderResponse> getOrderByUser(@PathVariable String userId);
}
