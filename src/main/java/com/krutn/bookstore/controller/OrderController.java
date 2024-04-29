package com.krutn.bookstore.controller;


import com.krutn.bookstore.entity.Order;
import com.krutn.bookstore.exeption.ResourceNotFoundException;
import com.krutn.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderController {

    @GetMapping("/order")
    public String showOrderForm() {
        return "order"; // Повертаємо назву вашого HTML-файлу для сторінки оформлення замовлення
    }

    @PostMapping("/order")
    @ResponseBody
    public String processOrder(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("address") String address,
            @RequestParam("phone") String phone
    ) {
        // Тут ви можете додати логіку для обробки інформації про замовлення
        // Наприклад, зберігати замовлення в базі даних або відправляти електронний лист із деталями замовлення

        // Повертаємо повідомлення про успішне оформлення замовлення
        return "Ваше замовлення успішно оформлено!";
    }
}