package com.foodmap.foodmap_backend.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.foodmap.foodmap_backend.domain.entities.User;
import com.foodmap.foodmap_backend.repositories.UserRepository;
import com.foodmap.foodmap_backend.security.CustomerUserDetails;

@Service
public class CustomerUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;

    public CustomerUserDetailsService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new CustomerUserDetails(user);
    }

    
}
