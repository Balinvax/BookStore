package com.krutn.bookstore.repository;

import com.krutn.bookstore.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByCategoryId(Long categoryId);

    public List<Book> findBooksByAuthorId(Long authorId);

    Book findByTitle(String title);

    List<Book> findBooksByTitleContainingIgnoreCase(String searchTerm);

    List<Book> findBooksById(Long id);

    Book findBookById(Long id);
}
