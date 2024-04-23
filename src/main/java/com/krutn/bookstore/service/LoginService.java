package com.krutn.bookstore.service;

import com.krutn.bookstore.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticate(String email, String password) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            String storedPassword = user.getPassword();
            return passwordEncoder.matches(password, storedPassword);
        }
        return false;
    }
}
