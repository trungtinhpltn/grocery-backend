package com.example.grocery_backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtService {
    private final Key key;
    private final long JWT_EXPIRATION;
    private final long REFRESH_TOKEN_EXPIRATION;

    public JwtService(@Value("${app.jwt.secret}") String secret,
                      @Value("${app.jwt.expiration-ms}") long expiration,
                      @Value("${app.jwt.refresh_token_expiration-ms}") long rfExpiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.JWT_EXPIRATION = expiration;
        this.REFRESH_TOKEN_EXPIRATION = rfExpiration;
    }


    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
        return Jwts.builder().setSubject(username).setIssuedAt(now).setExpiration(expiryDate).signWith(key,
                SignatureAlgorithm.HS256).compact();
    }

    public String generateRefreshToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + REFRESH_TOKEN_EXPIRATION);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith((key), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims parse(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsernameIfValid(String token) {
        try {
            return parse(token).getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            return null;
        }
    }
}
