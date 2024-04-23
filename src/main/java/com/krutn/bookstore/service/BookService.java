package com.krutn.bookstore.service;

import com.krutn.bookstore.entity.Book;
import com.krutn.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(int id) {
        return bookRepository.findById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    public Page<Book> getBooksPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }




    public List<Book> getFilteredBooks(Map<String, String> filters) {
        // Отримуємо значення фільтрів
        String author = filters.get("author");
        String genre = filters.get("genre");
        String minPrice = filters.get("minPrice");
        String maxPrice = filters.get("maxPrice");

        // Отримуємо відфільтрований список книг з бази даних
        List<Book> filteredBooks = bookRepository.findByAuthorsInAndGenresInAndPriceBetween();

        return filteredBooks;
    }

    public List<String> getAllAuthors() {
        return bookRepository.findAllAuthors();
    }
}
