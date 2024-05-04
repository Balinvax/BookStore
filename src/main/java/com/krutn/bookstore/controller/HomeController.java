package com.krutn.bookstore.controller;

import com.krutn.bookstore.entity.Sale;
import com.krutn.bookstore.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final SaleService saleService;

    @Autowired
    public HomeController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/")
    public String home(Model model) {
        // Отримуємо список акційних товарів
        List<Sale> sales = saleService.getAllSales();
        model.addAttribute("sales", sales); // Передаємо їх на головну сторінку

        return "home";
    }
}