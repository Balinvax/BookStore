package com.krutn.bookstore.repository;

import com.krutn.bookstore.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
    // Додаткові методи можна додавати тут
}
