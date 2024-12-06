package com.kattyavar.shika.order_service.service.integeration;

import com.kattyavar.shika.order_service.service.model.integeration.UserAccountInfoInt;

public interface UserServiceIntegration {

  UserAccountInfoInt getUserAccount(String userId);
}
