package com.example.mobitelproduct.controller;

import com.example.mobitelproduct.ResponseHandler;
import com.example.mobitelproduct.commonUrl;
import com.example.mobitelproduct.dto.ProductDto;
import com.example.mobitelproduct.entity.Product;
import com.example.mobitelproduct.service.ProductService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;
    @Autowired
    private ModelMapper modelMapper;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(commonUrl.create)
    public ResponseEntity<Object> create(@RequestBody ProductDto productDto) {
        try {
            Product pt = modelMapper.map(productDto, Product.class);
            Product newProduct = productService.addProduct(pt);
            return ResponseHandler.generateResponse("Successfully added", HttpStatus.CREATED, newProduct);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @GetMapping(commonUrl.getAll)
    public ResponseEntity<Object> getAll() {
        try {
            List<Product> productList = productService.findAllProducts();
            TypeToken<List<ProductDto>> typeToken = new TypeToken<>() {};
            List<ProductDto> list = modelMapper.map(productList, typeToken.getType());
            return ResponseHandler.generateResponse("Successfully retrieved data", HttpStatus.OK, list);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @PutMapping(commonUrl.update)
    public ResponseEntity<Object> update(@RequestBody ProductDto productDto) {
        try {
            Product pt = modelMapper.map(productDto, Product.class);
            Product updatedProduct = productService.updateProduct(pt);
            return ResponseHandler.generateResponse("Successfully updated data", HttpStatus.OK, updatedProduct);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @DeleteMapping(commonUrl.delete)
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try {
            productService.deleteProductById(id);
            return ResponseHandler.generateResponse("Successfully deleted data", HttpStatus.ACCEPTED, null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }

    }

    @GetMapping(commonUrl.findById)
    public ResponseEntity<Object> find(@PathVariable("num") Long id) {
        try {
            Product findProduct = productService.findProductById(id);
            ProductDto productDto = modelMapper.map(findProduct, ProductDto.class);
            return ResponseHandler.generateResponse("Successfully found data", HttpStatus.OK, productDto);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
