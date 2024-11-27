package com.kattyavar.shika.oauth2withjwttokenflow.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private static final String HEADER_AUTHORIZATION = "Authorization";
  private static final String TOKEN_PREFIX = "Bearer ";

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    // Extract JWT token from Authorization header
    String header = request.getHeader(HEADER_AUTHORIZATION);
    if (header != null && header.startsWith(TOKEN_PREFIX)) {
      String jwtToken = header.substring(TOKEN_PREFIX.length());

      // Validate JWT token and extract user info
      String userId = JwtUtil.extractUserId(jwtToken);
      if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        // If the token is valid, authenticate the user
        var authentication = JwtUtil.getAuthentication(jwtToken);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // Set the authentication object in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    // Continue with the filter chain
    filterChain.doFilter(request, response);
  }
}
