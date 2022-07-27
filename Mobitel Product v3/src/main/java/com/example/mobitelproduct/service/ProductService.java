package com.example.mobitelproduct.service;

import com.example.mobitelproduct.dto.ProductDto;
import com.example.mobitelproduct.dto.ProductHisDto;
import com.example.mobitelproduct.entity.ProductHis;
import com.example.mobitelproduct.util.RequestHandler;
import com.example.mobitelproduct.util.ResponseHandler;

import java.util.List;

public interface ProductService {

    ResponseHandler<ProductDto> addProduct(RequestHandler<ProductDto> ptRequest);

    ResponseHandler<ProductDto> findAllProducts();

    ResponseHandler<ProductDto> updateProduct(RequestHandler<ProductDto> ptRequest);

    ResponseHandler<ProductDto> findProductById(Long id);

    ResponseHandler<ProductDto> deleteProductById(Long id);

    ResponseHandler<ProductHisDto> findAllHistory();

    ResponseHandler<ProductHisDto> findHisByName(String name);

}
