package com.LittleDelhi.LittleDelhiBackend.mapper;

import com.LittleDelhi.LittleDelhiBackend.dto.response.UserResponse;
import com.LittleDelhi.LittleDelhiBackend.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .userName(user.getUserName())
                .role(user.getRole())
                .build();
    }
}
