package com.foodmap.foodmap_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.foodmap.foodmap_backend.domain.entities.User;


public interface UserService {
    Optional<User> getUserByName(String username);
    User creatUser(User user);
    List<User> listUsers();
    Optional<User> getUserByEmail(String email);
    Optional<User> getUser(Long userId);

}
