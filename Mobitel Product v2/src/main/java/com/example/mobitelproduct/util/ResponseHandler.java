package com.example.mobitelproduct.util;

import lombok.Data;

import java.util.List;

@Data
public class ResponseHandler<T> {
    private ResponseHeader responseHeader;
    private List<T> paraList;
    private T body;
}
