package com.example.grocery_backend.service;

import com.example.grocery_backend.entity.RefreshToken;
import com.example.grocery_backend.entity.User;
import com.example.grocery_backend.repository.UserRepository;
import com.example.grocery_backend.requestdto.LoginRequest;
import com.example.grocery_backend.responsedto.LoginResponse;
import com.example.grocery_backend.security.JwtTokenProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider, RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new RuntimeException("Tài khoản hoặc mật khẩu không chính xác!"));

        if (!user.getIsActive()) {
            throw new RuntimeException("Tài khoản đang bị khóa");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Tài khoản hoặc mật khẩu không chính xác!");
        }

        String accessToken = jwtTokenProvider.generateToken(user.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

        return new LoginResponse(user.getId(), user.getUsername(), user.getFullName(), user.getRole(), accessToken,
                refreshToken.getToken());
    }

}
