package com.example.multipledatasources.datasources.product;

import com.example.multipledatasources.model.employee.Employee;
import com.example.multipledatasources.model.product.Product;
import com.example.multipledatasources.model.product.ProductRepository;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDataSource implements ProductRepository {
    private ProductMapper productMapper;

    @Autowired
    public ProductDataSource(@Qualifier("microservicesDemoSQLSession") SqlSessionTemplate sqlSessionTemplate) {
        this.productMapper = sqlSessionTemplate.getMapper(ProductMapper.class);
    }

    @Override
    public List<Product> getAllProducts() {
        return productMapper.getAllProducts();
    }
}
