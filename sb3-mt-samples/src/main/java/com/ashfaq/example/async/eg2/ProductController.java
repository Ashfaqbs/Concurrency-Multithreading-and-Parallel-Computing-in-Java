package com.ashfaq.example.async.eg2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/process")
    public ResponseEntity<String> processProduct() {
        productService.processProductAsync();
        return ResponseEntity.ok("Processing started. Check logs for status.");
    }
}
