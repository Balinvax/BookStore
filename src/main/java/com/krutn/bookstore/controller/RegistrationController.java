package com.krutn.bookstore.controller;

import com.krutn.bookstore.entity.User;
import com.krutn.bookstore.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User()); // Додати об'єкт користувача до моделі
        return "registration"; // Повертаємо шаблон для сторінки реєстрації
    }

    @PostMapping
    public String registerUser(User user, Model model) {
        // Реєструємо користувача, якщо реєстрація успішна, повертаємо на сторінку логіну
        registrationService.registerUser(user);
        return "redirect:/login";
    }
}
