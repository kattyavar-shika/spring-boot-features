package com.kattyavar.shika.oauth2withjwttokenflow.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
      .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/oauth2/**", "/login/**", "/login", "/error").permitAll()
        .anyRequest().authenticated()
      )
      .csrf(AbstractHttpConfigurer::disable)
      .oauth2Login(oauth2 -> oauth2.successHandler(authenticationSuccessHandler()))
      //.oauth2Login(Customizer.withDefaults());
      .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  //Let build here AuthenticationSuccessHandler

  private AuthenticationSuccessHandler authenticationSuccessHandler (){
    return ((request, response, authentication) -> {

      org.springframework.security.oauth2.core.user.OAuth2User oAuth2User = (org.springframework.security.oauth2.core.user.OAuth2User) authentication.getPrincipal();

      String jwtToken = JwtUtil.generateJwtToken(oAuth2User);
      response.addHeader("Authorization", "Bearer " + jwtToken);
      response.sendRedirect("/api/v1/home");

    });

  }
}
