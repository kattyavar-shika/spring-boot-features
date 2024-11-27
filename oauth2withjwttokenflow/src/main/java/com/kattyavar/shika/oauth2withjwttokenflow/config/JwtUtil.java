package com.kattyavar.shika.oauth2withjwttokenflow.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

  private static final String SECRET_KEY = "fjldkfjdlfjsdklflsdjfldjfsdlfjsdkfjdfdfsdfsdfsdfsdfwererevwer";  // Use a secure key
  private static final long EXPIRATION_TIME = 1000 * 60 * 60;  // 1 ho

  // Generate JWT token after successful OAuth2 authentication
  public static String generateJwtToken(org.springframework.security.oauth2.core.user.OAuth2User oAuth2User) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("Key1", "my key1");
    claims.putAll(oAuth2User.getAttributes());

    return Jwts.builder()
      .setClaims(claims)
      .setSubject(oAuth2User.getName())  // Use user name as the subject
      .claim("userId", oAuth2User.getAttributes().get("sub"))  // Store the user ID as a claim
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // Set expiration time
      .signWith(SignatureAlgorithm.HS256, SECRET_KEY)  // Sign the token with the secret key
      .compact();
  }

  // Extract user ID from JWT token
  public static String extractUserId(String jwtToken) {
    Claims claims = Jwts.parser()
      .setSigningKey(SECRET_KEY)
      .parseClaimsJws(jwtToken)
      .getBody();
    return claims.get("userId", String.class);
  }

  // Get authentication from JWT token
  public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
    Claims claims = Jwts.parser()
      .setSigningKey(SECRET_KEY)
      .parseClaimsJws(token)
      .getBody();

    String userId = claims.get("userId", String.class);
    // In a real scenario, you should fetch user details from a database
    User principal = new User(userId, "", Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));

    return new UsernamePasswordAuthenticationToken(principal, token, principal.getAuthorities());
  }
}
