package com.example.grocery_backend.responsedto;

import com.example.grocery_backend.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private Long id;
    private String username;
    private String fullName;
    private Role role;
    private String accToken;
    private String refreshToken;
}
