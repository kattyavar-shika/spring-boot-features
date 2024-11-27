package com.kattyavar.shika.oauth2withjwttokenflow.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


  private final OAuth2AuthorizedClientService authorizedClientService;

  public CustomAuthenticationSuccessHandler(OAuth2AuthorizedClientService authorizedClientService) {
    this.authorizedClientService = authorizedClientService;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

    if (authentication instanceof OAuth2AuthenticationToken) {
      OAuth2AuthenticationToken oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

      // Get the OAuth2AuthorizedClient for the current authenticated user
      OAuth2AuthorizedClient authorizedClient = authorizedClientService.loadAuthorizedClient(
        oauth2AuthenticationToken.getAuthorizedClientRegistrationId(),
        oauth2AuthenticationToken.getName()
      );

      if (authorizedClient != null) {
        // Extract the access token, refresh token, and ID token
        String accessToken = authorizedClient.getAccessToken().getTokenValue();
        String refreshToken = authorizedClient.getRefreshToken() != null
          ? authorizedClient.getRefreshToken().getTokenValue()
          : null;

        String idToken = null;
        if (authentication.getPrincipal() instanceof DefaultOidcUser) {
          DefaultOidcUser defaultOidcUser = (DefaultOidcUser) authentication.getPrincipal();
          idToken = (String) defaultOidcUser.getIdToken().getTokenValue();
        }

        // Store tokens in a secure place (e.g., HTTP header, session, or custom storage)
        storeTokens(response, accessToken, refreshToken, idToken);
      }

      // Redirect user after successful authentication (or handle as needed)
      response.sendRedirect("/home");  // Redirect to a secured page or dashboard
    }
  }

  private void storeTokens(HttpServletResponse response, String accessToken, String refreshToken, String idToken) {
    // Example of storing tokens in HTTP headers (not recommended for production due to security concerns)
    response.setHeader("Authorization", "Bearer " + accessToken);  // Store the access token in the header

    // Optionally, store refresh and id tokens in a secure place
    if (refreshToken != null) {
      response.setHeader("X-Refresh-Token", refreshToken);  // Store the refresh token
    }
    if (idToken != null) {
      response.setHeader("X-ID-Token", idToken);  // Store the ID token
    }


    Cookie accessTokenCookie = new Cookie("access_token", accessToken);
    accessTokenCookie.setHttpOnly(true);
    accessTokenCookie.setSecure(true); // Ensure cookie is only sent over HTTPS
    accessTokenCookie.setPath("/"); // Set path to ensure the cookie is sent with every request
    accessTokenCookie.setMaxAge(3600); // Set expiry time (optional)
    response.addCookie(accessTokenCookie);

    if (refreshToken != null) {
      Cookie refreshTokenCookie = new Cookie("refresh_token", refreshToken);
      refreshTokenCookie.setHttpOnly(true);
      refreshTokenCookie.setSecure(true);
      refreshTokenCookie.setPath("/");
      refreshTokenCookie.setMaxAge(3600);
      response.addCookie(refreshTokenCookie);
    }

    if (idToken != null) {
      Cookie idTokenCookie = new Cookie("id_token", idToken);
      idTokenCookie.setHttpOnly(true);
      idTokenCookie.setSecure(true);
      idTokenCookie.setPath("/");
      idTokenCookie.setMaxAge(3600);
      response.addCookie(idTokenCookie);
    }

    // You can also store tokens in a database, session, or elsewhere, depending on your use case
    // E.g., Session, User object, or custom token store.
  }

}
