package com.kattyavar.shika.oauthLogin.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

  //To get the princple object for debug purpose only...
  @GetMapping
  public OAuth2User welcomeMessage(@AuthenticationPrincipal OAuth2User princple) {

    return princple;
    //return "Welcome to Oauth2 demo" + princple.toString();
  }

  //To get session information ...
  @GetMapping("/sid")
  public String welcomeMessage2(HttpServletRequest request) {
    HttpSession session = request.getSession();
    StringBuilder sessionData = new StringBuilder();
    sessionData.append("Session ID: ").append(session.getId()).append("\n");

    session.getAttributeNames().asIterator().forEachRemaining(attribute -> {
      sessionData.append(attribute).append(" = ").append(session.getAttribute(attribute)).append("\n");
    });

    return sessionData.toString();

    // return request.getSession().getId();

  }

}
