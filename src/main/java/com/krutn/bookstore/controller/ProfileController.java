package com.krutn.bookstore.controller;

import com.krutn.bookstore.entity.Order;
import com.krutn.bookstore.entity.User;
import com.krutn.bookstore.service.CustomUserDetails;
import com.krutn.bookstore.service.OrderService;
import com.krutn.bookstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;

    @Autowired
    OrderService orderService;

    @GetMapping("/user/profile")
    public String userCabinet(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            User user = userService.findById(userDetails.getId()).orElseThrow();
            List<Order> orders = orderService.getOrderByUserId(user.getId()); // Отримуємо замовлення користувача

            model.addAttribute("user", user);
            model.addAttribute("orders", orders); // Додаємо список замовлень до моделі
        } else {
            throw new IllegalStateException("Authentication principal is not of type CustomUserDetails");
        }
        return "profile";
    }

    @GetMapping("/user/profile/{orderId}/cancel")
    public ResponseEntity<String> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.ok("Order canceled successfully");
    }
}
