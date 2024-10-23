package com.kattyavar.shika.basic.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    return http
      .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/api/v1/open/**").permitAll()
        .anyRequest().authenticated()
      )
      .formLogin(formeLoing -> formeLoing.disable())
      .httpBasic(Customizer.withDefaults())
      .build();

  }
}
