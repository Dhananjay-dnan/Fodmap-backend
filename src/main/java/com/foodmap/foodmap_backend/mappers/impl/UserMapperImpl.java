package com.foodmap.foodmap_backend.mappers.impl;

import org.springframework.stereotype.Component;

import com.foodmap.foodmap_backend.domain.dto.UserDto;
import com.foodmap.foodmap_backend.domain.entities.User;
import com.foodmap.foodmap_backend.mappers.UserMapper;

@Component
public class UserMapperImpl implements UserMapper{

    @Override
    public User fromDto(UserDto userDto) {
        return new User(userDto.id(), userDto.username(), null, null, userDto.email(), null, userDto.role(), null, null);
    }

    @Override
    public UserDto toDto(User user) {
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getRole());
    }
    
    
}
