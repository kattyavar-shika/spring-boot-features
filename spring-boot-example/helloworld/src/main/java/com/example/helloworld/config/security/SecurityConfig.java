package com.example.helloworld.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    return http
      .sessionManagement( session ->         session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(autorize ->
        autorize
          .requestMatchers("/api/v1/version" ).permitAll()
          .anyRequest().authenticated())
      .formLogin(formlogin -> formlogin.disable())
      .httpBasic(Customizer.withDefaults())
      .csrf(csrf -> csrf.disable())
      .build();

  }

}
