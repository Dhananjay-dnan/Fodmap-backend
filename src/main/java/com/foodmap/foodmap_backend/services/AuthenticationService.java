package com.foodmap.foodmap_backend.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthenticationService{
    UserDetails authenticate(String userName, String password);
    String generateToken(UserDetails userDetails);
    UserDetails validateToken(String token);
}
