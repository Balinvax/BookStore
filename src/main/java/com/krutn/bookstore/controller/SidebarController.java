package com.krutn.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SidebarController {

    @GetMapping("/sidebar.html")
    public String showSidebar() {
        return "sidebar"; // Повертаємо назву шаблону для бокового меню
    }
}
