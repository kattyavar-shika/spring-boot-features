package com.kattyavar.shika.order_service.service;

import com.kattyavar.shika.order_service.controller.OrderRequest;
import com.kattyavar.shika.order_service.controller.OrderResponse;
import com.kattyavar.shika.order_service.service.model.UserAccountInfo;

public interface OrderService {

  OrderResponse createOrder(OrderRequest orderRequest);
  OrderResponse getOrderByUserId(String userId);

  UserAccountInfo getUserAccount(String userId);
}
