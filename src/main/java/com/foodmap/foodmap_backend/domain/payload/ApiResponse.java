package com.foodmap.foodmap_backend.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiResponse {
    private Boolean success;
    private String message;
    private String token;
    private Long expiresIn;
    private Long Id;
}
