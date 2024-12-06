package com.kattyavar.shika.order_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Value("${service.order.base.url}") // Injecting the value from properties
  String url;

  @Bean
  public WebClient userServiceWebClient() {
    return WebClient.builder()
      .baseUrl(url)
      .build();
  }
}
