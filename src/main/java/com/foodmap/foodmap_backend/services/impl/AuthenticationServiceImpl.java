package com.foodmap.foodmap_backend.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.foodmap.foodmap_backend.services.AuthenticationService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secretKey;

    private final Long jwtExpiryMs = 8640000L;
    @Override
    public UserDetails authenticate(String userName, String password) {
       authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
       return userDetailsService.loadUserByUsername(userName);
    }

    @Override
    public String generateToken(UserDetails userDetails) {       
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
        .claims(claims)
        .subject(userDetails.getUsername())
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis()+ jwtExpiryMs))
        .signWith(getSigningKey())
        .compact();

    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = secretKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public UserDetails validateToken(String token) {
        
        String userName = extractUserName(token);
        return userDetailsService.loadUserByUsername(userName);
    }

    private String extractUserName(String token){
        Claims claims = Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
        return claims.getSubject();
    }
    
}
