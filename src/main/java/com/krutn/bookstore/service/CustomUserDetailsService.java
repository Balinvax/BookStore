package com.krutn.bookstore.service;

import com.krutn.bookstore.entity.User;
import com.krutn.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.Collections;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("1");
        System.out.println("Searching for user with email: " + email);
        try {
            User userEntity = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
            System.out.println("2");
            UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(userEntity.getEmail());
            builder.password(userEntity.getPassword());
            builder.authorities(Collections.singletonList(new SimpleGrantedAuthority(getAuthority(userEntity.getRole()))));
            return builder.build();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String getAuthority(int role) {
        if (role == 1) {
            return "ROLE_ADMIN";
        } else if (role == 0) {
            return "ROLE_USER";
        } else {
            // Визначте поведінку для невідомих ролей
            throw new IllegalArgumentException("Unknown role: " + role);
        }
    }
}