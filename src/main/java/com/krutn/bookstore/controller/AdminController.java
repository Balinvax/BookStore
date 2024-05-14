package com.krutn.bookstore.controller;

import com.krutn.bookstore.entity.Book;
import com.krutn.bookstore.entity.Author;
import com.krutn.bookstore.entity.Order;
import com.krutn.bookstore.repository.OrderRepository;
import com.krutn.bookstore.service.AuthorService;
import com.krutn.bookstore.service.BookService;
import com.krutn.bookstore.service.CategoryService;
import com.krutn.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    BookService bookService;

    @Autowired
    OrderService orderService;

    @Autowired
    AuthorService authorService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    private OrderRepository orderRepository;


    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        // Передаємо в модель списки книг та замовлень для відображення на головній сторінці адміністратора
        model.addAttribute("books", bookService.getAllBooks());
        model.addAttribute("orders", orderService.findAll());
        return "admin/dashboard";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "admin/books";
    }

    @GetMapping("/books/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/add-book";
    }

    @PostMapping("/books/add")
    public String addBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("message", "Книгу успішно додано!");
        return "redirect:/admin/books";
    }

    @GetMapping("/books/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            model.addAttribute("error", "Книга не знайдена!");
            return "admin/books";
        }
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthors());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/edit-book";
    }

    @PostMapping("/books/update")
    public String updateBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("message", "Книгу успішно оновлено!");
        return "redirect:/admin/books";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookService.deleteBook(id);
        redirectAttributes.addFlashAttribute("message", "Книгу успішно видалено!");
        return "redirect:/admin/books";
    }

    @GetMapping("/orders")
    public String listOrders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "admin/orders";
    }

    @GetMapping("/orders/edit/{id}")
    public String showEditOrderForm(@PathVariable Long id, Model model) {
        Order order = orderService.findById(id);
        if (order == null) {
            model.addAttribute("error", "Замовлення не знайдено!");
            return "admin/orders";
        }
        model.addAttribute("order", order);
        return "admin/edit-order";
    }

    @PostMapping("/orders/update")
    public String updateOrder(@ModelAttribute Order order, RedirectAttributes redirectAttributes) {
        orderRepository.save(order);
        redirectAttributes.addFlashAttribute("message", "Статус замовлення успішно оновлено!");
        return "redirect:/admin/orders";
    }

    @GetMapping("/authors")
    public String listAuthors(Model model) {
        model.addAttribute("authors", authorService.getAllAuthors());
        return "admin/authors";
    }

    @GetMapping("/authors/add")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "admin/add-author";
    }

    @PostMapping("/authors/add")
    public String addAuthor(@ModelAttribute Author author, RedirectAttributes redirectAttributes) {
        authorService.saveAuthor(author);
        redirectAttributes.addFlashAttribute("message", "Автор успішно доданий!");
        return "redirect:/admin/authors";
    }

    @GetMapping("/authors/edit/{id}")
    public String showEditAuthorForm(@PathVariable Long id, Model model) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            model.addAttribute("error", "Автор не знайдений!");
            return "admin/authors";
        }
        model.addAttribute("author", author);
        return "admin/edit-author";
    }

    @PostMapping("/authors/update")
    public String updateAuthor(@ModelAttribute Author author, RedirectAttributes redirectAttributes) {
        authorService.saveAuthor(author);
        redirectAttributes.addFlashAttribute("message", "Автор успішно оновлений!");
        return "redirect:/admin/authors";
    }

    @GetMapping("/authors/delete/{id}")
    public String deleteAuthor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        authorService.deleteAuthor(id);
        redirectAttributes.addFlashAttribute("message", "Автор успішно видалений!");
        return "redirect:/admin/authors";
    }
}