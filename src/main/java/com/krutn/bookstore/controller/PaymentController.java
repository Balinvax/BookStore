package com.krutn.bookstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {
    @GetMapping("/payment")
    public String showHelpPage() {
        return "payment"; // Повертаємо назву HTML-файлу для відображення сторінки допомоги
    }
}

