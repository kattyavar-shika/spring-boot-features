package com.kattyavar.shika.order_service.service.integeration;

import com.kattyavar.shika.order_service.service.model.integeration.UserAccountInfoInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UserServiceIntegrationImpl implements UserServiceIntegration {

  @Autowired
  WebClient userServiceWebClient;

  @Override
  public UserAccountInfoInt getUserAccount(String userId) {

    return userServiceWebClient
      .get()
      .uri("/users/{userId}", userId)
      .retrieve()
      .bodyToMono(UserAccountInfoInt.class)
      .block();
  }
}

