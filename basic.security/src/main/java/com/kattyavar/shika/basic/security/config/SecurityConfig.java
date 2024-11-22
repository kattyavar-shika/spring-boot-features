package com.kattyavar.shika.basic.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private JwtFilter jwtFilter;

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    //authenticationProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
    authenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
    return authenticationProvider;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    return http
      .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(authorize -> authorize
        .requestMatchers("/api/v1/open/**", "/api/v1/login").permitAll()
        .anyRequest().authenticated()
      )
      .formLogin(formeLoing -> formeLoing.disable())
      .httpBasic(Customizer.withDefaults())
      .build();

  }

  @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }
  /*

  // In case you would like to give inMemory user names...

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails userDetails1 = User
      .withDefaultPasswordEncoder()
      .username("temp1")
      .password("test1pass")
      .roles("USER")
      .build();

    UserDetails userDetails2 = User
      .withDefaultPasswordEncoder()
      .username("temp2")
      .password("test2pass")
      .roles("USER")
      .build();


    InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(userDetails1, userDetails2);

    return inMemoryUserDetailsManager;

  }

   */

}
