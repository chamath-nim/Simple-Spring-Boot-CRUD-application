package com.example.mobitelproduct.service;

import com.example.mobitelproduct.HeaderMapper;
import com.example.mobitelproduct.dto.ProductDto;
import com.example.mobitelproduct.dto.ProductHisDto;
import com.example.mobitelproduct.entity.Product;
import com.example.mobitelproduct.entity.ProductHis;
import com.example.mobitelproduct.repository.ProductHisRepo;
import com.example.mobitelproduct.repository.ProductRepo;
import com.example.mobitelproduct.util.RequestHandler;
import com.example.mobitelproduct.util.ResponseHandler;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductHisRepo productHisRepo;
    @Autowired
    private ModelMapper modelMapper;

    public ResponseHandler<ProductDto> addProduct(RequestHandler<ProductDto> ptRequest) {
        ResponseHandler<ProductDto> ptResponse = new ResponseHandler<>();

        Product p = modelMapper.map(ptRequest.getBody(), Product.class);
        p.setCreatedBy(ptRequest.requestHeader.getCreatedBy());
        p.setCrDateTime(CurrentDateTime());
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
        String currentDateTime = CurrentDateTime();

        Product p = modelMapper.map(ptRequest.getBody(), Product.class);
        p.setCreatedBy(ptRequest.requestHeader.getCreatedBy());
        p.setCrDateTime(currentDateTime);

        Product old = productRepo.findProductById(p.getId());
        ProductHis productHis = modelMapper.map(old, ProductHis.class);
        productHis.setUptDateTime(currentDateTime);
        productHis.setUpdatedBy(ptRequest.requestHeader.getCreatedBy());
        productHis.setId(null);

        productHisRepo.save(productHis);
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

    public ResponseHandler<ProductHisDto> findAllHistory(){
        ResponseHandler<ProductHisDto> ptResponse = new ResponseHandler<>();

        List<ProductHis> productHisList = productHisRepo.findAll();
        TypeToken<List<ProductHisDto>> typeToken = new TypeToken<>() {
        };
        List<ProductHisDto> list = modelMapper.map(productHisList, typeToken.getType());
        ptResponse.setParaList(list);
        HeaderMapper.success(ptResponse, "find All");
        return ptResponse;
    }

    public ResponseHandler<ProductHisDto> findHisByName(String name){
        ResponseHandler<ProductHisDto> ptResponse = new ResponseHandler<>();

        List<ProductHis> productHisList = productHisRepo.findProductHisByPkgName(name);
        TypeToken<List<ProductHisDto>> typeToken = new TypeToken<>() {
        };
        List<ProductHisDto> list = modelMapper.map(productHisList, typeToken.getType());
        ptResponse.setParaList(list);
        HeaderMapper.success(ptResponse, "find All");
        return ptResponse;
    }

    public String CurrentDateTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
