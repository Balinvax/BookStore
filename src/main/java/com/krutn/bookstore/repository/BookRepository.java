package com.krutn.bookstore.repository;

import com.krutn.bookstore.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;


public interface BookRepository extends JpaRepository<Book, Integer> {



}
