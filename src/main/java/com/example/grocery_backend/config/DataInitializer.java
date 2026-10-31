package com.example.grocery_backend.config;

import com.example.grocery_backend.entity.Role;
import com.example.grocery_backend.entity.User;
import com.example.grocery_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByUsername("spadmin").isEmpty()) {
            User admin = new User();
            admin.setUsername("spadmin");
            admin.setPassword(passwordEncoder.encode("123456789"));
            admin.setFullName("Supper Admin");
            admin.setPhoneNumber("0");
            admin.setRole(Role.ROLE_ADMIN);
            admin.setIsActive(true);
            userRepository.save(admin);
            System.out.println("\t--[Khởi tạo]: tài khoản spadmin");
        }
    }
}
