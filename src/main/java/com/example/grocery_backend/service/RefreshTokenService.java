package com.example.grocery_backend.service;

import com.example.grocery_backend.repository.RefreshTokenRepository;
import com.example.grocery_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final Long REFRESH_TOKEN_DURATION_MS = 604800000L;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

//    public RefreshToken createRefreshToken(String username) {
//        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not exit!"));
//        refreshTokenRepository.deleteByUser(user);
//
//
//    }

}
