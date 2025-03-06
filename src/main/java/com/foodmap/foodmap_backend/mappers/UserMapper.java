package com.foodmap.foodmap_backend.mappers;

import com.foodmap.foodmap_backend.domain.dto.UserDto;
import com.foodmap.foodmap_backend.domain.entities.User;

public interface UserMapper {

    User fromDto(UserDto userDto);
    UserDto toDto(User user);
    
}
