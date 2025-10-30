package com.example.reactivedemo.controller;

import com.example.reactivedemo.model.Product;
import com.example.reactivedemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET /products → list of all products
    @GetMapping
    public Flux<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET /products/{id} → single product by ID
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id)
                .map(product -> ResponseEntity.ok(product))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
