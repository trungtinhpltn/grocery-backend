package com.example.grocery_backend.requestdto;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
