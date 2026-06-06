package com.LittleDelhi.LittleDelhiBackend.dto.response;

import com.LittleDelhi.LittleDelhiBackend.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String userName;
    private Role role;
}
