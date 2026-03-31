package com.sekazedy.enterpriseordermanagementsystem.controller;

import com.sekazedy.enterpriseordermanagementsystem.dto.CreateUserRequest;
import com.sekazedy.enterpriseordermanagementsystem.dto.UserResponse;
import com.sekazedy.enterpriseordermanagementsystem.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping
    public UserResponse create(@RequestBody CreateUserRequest request) {
        return userService.create(request);
    }

    @GetMapping("/{id}")
    public UserResponse get(@PathVariable Long id) {
        return userService.getById(id);
    }
}
