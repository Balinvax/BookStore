package com.krutn.bookstore.controller;

import com.krutn.bookstore.entity.Book;
import com.krutn.bookstore.service.BookService;
import com.krutn.bookstore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private BookService BookService;

    @GetMapping("/category/{categoryId}")
    public String getCategoryBooks(@PathVariable Long categoryId, Model model) {
        // Отримати книги для даної категорії за її ідентифікатором
        List<Book> books = BookService.getBooksByCategoryId(categoryId);

        // Передати список книг у модель для відображення на сторінці
        model.addAttribute("books", books);

        return "categories";
    }


}