package com.krutn.bookstore.repository;

import com.krutn.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail (String email);


}
