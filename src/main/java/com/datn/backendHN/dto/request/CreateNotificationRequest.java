package com.datn.backendHN.dto.request;

import lombok.Data;

@Data
public class CreateNotificationRequest {
    private Integer userId;
    private String message;
    private String title;
} 