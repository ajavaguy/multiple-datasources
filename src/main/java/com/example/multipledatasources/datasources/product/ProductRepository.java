package com.example.multipledatasources.datasources.product;

import com.example.multipledatasources.model.product.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();
}
