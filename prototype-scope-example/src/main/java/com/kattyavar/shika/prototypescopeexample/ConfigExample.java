package com.kattyavar.shika.prototypescopeexample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigExample {

  @Bean
  Cart getCart(){
    return new Cart();
  }
}
