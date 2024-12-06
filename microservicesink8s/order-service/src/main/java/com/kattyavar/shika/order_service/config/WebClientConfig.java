package com.kattyavar.shika.order_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  public WebClient userServiceWebClient(){
    return WebClient.builder()
      .baseUrl("http://localhost:9999/api/v1")
      .build();
  }
}
