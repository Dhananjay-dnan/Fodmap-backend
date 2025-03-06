package com.foodmap.foodmap_backend.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foodmap.foodmap_backend.domain.dto.UserDto;
import com.foodmap.foodmap_backend.domain.entities.User;
import com.foodmap.foodmap_backend.mappers.UserMapper;
import com.foodmap.foodmap_backend.services.UserService;

@RestController
@CrossOrigin(origins = {"http://localhost:5174", "http://localhost:5173"})
@RequestMapping(path = "api/users")
public class UserController {   

    private final UserMapper userMapper;
    private final UserService userService;

    public  UserController(UserMapper userMapper, UserService userService)
    {
        this.userMapper = userMapper;
        this.userService = userService;
    }

    @GetMapping(path = "/name/{user_name}")
    public Optional<UserDto> getUser(@PathVariable("user_name") String username) {
        return userService.getUserByName(username).map(userMapper::toDto);
    }

    @GetMapping(path = "/{user_id}")
    public Optional<UserDto> getUserDetails(@PathVariable("user_id") Long userId) {
        return userService.getUser(userId).map(userMapper::toDto);
    }

    @GetMapping
    public List<UserDto> listUsers() {
        return userService.listUsers().stream().map(userMapper::toDto).toList();
    }

    @PostMapping(path = "/create")
    public UserDto createUser(@RequestBody UserDto userDto){
        User createdUser = userService.creatUser(userMapper.fromDto(userDto));
        return userMapper.toDto(createdUser);
    }

    // @PostMapping("/login")
    // public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
    //     User user = userService.getUser(loginRequest.getUserName()).orElse(null);

    //     if (user == null || !user.getPassword().equals(loginRequest.getPassword())) {
    //         return ResponseEntity.status(401).body(new ApiResponse(false, "Invalid email or password", null, null, null));
    //     }

    //     // In a real application, generate a token (e.g., JWT) and return it
    //     // Here, we'll simulate a token for simplicity
    //     String token = "dummy-token";

    //     return ResponseEntity.ok(new ApiResponse(true, "Login successful", token, 86400L, user.getUsername()));
    // }

    // @PostMapping("/signup")
    // public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
    //     if (userService.getUserByEmail(signUpRequest.getEmail()).isPresent()) {
    //         return ResponseEntity.badRequest().body(new ApiResponse(false, "Email is already in use!",null,null, null));
    //     }

    //     // In production, encode the password!
    //     User user = new User();
    //     user.setUsername(signUpRequest.getUserName());
    //     user.setEmail(signUpRequest.getEmail());
    //     System.out.println(user.getEmail());
    //     user.setPassword(signUpRequest.getPassword());
    //     user.setRole(Role.CUSTOMER);

    //     userService.creatUser(user);

    //     return ResponseEntity.ok(new ApiResponse(true, "User registered successfully", null, null, user.getUsername()));
    // }
}
