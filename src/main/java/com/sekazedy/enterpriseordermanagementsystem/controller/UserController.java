package com.sekazedy.enterpriseordermanagementsystem.controller;

import com.sekazedy.enterpriseordermanagementsystem.dto.CreateUserRequest;
import com.sekazedy.enterpriseordermanagementsystem.dto.UserResponse;
import com.sekazedy.enterpriseordermanagementsystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "Users", description = "Operations related to users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @Operation(summary = "Create a new user", description = "Creates a user with username and email")
    @PostMapping
    public UserResponse create(@Valid @RequestBody CreateUserRequest request) {
        return userService.create(request);
    }

    @Operation(summary = "Get user by ID", description = "Fetches user data by user ID")
    @GetMapping("/{id}")
    public UserResponse get(@PathVariable Long id) {
        return userService.getById(id);
    }
}
