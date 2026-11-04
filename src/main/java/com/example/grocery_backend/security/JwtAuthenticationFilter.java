package com.example.grocery_backend.security;

import com.example.grocery_backend.entity.User;
import com.example.grocery_backend.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public JwtAuthenticationFilter(JwtService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
                String token = header.substring(7);
                String username = jwtService.extractUsernameIfValid(token);
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    User user = userRepository.findByUsername(username)
                            .orElseThrow(() -> new RuntimeException("User not found!"));
                    var authorities = List.of(new SimpleGrantedAuthority(user.getRole().name()));
                    AuthUser authUser = new AuthUser(user.getId(), user.getUsername(), user.getRole().name());
                    var auth = new UsernamePasswordAuthenticationToken(authUser
                            , null, authorities);
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch (Exception e) {
            logger.error("err");
        }
        filterChain.doFilter(request, response);
    }
}
