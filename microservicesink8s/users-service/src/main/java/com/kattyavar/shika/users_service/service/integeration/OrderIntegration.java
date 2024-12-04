package com.kattyavar.shika.users_service.service.integeration;

import com.kattyavar.shika.users_service.service.integeration.dto.OrderResponse;

public interface OrderIntegration {

  OrderResponse getOrdersByUserId(String userId);
}
