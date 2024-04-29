package com.krutn.bookstore.controller;

import com.krutn.bookstore.entity.Book;
import com.krutn.bookstore.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {


    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public String searchBooks(@RequestParam(name = "searchTerm") String searchTerm, Model model) {
        List<Book> books = searchService.searchBooks(searchTerm);
        model.addAttribute("books", books);
        return "search"; // Змініть цей рядок відповідно до використаної технології (Thymeleaf або просто HTML)
    }


}
