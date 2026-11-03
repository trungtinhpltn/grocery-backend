package com.example.grocery_backend.service;

import com.example.grocery_backend.entity.RefreshToken;
import com.example.grocery_backend.entity.User;
import com.example.grocery_backend.repository.RefreshTokenRepository;
import com.example.grocery_backend.repository.UserRepository;
import com.example.grocery_backend.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository,
                               JwtTokenProvider jwtTokenProvider) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;

    }

    public RefreshToken createRefreshToken(User user) {
        refreshTokenRepository.deleteByUser(user);
        String jwtRefreshToken = jwtTokenProvider.generateRefreshToken(user.getUsername());
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(Instant.now().plusMillis(604800000));
        refreshToken.setToken(jwtRefreshToken);
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token was expiry or invalid");
        }
        return token;
    }

}
