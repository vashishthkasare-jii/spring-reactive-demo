package com.example.reactivedemo.service;

import com.example.reactivedemo.model.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();

    @PostConstruct
    public void initData() {
        products.add(new Product(1, "Keyboard", new BigDecimal("29.99")));
        products.add(new Product(2, "Mouse", new BigDecimal("19.99")));
        products.add(new Product(3, "Monitor", new BigDecimal("129.99")));
    }

    public Flux<Product> getAllProducts() {
        // Return a reactive stream of all products
        return Flux.fromIterable(products);
    }

    public Mono<Product> getProductById(Integer id) {
        // Return the first matching product, or empty Mono if not found
        return Flux.fromIterable(products)
                   .filter(p -> p.getId().equals(id))
                   .next();
    }
}
