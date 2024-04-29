package com.krutn.bookstore.service;

import com.krutn.bookstore.entity.Author;
import com.krutn.bookstore.entity.Book;
import com.krutn.bookstore.repository.AuthorRepository;
import com.krutn.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;


    public List<Book> searchBooks(String searchTerm) {
        // Перевірка, чи є значення searchTerm порожнім або містить лише пробіли
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return Collections.emptyList(); // Повернення порожнього списку, якщо searchTerm порожній
        }

        searchTerm = searchTerm.toLowerCase(); // Перетворення на нижній регістр

        // Пошук авторів за частковим збігом
        List<Author> authors = authorRepository.findByNameContainingIgnoreCase(searchTerm);
        if (!authors.isEmpty()) {
            List<Book> books = new ArrayList<>();
            for (Author author : authors) {
                books.addAll(bookRepository.findBooksByAuthorId(author.getId()));
            }
            return books;
        }

        // Пошук книг за частковим збігом назви
        return bookRepository.findBooksByTitleContainingIgnoreCase(searchTerm);
    }




}
