package com.kattyavar.shika.users_service.service;

import com.kattyavar.shika.users_service.model.UserOrderResponse;

public interface UserService {

  UserOrderResponse getUserOrdersByUserId(String userId);
}
