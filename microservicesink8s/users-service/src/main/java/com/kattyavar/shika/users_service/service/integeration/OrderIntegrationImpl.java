package com.kattyavar.shika.users_service.service.integeration;

import com.kattyavar.shika.users_service.service.integeration.dto.OrderInfo;
import com.kattyavar.shika.users_service.service.integeration.dto.OrderResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderIntegrationImpl implements OrderIntegration {

  @Autowired
  WebClient orderWebClient;

  private static final String serviceName = "usersservice";

  @Override
  @CircuitBreaker(name = serviceName, fallbackMethod = "getDefaultOrders")
  public OrderResponse getOrdersByUserId(String userId) {
    return orderWebClient
      .get()
      .uri("/{userId}/orders", userId)
      .retrieve()
      .bodyToMono(OrderResponse.class)
      .block();
  }

  public OrderResponse getDefaultOrders(Exception e) {
    List<OrderInfo> ordersInfo = new ArrayList<>();
    ordersInfo.add(new OrderInfo("Fall back order id", LocalDateTime.now(), 200));
    return new OrderResponse("Fall back data", ordersInfo);
  }

  @Override
  // Without fallback
  // @Retry(name = serviceName)

  //With Fallback
  @Retry(name = serviceName, fallbackMethod = "getDefaultOrders")
  public OrderResponse getOrdersByUserIdWithReTry(String userId) {

    System.out.println("Calling this service now....");
    return orderWebClient
      .get()
      .uri("/{userId}/orders", userId)
      .retrieve()
      .bodyToMono(OrderResponse.class)
      .block();
  }
}
