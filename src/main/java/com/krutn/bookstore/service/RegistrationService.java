package com.krutn.bookstore.service;


import com.krutn.bookstore.entity.User;
import com.krutn.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        // Хешуємо пароль перед збереженням
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        // Зберігаємо користувача в базу даних
        System.out.println("1 + " + hashedPassword);
        return userRepository.save(user);
    }
}
