package com.kattyavar.shika.basic.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
  String SECRET_KEY = "fjldkfjdlfjsdklflsdjfldjfsdlfjsdkfjdfdfsdfsdfsdfsdfwererevwer";

  public String generateToken(String userName) {

    Map<String, Object> claims = new HashMap<>();

    try {


      return Jwts.builder()
        .setClaims(claims)
        .setSubject(userName)
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60*60))
        .signWith(getKey(), SignatureAlgorithm.HS256).compact();
    } catch (Exception exception) {
      System.out.println("Got exception " + exception.toString());
    }
    return "Unable to create token";
  }

  private Key getKey() {

    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);

    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String extractUserName(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public boolean validateToken(String token, UserDetails userDetails) {

    final String userName = extractUserName(token);
    return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token)
      .before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }


  private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
    final Claims claims = extractAllClaims(token);
    return claimResolver.apply(claims);
  }


  private Claims extractAllClaims(String token) {

    return Jwts.parserBuilder()
      .setSigningKey(getKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }


}
