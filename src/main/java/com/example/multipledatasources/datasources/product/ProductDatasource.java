package com.example.multipledatasources.datasources.product;

import com.example.multipledatasources.model.product.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDatasource implements ProductRepository {

    @Override
    public List<Product> getAllProducts() {
        return null;
    }
}
