package com.foodmap.foodmap_backend.controllers;


import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodmap.foodmap_backend.domain.entities.Role;
import com.foodmap.foodmap_backend.domain.entities.User;
import com.foodmap.foodmap_backend.domain.payload.ApiResponse;
import com.foodmap.foodmap_backend.domain.payload.LoginRequest;
import com.foodmap.foodmap_backend.domain.payload.SignupRequest;
import com.foodmap.foodmap_backend.services.AuthenticationService;
import com.foodmap.foodmap_backend.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path= "api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
 private final AuthenticationService authenticationService;
 private final UserService userService;
 private final PasswordEncoder passwordEncoder;

 

 @PostMapping
 public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) {
    UserDetails userDetails = authenticationService.authenticate(
        loginRequest.getUserName(),
        loginRequest.getPassword()
    );
    User user = userService.getUserByName(loginRequest.getUserName()).orElseThrow();
    if (user.getRole() != Role.CUSTOMER) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, "Access denied!", null, null, null));
    }
    String tokenValue = authenticationService.generateToken(userDetails);
    ApiResponse apiResponse = ApiResponse.builder()
    .success(true)
    .message("Successful Login")
    .token(tokenValue)
    .expiresIn(86400L)
    .Id(user.getId())
    .build();

     ResponseCookie cookie = ResponseCookie.from("jwt", tokenValue)
        .httpOnly(true)
        .secure(false) // Set to true in production (requires HTTPS)
        .maxAge(86400) // Token expiration time in seconds
        .path("/")
        .sameSite("Lax") // Can be "Lax", "Strict", or "None"
        .build();

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(apiResponse);
 }

 @PostMapping(path = "/owner")
 public ResponseEntity<ApiResponse> RestaurantLogin(@RequestBody LoginRequest loginRequest) {
    UserDetails userDetails = authenticationService.authenticate(
        loginRequest.getUserName(),
        loginRequest.getPassword()
    );
    User user = userService.getUserByName(loginRequest.getUserName()).orElseThrow();
    if (user.getRole() != Role.RESTAURANT_OWNER) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, "Access denied!", null, null, null));
    }
    String tokenValue = authenticationService.generateToken(userDetails);
    ApiResponse apiResponse = ApiResponse.builder()
    .success(true)
    .message("Successful Login")
    .token(tokenValue)
    .expiresIn(86400L)
    .Id(user.getId())
    .build();

     ResponseCookie cookie = ResponseCookie.from("jwt", tokenValue)
        .httpOnly(true)
        .secure(false) // Set to true in production (requires HTTPS)
        .maxAge(86400) // Token expiration time in seconds
        .path("/")
        .sameSite("Lax") // Can be "Lax", "Strict", or "None"
        .build();

    return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(apiResponse);
 }

  @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        if (userService.getUserByEmail(signUpRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Email is already in use!",null,null, null));
        }

        // In production, encode the password!
        User user = new User();
        user.setUsername(signUpRequest.getUserName());
        user.setEmail(signUpRequest.getEmail());
        System.out.println(user.getEmail());
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.CUSTOMER);

        userService.creatUser(user);

        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully", null, null, user.getId()));
    }

    @PostMapping("/signup/owner")
    public ResponseEntity<?> registerRestaurantOwner(@RequestBody SignupRequest signUpRequest) {
        if (userService.getUserByEmail(signUpRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Email is already in use!",null,null, null));
        }

        // In production, encode the password!
        User user = new User();
        user.setUsername(signUpRequest.getUserName());
        user.setEmail(signUpRequest.getEmail());
        System.out.println(user.getEmail());
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());
        user.setPassword(encodedPassword);
        user.setRole(Role.RESTAURANT_OWNER);

        userService.creatUser(user);

        return ResponseEntity.ok(new ApiResponse(true, "Business Registered successfully", null, null, user.getId()));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request, HttpServletResponse response) {
        // Invalidate the session if it exists
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }

        // Clear the security context
        SecurityContextHolder.clearContext();

        // Delete the 'jwt' cookie
        ResponseCookie deleteCookie = ResponseCookie.from("jwt", null)
            .httpOnly(true)
            .secure(false) // Should match the original cookie's secure flag
            .maxAge(0) // Set maxAge to 0 to delete the cookie
            .path("/")
            .sameSite("Lax") // Should match the original cookie's sameSite attribute
            .build();

            
        // Add the cookie to the response
        response.addHeader(HttpHeaders.SET_COOKIE, deleteCookie.toString());
    
        return ResponseEntity.ok("Logged out successfully");
    }
    @GetMapping("/redirect")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:5174");
    }
}
