package com.kattyavar.shika.basic.security.controller;

import com.kattyavar.shika.basic.security.model.LoginUserRequest;
import com.kattyavar.shika.basic.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class loginController {

  //From below code to work you need to give bean of AuthenticationManager as below
  /*
    @Bean
  AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
    return configuration.getAuthenticationManager();
  }
   */

  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  JwtService jwtService;

  @PostMapping("")
  public String login(@RequestBody LoginUserRequest payload) {

    Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.userName(), payload.password()));

    if(authentication.isAuthenticated()){
      return jwtService.generateToken(payload.userName());
    }


    return authentication.toString();

  }

}
