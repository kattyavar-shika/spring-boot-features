package com.kattyavar.shika.users_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

  @Bean
  public WebClient orderWebClient() {
    return WebClient.builder()
      .baseUrl("http://localhost:9090/api/v1/users")
      .build();
  }
}
