package com.krutn.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    @GetMapping("/contacts")
    public String showContactPage() {
        return "contacts"; // Повертаємо назву HTML-файлу для відображення сторінки контактів
    }
}
