package com.krutn.bookstore.service;

import com.krutn.bookstore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticate(String email, String password) {
        AtomicBoolean isAuthenticated = new AtomicBoolean(false);
        userService.getUserByEmail(email).ifPresent(user -> {
            if (passwordEncoder.matches(password, user.getPassword())) {
                isAuthenticated.set(true);
            }
        });
        return isAuthenticated.get();
    }
}
