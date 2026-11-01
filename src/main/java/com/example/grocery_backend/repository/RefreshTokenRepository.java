package com.example.grocery_backend.repository;

import com.example.grocery_backend.entity.RefreshToken;
import com.example.grocery_backend.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Transactional
    int deleteByUser(User user);
}
