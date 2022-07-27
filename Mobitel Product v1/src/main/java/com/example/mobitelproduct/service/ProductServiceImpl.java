package com.example.mobitelproduct.service;

import com.example.mobitelproduct.entity.Product;
import com.example.mobitelproduct.exception.UserNotFoundException;
import com.example.mobitelproduct.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepo productRepo;

    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    public List<Product> findAllProducts() {
        return productRepo.findAll();
    }

    public Product updateProduct(Product product) {
        return productRepo.save(product);
    }

    public Product findProductById(Long id) {
        return productRepo.findProductById(id).orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    @Transactional
    public void deleteProductById(Long id) {
        productRepo.deleteProductById(id);
    }
}
