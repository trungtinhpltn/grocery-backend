package com.example.grocery_backend.requestdto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "username or password are not blank!")
    private String username;
    @NotBlank(message = "username or password are not blank!")
    private String password;
}
