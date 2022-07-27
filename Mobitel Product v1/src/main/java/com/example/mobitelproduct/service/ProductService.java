package com.example.mobitelproduct.service;

import com.example.mobitelproduct.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    List<Product> findAllProducts();

    Product updateProduct(Product product);

    Product findProductById(Long id);

    void deleteProductById(Long id);
}
