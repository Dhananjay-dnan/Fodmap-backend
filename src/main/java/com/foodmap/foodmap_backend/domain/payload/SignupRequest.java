package com.foodmap.foodmap_backend.domain.payload;

import lombok.Data;

@Data
public class SignupRequest {
    private String userName;
    private String email;
    private String password;
}
