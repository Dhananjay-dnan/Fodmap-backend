package com.foodmap.foodmap_backend.domain.payload;

import lombok.Data;

@Data
public class LoginRequest {
    private String userName;
    private String password;
}
