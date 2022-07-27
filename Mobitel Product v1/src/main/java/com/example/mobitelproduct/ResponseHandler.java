package com.example.mobitelproduct;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResponseHandler {
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object resStudent) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", resStudent);
        return new ResponseEntity<>(map, status);
    }
}
