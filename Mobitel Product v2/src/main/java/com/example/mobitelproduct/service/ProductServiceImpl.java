package com.example.mobitelproduct.service;

import com.example.mobitelproduct.HeaderMapper;
import com.example.mobitelproduct.dto.ProductDto;
import com.example.mobitelproduct.entity.Product;
import com.example.mobitelproduct.repository.ProductRepo;
import com.example.mobitelproduct.util.RequestHandler;
import com.example.mobitelproduct.util.ResponseHandler;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseHandler<ProductDto> addProduct(RequestHandler<ProductDto> ptRequest) {
        ResponseHandler<ProductDto> ptResponse = new ResponseHandler<>();

        Product p = modelMapper.map(ptRequest.getBody(), Product.class);
        p.setRequestedBy(ptRequest.requestHeader.getRequestedBy());
        Product product = productRepo.save(p);
        ptResponse.setBody(modelMapper.map(product, ProductDto.class));
        HeaderMapper.success(ptResponse, "created");
        return ptResponse;
    }

    public ResponseHandler<ProductDto> findAllProducts() {
        ResponseHandler<ProductDto> ptResponse = new ResponseHandler<>();

        List<Product> productList = productRepo.findAll();
        TypeToken<List<ProductDto>> typeToken = new TypeToken<>() {
        };
        List<ProductDto> list = modelMapper.map(productList, typeToken.getType());
        ptResponse.setParaList(list);
        HeaderMapper.success(ptResponse, "find All");
        return ptResponse;
    }

    public ResponseHandler<ProductDto> updateProduct(RequestHandler<ProductDto> ptRequest) {
        ResponseHandler<ProductDto> ptResponse = new ResponseHandler<>();

        Product p = modelMapper.map(ptRequest.getBody(), Product.class);
        p.setRequestedBy(ptRequest.requestHeader.getRequestedBy());
        Product product = productRepo.save(p);
        ptResponse.setBody(modelMapper.map(product, ProductDto.class));
        HeaderMapper.success(ptResponse, "updated");
        return ptResponse;
    }

    public ResponseHandler<ProductDto> findProductById(Long id) {
        ResponseHandler<ProductDto> ptResponse = new ResponseHandler<>();

        try {
            Product product = productRepo.findProductById(id);
            ProductDto productDto = modelMapper.map(product, ProductDto.class);
            ptResponse.setBody(productDto);
            HeaderMapper.success(ptResponse, "found by id");
        } catch (IllegalArgumentException e) {
            HeaderMapper.notFoundError(ptResponse, "Product by id " + id + " was not found");
        }

        return ptResponse;
    }

    @Transactional
    public ResponseHandler<ProductDto> deleteProductById(Long id) {
        ResponseHandler<ProductDto> ptResponse = new ResponseHandler<>();
        productRepo.deleteProductById(id);
        HeaderMapper.success(ptResponse, "deleted by id");
        return ptResponse;
    }
}
