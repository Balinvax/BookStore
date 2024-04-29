package com.krutn.bookstore.repository;

import com.krutn.bookstore.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByCategoryId(Long categoryId);

    public List<Book> findBooksByAuthorId(Long authorId);


    List<Book> findBooksByTitleContainingIgnoreCase(String searchTerm);
}
