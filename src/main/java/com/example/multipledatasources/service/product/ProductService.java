package com.example.multipledatasources.service.product;

import com.example.multipledatasources.datasources.product.ProductRepository;
import com.example.multipledatasources.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() {
        return productRepository.getAllProducts();
    }
}
