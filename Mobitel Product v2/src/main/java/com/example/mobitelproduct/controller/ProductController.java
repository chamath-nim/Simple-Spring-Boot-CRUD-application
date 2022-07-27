package com.example.mobitelproduct.controller;

import com.example.mobitelproduct.commonUrl;
import com.example.mobitelproduct.dto.ProductDto;
import com.example.mobitelproduct.service.ProductService;
import com.example.mobitelproduct.util.RequestHandler;
import com.example.mobitelproduct.util.ResponseHandler;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(commonUrl.create)
    public ResponseHandler<ProductDto> create(@RequestBody RequestHandler<ProductDto> ptRequest) {
        return productService.addProduct(ptRequest);
    }

    @GetMapping(commonUrl.getAll)
    public ResponseHandler<ProductDto> getAll() {
       return productService.findAllProducts();
    }

    @PutMapping(commonUrl.update)
    public ResponseHandler<ProductDto> update(@RequestBody RequestHandler<ProductDto> ptRequest) {
        return productService.updateProduct(ptRequest);
    }

    @GetMapping(commonUrl.findById)
    public ResponseHandler<ProductDto> find(@PathVariable("num") Long id) {
        return productService.findProductById(id);
    }

    @DeleteMapping(commonUrl.delete)
    public ResponseHandler<ProductDto> delete(@PathVariable Long id) {
        return productService.deleteProductById(id);

    }


}
