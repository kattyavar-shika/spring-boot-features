package com.kattyavar.shika.users_service.service.integeration;

import com.kattyavar.shika.users_service.service.integeration.dto.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderIntegrationImpl implements OrderIntegration {

  @Autowired
  WebClient orderWebClient;

  @Override
  public OrderResponse getOrdersByUserId(String userId) {

    return orderWebClient
      .get()
      .uri("/{userId}/orders", userId)
      .retrieve()
      .bodyToMono(OrderResponse.class)
      .block();
  }
}
