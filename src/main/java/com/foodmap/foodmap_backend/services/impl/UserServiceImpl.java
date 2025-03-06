package com.foodmap.foodmap_backend.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.foodmap.foodmap_backend.domain.entities.User;
import com.foodmap.foodmap_backend.repositories.UserRepository;
import com.foodmap.foodmap_backend.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserByName(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User creatUser(User user) {
        if (user.getId() != null)
            throw new IllegalArgumentException("Already have an Id");
        if (user.getUsername() == null || user.getUsername().isEmpty())
            throw new IllegalArgumentException("UserName must not be empty");
        if (user.getPassword() == null || user.getPassword().isEmpty())
            throw new IllegalArgumentException("UserName must not be empty");
        User userToSave = new User(null, user.getUsername(), user.getPassword(), null, null, user.getEmail(), user.getRole(),null, user.getLocation());
        return userRepository.save(userToSave);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

}
