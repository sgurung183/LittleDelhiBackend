package com.LittleDelhi.LittleDelhiBackend.service;

import com.LittleDelhi.LittleDelhiBackend.dto.request.CreateUserRequest;
import com.LittleDelhi.LittleDelhiBackend.dto.response.UserResponse;
import com.LittleDelhi.LittleDelhiBackend.model.User;
import com.LittleDelhi.LittleDelhiBackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(CreateUserRequest request) {
        User saved = userRepository.save(
                User.builder()
                        .name(request.getName())
                        .userName(request.getUserName())
                        .password(request.getPassword())
                        .build()
        );
        return toResponse(saved);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream() // opens a pipeline on the list
                .map(user -> toResponse(user))   // transforms each User into a UserResponse
                .toList();                        // collects results back into a List
    }

    public Optional<UserResponse> getUserById(Long id) {
        return userRepository.findById(id).map(user -> toResponse(user));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .userName(user.getUserName())
                .role(user.getRole())
                .build();
    }
}
