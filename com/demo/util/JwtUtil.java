package com.demo.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "mySuperSecretKeyForJwtToken@123456";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10 hours

    //Use a reusable key instance
    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    

    //Generate JWT token with subject and custom role claim
    public String generateToken(String username, String role) 
    {
        return Jwts.builder()
                .setSubject(username)
                .claim("authorities", role)  //Spring Security reads this
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    //Extract username from token 
    public String extractUsername(String token) 
    {
        return getClaims(token).getSubject();
    }
  
    //Extract role from token 
    public String extractRole(String token) 
    {
        return getClaims(token).get("role", String.class);
    }
  
    //Validate token against user details and expiration
    public boolean validateToken(String token, UserDetails userDetails) 
    {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    
    //Check token expiration
    private boolean isTokenExpired(String token) 
    {
        final Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

   
    //Get all claims from token using key
    private Claims getClaims(String token) 
    {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
}
