package com.sekazedy.enterpriseordermanagementsystem.service;

import com.sekazedy.enterpriseordermanagementsystem.dto.CreateUserRequest;
import com.sekazedy.enterpriseordermanagementsystem.dto.UserResponse;
import com.sekazedy.enterpriseordermanagementsystem.model.User;
import com.sekazedy.enterpriseordermanagementsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public UserResponse create(CreateUserRequest request) {
        User user = new User(request.getUserName(), request.getEmail());
        userRepository.save(user);

        return toResponse(user);
    }

    public UserResponse getById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return toResponse(user);
    }

    private UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUserName(),
                user.getEmail()
        );
    }
}
