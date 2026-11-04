package com.example.grocery_backend.service;

import com.example.grocery_backend.dto.LoginRequest;
import com.example.grocery_backend.dto.LoginResponse;
import com.example.grocery_backend.entity.RefreshToken;
import com.example.grocery_backend.entity.User;
import com.example.grocery_backend.repository.UserRepository;
import com.example.grocery_backend.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JwtService jwtService, RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.username()).orElseThrow(() -> new RuntimeException("Tài khoản hoặc mật khẩu không chính xác!"));

        if (!user.getIsActive()) {
            throw new RuntimeException("Tài khoản đang bị khóa");
        }

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new RuntimeException("Tài khoản hoặc mật khẩu không chính xác!");
        }

        String accessToken = jwtService.generateToken(user.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return new LoginResponse(user.getId(), user.getUsername(), user.getFullName(), user.getRole(), accessToken,
                refreshToken.getToken());
    }

}
