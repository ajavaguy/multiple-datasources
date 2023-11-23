package com.example.multipledatasources.datasources.product;

import com.example.multipledatasources.model.product.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> getAllProducts();
}
