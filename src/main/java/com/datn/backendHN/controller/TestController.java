package com.datn.backendHN.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Kiểm tra hệ thống", description = "Các API dùng để kiểm tra trạng thái hoạt động của hệ thống")
public class TestController {
    
    @GetMapping("/api/test")
    public String testAPI() {
        return "API đang hoạt động tốt!";
    }

    @GetMapping("/api/status")
    public Map<String, String> getStatus() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("message", "API đang hoạt động");
        response.put("timestamp", new Date().toString());
        return response;
    }
    

    
} 