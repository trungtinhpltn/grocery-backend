package com.example.grocery_backend.dto;

import com.example.grocery_backend.entity.Role;

//import com.example.grocery_backend.entity.Role;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//
//@Data
//@AllArgsConstructor
//public class LoginResponse {
//    private Long id;
//    private String username;
//    private String fullName;
//    private Role role;
//    private String accessToken;
//    private String refreshToken;
//}
public record LoginResponse(Long id, String username, String fullname, Role role, String accessToken,
                            String refreshToken
) {
}