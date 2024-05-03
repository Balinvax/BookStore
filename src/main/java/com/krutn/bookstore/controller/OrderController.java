package com.krutn.bookstore.controller;


import com.krutn.bookstore.dto.OrderDto;
import com.krutn.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order")
    public String orderForm() {
        return "order";
    }

    @PostMapping("/orders")
    public ResponseEntity<String> createOrder(@RequestBody OrderDto orderDto) {
        try {
            orderService.createOrder(orderDto);
            return ResponseEntity.ok("Замовлення успішно створено");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Помилка при створенні замовлення");
        }
    }
}