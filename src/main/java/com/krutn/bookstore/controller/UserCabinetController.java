package com.krutn.bookstore.controller;

import com.krutn.bookstore.entity.User;
import com.krutn.bookstore.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class UserCabinetController {

    UserService userService;

    @GetMapping("/userCabinet/{userId}")
    public String getUserCabinet(@PathVariable Long userId, Model model) {
        // Отримати дані про користувача з бази даних за допомогою сервісу
        Optional<User> user = userService.getUserById(userId);
        model.addAttribute("user", user);
        // Передати ідентифікатор користувача до шаблону хедера
        model.addAttribute("userId", userId);
        return "userCabinet";
    }

}
