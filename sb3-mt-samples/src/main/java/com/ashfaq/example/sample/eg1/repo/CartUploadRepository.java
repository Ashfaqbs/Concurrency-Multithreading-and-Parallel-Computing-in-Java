package com.ashfaq.example.sample.eg1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashfaq.example.sample.eg1.entity.CartUpload;

public interface CartUploadRepository extends JpaRepository<CartUpload, String> {
}
