package com.example.multipledatasources.model.product;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository {
    List<Product> getAllProducts();
}
