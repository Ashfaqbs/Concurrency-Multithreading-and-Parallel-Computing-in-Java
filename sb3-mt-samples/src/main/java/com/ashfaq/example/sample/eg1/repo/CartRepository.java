package com.ashfaq.example.sample.eg1.repo;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ashfaq.example.sample.eg1.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}

